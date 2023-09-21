package window.impl

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import theme.Theme
import window.DefaultWindow
import window.WindowPosition

class FileWindow : DefaultWindow() {
    override fun id(): String {
        return "FILE"
    }

    @Composable
    override fun windowUi() {
        Column {
            repeat(1000) {
                Text("Hello File #$it", color = Theme.fontColor)
            }
        }
    }

    /**
     *  窗口位置
     */
    override fun position(): WindowPosition {
        return WindowPosition.LEFT_TOP
    }

    override fun color(): Color {
        return Color(83, 167, 197)
    }
}