package editor

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import editor.action.Action
import editor.action.HomeAction
import editor.action.MessageAction
import editor.tool.Tool
import editor.ui.Ui

class Editor : Ui {
    // header height
    private val headerHeight = 30.dp

    // footer height
    private val footerHeight = 25.dp

    // left or right width
    private val leftRightWidth = 30.dp

    // left or right color
    private val toolColor = Color(43, 45, 48)

    // body color
    private val bodyColor = Color(30, 31, 34)

    // selected file bar
    val selectedFileIndex = mutableStateOf(0)

    // left action index
    val leftActionIndex = mutableStateOf(0)

    // left action list
    private val leftActionList = mutableStateListOf<Action>()

    init {
        leftActionList.add(HomeAction())
        leftActionList.add(MessageAction())
    }


    @Composable
    override fun ui() {
        Column(modifier = Modifier.fillMaxSize()) {
            // header park
            Row(modifier = Modifier.height(headerHeight).fillMaxWidth().background(toolColor), verticalAlignment = Alignment.CenterVertically) {
                localSpacer()
                Tool("File").ui()
                localSpacer()
                Tool("Edit").ui()
                localSpacer()
                Tool("About").ui()
            }
            horizontalSpacer()
            // body park
            Row(modifier = Modifier.weight(1f).fillMaxHeight()) {
                // left park
                Column(modifier = Modifier.width(leftRightWidth).fillMaxHeight().background(color = toolColor)) {
                    repeat(leftActionList.size) {
                        val action = leftActionList[it]
                        action.selected = it == leftActionIndex.value
                        action.index = it
                        action.editor = this@Editor
                        action.ui()
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
                        var style = ScrollbarStyle(
                            thickness = 8.dp,
                            shape = RoundedCornerShape(0.dp),
                            hoverDurationMillis = 1000,
                            unhoverColor = Color(69, 69, 71),
                            hoverColor = Color(78, 78, 80),
                            minimalHeight = LocalScrollbarStyle.current.minimalHeight + 10.dp,
                        )
                        VerticalScrollbar(
                            adapter = rememberScrollbarAdapter(scrollState),
                            Modifier.align(Alignment.CenterEnd),
                            style = style
                        )
                    }
                }
                // right park
                Column(modifier = Modifier.width(leftRightWidth).fillMaxHeight().background(color = toolColor)) {
                }
            }
            horizontalSpacer()
            // footer park
            Row(modifier = Modifier.height(footerHeight).fillMaxWidth().background(toolColor), verticalAlignment = Alignment.CenterVertically) {
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
    fun localSpacer() {
        Spacer(modifier = Modifier.width(6.dp))
    }
}