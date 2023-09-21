import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import theme.Theme
import tool.Tool
import ui.Ui
import ui.window.DefaultWindow
import ui.window.WindowPosition
import window.FileWindow
import window.MessageWindow
import window.RunWindow
import window.ToolWindow

class Editor : Ui {
    // header height
    private val headerHeight = 35.dp

    // footer height
    private val footerHeight = 25.dp

    // left or right width
    private val leftRightWidth = 30.dp

    // left or right color
    private val toolColor = Theme.getInstance().lightGery

    // body color
    private val bodyColor = Theme.getInstance().darkGery

    // selected file bar
    val selectedFileIndex = mutableStateOf(0)

    // left action index
    val leftActionIndex = mutableStateOf(0)

    // left action list
    private val windowList = mutableStateListOf<DefaultWindow>()


    private var leftWindowWidth = mutableStateOf(200)
    private var rightWindowWidth = mutableStateOf(200)
    private var bottomWindowWidth = mutableStateOf(100)
    var leftTopWindowId = mutableStateOf("")
    var rightTopWindowId = mutableStateOf("")
    var leftBottomWindowId = mutableStateOf("")


    init {
        windowList.add(FileWindow())
        windowList.add(MessageWindow())
        windowList.add(RunWindow())
        windowList.add(ToolWindow())
        leftTopWindowId.value = windowList.first().id()
        windowList.first().selected.value = true

        checkWindowId()
    }

    private fun checkWindowId() {
        // 检查窗口id是否重复
        val idList = mutableListOf<String>()
        for (window in windowList) {
            if (idList.contains(window.id())) {
                throw RuntimeException("Window id is repeat")
            }
            idList.add(window.id())
        }
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


    @Composable
    override fun ui() {
        Column(modifier = Modifier.fillMaxSize()) {
            // header park
            Row(
                modifier = Modifier.height(headerHeight).fillMaxWidth().background(toolColor),
                verticalAlignment = Alignment.CenterVertically
            ) {
                localSpacer(leftRightWidth)
                Tool("File", this@Editor).ui()
                localSpacer()
                Tool("Edit", this@Editor).ui()
                localSpacer()
                Tool("About", this@Editor).ui()
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
                        Column(modifier = Modifier.weight(1f).fillMaxHeight().background(color = bodyColor)) {
                            Box(modifier = Modifier.height(40.dp)) {
                                val scrollState = rememberScrollState()
                                Row(
                                    modifier = Modifier.fillMaxHeight().horizontalScroll(scrollState),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    repeat(5) {
                                        FileBar("Demo.kt", it == selectedFileIndex.value, it).ui()
                                    }
                                }
                                HorizontalScrollbar(
                                    adapter = rememberScrollbarAdapter(scrollState),
                                    Modifier.align(Alignment.BottomCenter),
                                    style = Theme.getInstance().scrollbarStyle()
                                )
                            }
                            horizontalSpacer()
                            Box(modifier = Modifier.weight(1f)) {
                                // 构建一个滚动列表
                                val scrollState = rememberScrollState()
                                Box {
                                    Column(
                                        Modifier.verticalScroll(scrollState).fillMaxWidth()
                                    ) {
                                        repeat(5) {
                                            Text("Hello body #$it", color = fontColor)
                                        }
                                    }
                                }
                                VerticalScrollbar(
                                    adapter = rememberScrollbarAdapter(scrollState),
                                    Modifier.align(Alignment.CenterEnd),
                                    style = Theme.getInstance().scrollbarStyle()
                                )
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

    companion object {
        var editor = Editor()
    }
}