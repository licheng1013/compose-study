package window

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import theme.Theme
import ui.window.DefaultWindow
import ui.window.WindowPosition

class RunWindow : DefaultWindow() {

    @Composable
    override fun windowUi() {
        // 构建一个滚动列表
        Column(

        ) {
            repeat(1000) {
                Text("Run #$it", color = Theme.getInstance().fontColor)
            }
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