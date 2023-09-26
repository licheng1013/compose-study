package window

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import open.DateUtil
import open.IconUtil
import open.PointerUtil
import theme.Theme
import theme.ThemeIcon
import ui.window.DefaultWindow
import ui.window.WindowPosition

object MessageWindow : DefaultWindow() {

    private var messageList = mutableStateListOf<@Composable () -> Unit>()

    init {
        repeat(30) {
            textMessage("HelloWorld$it")
        }
    }

    @Composable
    override fun windowUi() {
        Column {
            Spacer(modifier = Modifier.height(6.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.width(8.dp))
                Text("Message", color = Theme.getInstance().fontColor)
                Spacer(modifier = Modifier.weight(1f))
                Text("Clear", color = Color(87, 170, 247), modifier = PointerUtil.onTapWithHand{
                    messageList.clear()
                })
                Spacer(modifier = Modifier.width(8.dp))
            }
            Box {
                val scrollStateY = ScrollState(0)
                Box(
                    Modifier.verticalScroll(scrollStateY).fillMaxSize()
                        .background(color = Theme.getInstance().lightGery)
                ) {
                    repeat(messageList.size) {
                        messageList[it]()
                    }
                }
                VerticalScrollbar(
                    adapter = rememberScrollbarAdapter(scrollStateY),
                    Modifier.align(Alignment.CenterEnd),
                    style = Theme.getInstance().scrollbarStyle()
                )
            }
        }
    }


    @Composable
    override fun layout() {
        windowUi()
    }

    override fun id(): String {
        return "MESSAGE"
    }

    /**
     *  窗口位置
     */
    override fun position(): WindowPosition {
        return WindowPosition.RIGHT_TOP
    }

    override fun iconColor(): Color {
        return Color(109, 213, 128)
    }

    override fun iconPath(): String {
        return ThemeIcon.getInstance().message
    }

    private fun textMessage(message: String) {
        messageList.add {
            Box(modifier = Modifier.padding(6.dp)) {
                Box(modifier = Modifier.fillMaxWidth().background(Theme.getInstance().tipColor)) {
                    Column {
                        Spacer(modifier = Modifier.height(10.dp))
                        Row {
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(message, color = Theme.getInstance().fontColor)
                            Spacer(modifier = Modifier.weight(1f))
                            Text(DateUtil.getHourMinute(), color = Theme.getInstance().fontColor)
                            Spacer(modifier = Modifier.width(4.dp))
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }

}