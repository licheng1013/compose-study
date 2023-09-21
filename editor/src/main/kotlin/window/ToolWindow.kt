package window

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import theme.Theme
import ui.window.DefaultWindow
import ui.window.WindowPosition

class ToolWindow : DefaultWindow() {
    override fun id(): String {
        return "TOOL"
    }

    @Composable
    override fun windowUi() {
        Column(
        ) {
            repeat(100) {
                Text("Hello Tool #$it", color = Theme.getInstance().fontColor)
            }
        }
    }

    /**
     *  窗口位置
     */
    override fun position(): WindowPosition {
        return WindowPosition.RIGHT_TOP
    }

    override fun color(): Color {
        return Color(83, 167, 197)
    }
}