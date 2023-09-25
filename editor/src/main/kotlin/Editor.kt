import action.*
import action.sub.OpenFolder
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.DataManager
import open.PointerUtil
import theme.Theme
import ui.Ui
import ui.document.DefaultDocument
import ui.document.EmptyDocument
import ui.window.DefaultWindow
import ui.window.WindowPosition
import window.*

class Editor : Ui {
    // header height
    private val headerHeight = 35.dp

    // footer height
    private val footerHeight = 25.dp

    // left or right width
    private val leftRightWidth = 30.dp
    private val toolColor = Theme.getInstance().lightGery
    private val bodyColor = Theme.getInstance().darkGery


    val windowList = mutableStateListOf<DefaultWindow>()
    val actionList = mutableStateListOf<DefaultAction>()
    private val documentList = mutableStateListOf<DefaultDocument>()
    private val documentId = mutableStateOf("")

    private var leftWindowWidth = mutableStateOf(200)
    private var rightWindowWidth = mutableStateOf(200)
    private var bottomWindowWidth = mutableStateOf(100)
    var leftTopWindowId = mutableStateOf("")
    var rightTopWindowId = mutableStateOf("")
    var leftBottomWindowId = mutableStateOf("")


    init {
        // 默认动作
        addAction(FileAction())
        addAction(EditAction())
        addAction(CodeAction())
        addAction(HelpAction())
        addAction(OpenFolder())

        // 默认窗口
        addWindow(FileWindow)
        addWindow(MessageWindow)
        addWindow(RunWindow())
        addWindow(ToolWindow())
        addWindow(PluginWindow())

        val manager = DataManager.getInstance()
        if (manager.openPath.isNotEmpty()) {
            FileWindow.selectPath(manager.openPath)
            openWindowById(FileWindow.id())
        }

    }

    private fun addAction(action: DefaultAction) {
        // check
        for (act in actionList) {
            if (act.id() == action.id()) {
                println("Action id is repeat")
                return
            }
        }
        // 是否存在在相同的组件
        for (act in actionList) {
            if (act.group() == action.group()) {
                act.actionList().add(action)
                return
            }
        }
        actionList.add(action)
    }


    fun openWindowById(id: String) {
        for (window in windowList) {
            if (window.id() == id) {
                window.selected.value = true
                when (window.position()) {
                    WindowPosition.LEFT_TOP -> leftTopWindowId.value = window.id()
                    WindowPosition.LEFT_BOTTOM -> leftBottomWindowId.value = window.id()
                    WindowPosition.RIGHT_TOP -> rightTopWindowId.value = window.id()
                    WindowPosition.RIGHT_BOTTOM -> TODO()
                }
            }
        }
    }


    private fun addDocument(document: DefaultDocument) {
        // check
        for (doc in documentList) {
            if (doc.id() == document.id()) {
                println("Document id is repeat")
                return
            }
        }
        documentList.add(document)
    }

    fun addDocumentWithSelect(document: DefaultDocument) {
        addDocument(document)
        documentId.value = document.id()
    }


    private fun addWindow(window: DefaultWindow) {
        windowList.forEach {
            if (it.id().contains(window.id())) {
                throw RuntimeException("窗口插件id重复")
            }
        }
        windowList.add(window)
    }

    private fun getDocumentById(id: String): DefaultDocument {
        for (document in documentList) {
            if (document.id() == id) {
                return document
            }
        }
        return EmptyDocument()
    }


    private fun getWindowById(id: String): DefaultWindow {
        for (window in windowList) {
            if (window.id() == id) {
                return window
            }
        }
        throw RuntimeException("Window not fround")
    }

    @Composable
    private fun buildWindowByPosition(position: WindowPosition) {
        repeat(windowList.size) {
            val action = windowList[it]
            if (action.position() == position) {
                windowSPacer()
                action.ui()
            }
        }
    }


    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun ui() {
        Column(modifier = PointerUtil.onTap {
            contextMenu.value = false
        }
            .fillMaxSize()
        ) {
            // header park
            Row(
                modifier = Modifier.height(headerHeight).fillMaxWidth().background(toolColor),
                verticalAlignment = Alignment.CenterVertically
            ) {
                localSpacer(leftRightWidth)
                actionList.forEach {
                    it.ui()
                    localSpacer()
                }
            }

            horizontalSpacer()

            // body park
            Row(modifier = Modifier.weight(1f).fillMaxHeight()) {
                // left park
                Column(modifier = Modifier.width(leftRightWidth).fillMaxHeight().background(color = toolColor)) {
                    buildWindowByPosition(WindowPosition.LEFT_TOP)
                    Spacer(Modifier.weight(1f))
                    buildWindowByPosition(WindowPosition.LEFT_BOTTOM)
                }
                verticalSpacer()


                Column(modifier = Modifier.weight(1f)) {
                    Row(modifier = Modifier.weight(1f)) {
                        if (leftTopWindowId.value.isNotEmpty()) {
                            Box(
                                Modifier.width(leftWindowWidth.value.dp).fillMaxHeight()
                                    .background(color = Theme.getInstance().lightGery)
                            ) {
                                getWindowById(leftTopWindowId.value).layout()
                            }
                            Theme.getInstance().dragX {
                                leftWindowWidth.value += it.toInt()
                            }
                        }

                        // center park
                        Column(modifier = Modifier.weight(1f).background(color = bodyColor)) {
                            Box(modifier = Modifier.height(40.dp)) {
                                val scrollState = rememberScrollState()
                                Row(
                                    modifier = Modifier.fillMaxHeight().horizontalScroll(scrollState),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    repeat(documentList.size) {
                                        TooltipArea({
                                            Theme.getInstance().tipPanel {
                                                Text(
                                                    documentId.value,
                                                    color = Theme.getInstance().fontColor,
                                                    modifier = Modifier.padding(8.dp)
                                                )
                                            }
                                        }
                                        ) {
                                            FileBar(documentList[it], documentId).ui()
                                        }
                                    }
                                }
                                HorizontalScrollbar(
                                    adapter = rememberScrollbarAdapter(scrollState),
                                    Modifier.align(Alignment.BottomCenter),
                                    style = Theme.getInstance().scrollbarStyle()
                                )
                            }
                            horizontalSpacer()
                            when (documentId.value.isEmpty()) {
                                true -> Column(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text("No document", color = Theme.getInstance().fontColor, fontSize = 32.sp)
                                }

                                false -> getDocumentById(documentId.value).layout()
                            }
                        }

                        if (rightTopWindowId.value.isNotEmpty()) {
                            Theme.getInstance().dragX {
                                rightWindowWidth.value -= it.toInt()
                            }
                            Box(
                                Modifier.width(rightWindowWidth.value.dp).fillMaxHeight()
                                    .background(color = Theme.getInstance().lightGery)
                            ) {
                                getWindowById(rightTopWindowId.value).layout()
                            }
                        }
                    }

                    if (leftBottomWindowId.value.isNotEmpty()) {
                        Theme.getInstance().dragY {
                            bottomWindowWidth.value -= it.toInt()
                        }
                        Box(
                            Modifier.height(bottomWindowWidth.value.dp).fillMaxWidth()
                                .background(color = Theme.getInstance().lightGery)
                        ) {
                            getWindowById(leftBottomWindowId.value).layout()
                        }
                    }
                }

                // right park
                verticalSpacer()
                Column(modifier = Modifier.width(leftRightWidth).fillMaxHeight().background(color = toolColor)) {
                    buildWindowByPosition(WindowPosition.RIGHT_TOP)
                    Spacer(Modifier.weight(1f))
                    buildWindowByPosition(WindowPosition.RIGHT_BOTTOM)
                }
            }


            horizontalSpacer()
            // footer park
            Row(
                modifier = Modifier.height(footerHeight).fillMaxWidth().background(toolColor),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(Modifier.weight(1f))
                Text("12char", color = fontColor)
                localSpacer()
                Text("11:23", color = fontColor)
                localSpacer()
                Text("UTF-8", color = fontColor)
                localSpacer()
            }
        }


        if (contextMenu.value) {
            Box(
                Modifier.offset(
                    contextMenuOffset.value.x.dp, contextMenuOffset.value.y.dp
                )
            ) {
                Column(
                    modifier = Theme.getInstance().border()
                        .background(Theme.getInstance().lightGery)
                        .width(200.dp)
                ) {
                    Text("Hello", color = Theme.getInstance().fontColor)
                }
            }

        }


    }

    private var contextMenu = mutableStateOf(false)
    private var contextMenuOffset = mutableStateOf(Offset.Zero)

    fun openContextMenu(offset: Offset) {
        //contextMenu.value = true
        contextMenuOffset.value = offset
    }


    // 10.dp 分割宽度
    @Composable
    fun localSpacer(width: Dp = 6.dp) {
        Spacer(modifier = Modifier.width(width))
    }

    @Composable
    fun windowSPacer() {
        Spacer(modifier = Modifier.height(0.dp))
    }

    fun offAll(position: WindowPosition, id: String) {
        // 关闭所有窗口
        for (window in windowList) {
            if (window.position() == position && window.id() != id) {
                window.selected.value = false
            }
        }
    }

    fun removeDocument(documentId: String) {
        // first document id
        var firstDocumentId = ""
        for (document in documentList) {
            if (document.id() == documentId) {
                documentList.remove(document)
                break
            }
            firstDocumentId = document.id()
        }
        if (firstDocumentId.isEmpty() && documentList.isNotEmpty()) {
            firstDocumentId = documentList.first().id()
        }
        this.documentId.value = firstDocumentId
    }

    fun closeContextMenu() {
        contextMenu.value = false
    }

    companion object {
        var editor = Editor()
    }
}