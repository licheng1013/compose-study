package editor

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
import editor.theme.Theme
import editor.tool.Tool
import editor.ui.Ui
import editor.window.DefaultWindow
import editor.window.WindowPosition
import editor.window.impl.FileWindow
import editor.window.impl.MessageWindow

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
    var leftWindowId = mutableStateOf("")



    init {
        windowList.add(FileWindow())
        windowList.add(MessageWindow())
        leftWindowId.value = windowList.first().id()
    }

    private fun getWindowById(): DefaultWindow {
        for (window in windowList) {
            if (window.id() == leftWindowId.value) {
                return window
            }
        }
        throw RuntimeException("Window not fround")
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
                    repeat(windowList.size) {
                        val action = windowList[it]
                        if (action.position() == WindowPosition.LEFT_TOP) {
                            windowSPacer()
                            action.ui()
                        }
                    }
                }
                if (leftWindowId.value.isNotEmpty()) {
                    Box(Modifier.width(leftWindowWidth.value.dp)) {
                        getWindowById().windowUi()
                    }
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
                }
                // right park
                Column(modifier = Modifier.width(leftRightWidth).fillMaxHeight().background(color = toolColor)) {
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
    fun windowSPacer(){
        Spacer(modifier = Modifier.height(0.dp))
    }

    companion object {
        var editor = Editor()
    }
}