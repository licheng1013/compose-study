package window.impl

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import theme.Theme
import window.DefaultWindow
import window.WindowPosition

class ToolWindow : DefaultWindow() {
    override fun id(): String {
        return "TOOL"
    }

    @Composable
    override fun windowUi() {
        Column(
        ) {
            repeat(100) {
                Text("Hello Tool #$it", color = Theme.fontColor)
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