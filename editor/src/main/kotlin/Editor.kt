import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import theme.Theme
import tool.Tool
import ui.Ui
import window.DefaultWindow
import window.WindowPosition
import window.impl.FileWindow
import window.impl.MessageWindow
import window.impl.RunWindow

class Editor : Ui {
    // header height
    private val headerHeight = 35.dp

    // footer height
    private val footerHeight = 25.dp

    // left or right width
    private val leftRightWidth = 30.dp

    // left or right color
    private val toolColor = Theme.lightGery

    // body color
    private val bodyColor = Theme.darkGery

    // selected file bar
    val selectedFileIndex = mutableStateOf(0)

    // left action index
    val leftActionIndex = mutableStateOf(0)

    // left action list
    private val windowList = mutableStateListOf<DefaultWindow>()


    private var leftWindowWidth = mutableStateOf(200)
    private var width = 200;
    var leftTopWindowId = mutableStateOf("")
    var leftBottomWindowId = mutableStateOf("")


    init {
        windowList.add(FileWindow())
        windowList.add(MessageWindow())
        windowList.add(RunWindow())
        leftTopWindowId.value = windowList.first().id()
    }

    private fun getWindowById(id:String): DefaultWindow {
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
                if (leftTopWindowId.value.isNotEmpty()) {
                    Box(Modifier.width(leftWindowWidth.value.dp)) {
                        getWindowById(leftTopWindowId.value).windowUi()
                    }
                    Spacer(
                        Modifier.fillMaxHeight().background(Color.White).width(1.dp)
                            .pointerHoverIcon(PointerIcon.Hand).pointerInput(Unit) {
                                detectDragGestures { _, dragAmount ->
                                    leftWindowWidth.value += dragAmount.x.toInt()
                            }
                        })
                }

                // center park
                Column(modifier = Modifier.weight(1f).fillMaxHeight().background(color = bodyColor)) {
                    Row(modifier = Modifier.height(40.dp), verticalAlignment = Alignment.CenterVertically) {
                        repeat(5) {
                            FileBar("Demo.kt", it == selectedFileIndex.value, this@Editor, it).ui()
                        }
                    }
                    horizontalSpacer()
                    Box(modifier = Modifier.weight(1f)) {
                        // 构建一个滚动列表
                        val scrollState = rememberScrollState()
                        Box {
                            Column(
                                Modifier.verticalScroll(scrollState).fillMaxWidth()
                            ) {
                                repeat(1000) {
                                    Text("Hello body #$it", color = fontColor)
                                }
                            }
                        }
                        VerticalScrollbar(
                            adapter = rememberScrollbarAdapter(scrollState),
                            Modifier.align(Alignment.CenterEnd),
                            style = Theme.scrollbarStyle()
                        )
                    }

                    if (leftBottomWindowId.value.isNotEmpty()) {
                        Spacer(
                            Modifier.fillMaxWidth().background(Color.White).height(1.dp)
                                .pointerHoverIcon(PointerIcon.Hand).pointerInput(Unit) {
                                    detectDragGestures { _, dragAmount ->
                                        // leftWindowWidth.value += dragAmount.x.toInt()
                                    }
                                })
                        Box(Modifier.height(100.dp)) {
                            getWindowById(leftBottomWindowId.value).windowUi()
                        }
                    }
                }
                // right park
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

    companion object {
        var editor = Editor()
    }
}