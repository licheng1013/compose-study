package window.impl

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import theme.Theme
import window.DefaultWindow
import window.WindowPosition

class RunWindow : DefaultWindow() {

    @Composable
    override fun windowUi() {
        // 构建一个滚动列表
        Box(modifier = Modifier.fillMaxSize()){
            val scrollState = rememberScrollState()
            Box {
                Column(
                    Modifier.verticalScroll(scrollState).fillMaxSize()
                        .background(color = Theme.lightGery)
                ) {
                    repeat(1000) {
                        Text("Run #$it", color = Theme.fontColor)
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

    override fun id(): String {
        return "RUN"
    }

    /**
     *  窗口位置
     */
    override fun position(): WindowPosition {
        return WindowPosition.LEFT_BOTTOM
    }

    override fun color(): Color {
        return Color(190, 147, 255)
    }

    override fun icon(): ImageVector {
        return Icons.Default.Build
    }
}