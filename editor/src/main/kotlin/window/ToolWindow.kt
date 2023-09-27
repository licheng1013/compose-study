package window

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import theme.Theme
import ui.window.DefaultWindow
import ui.window.WindowPosition

object ToolWindow : DefaultWindow() {
    override fun id(): String {
        return "TOOL"
    }

    @Composable
    override fun windowUi() {
        Column {
            repeat(100) {
                Text("Hello Tool #$it", color = Theme.getInstance().fontColor, modifier = Modifier.width(150.dp))
            }
        }
    }

    /**
     *  窗口位置
     */
    override fun position(): WindowPosition {
        return WindowPosition.RIGHT_TOP
    }

    override fun iconColor(): Color {
        return Color(83, 167, 197)
    }


}