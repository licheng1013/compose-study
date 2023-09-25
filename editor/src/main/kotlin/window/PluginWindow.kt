package window

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import open.IconUtil
import theme.Theme
import theme.ThemeIcon
import ui.window.DefaultWindow
import ui.window.WindowPosition

class PluginWindow : DefaultWindow() {

    override fun id(): String {
        return "PLUGIN"
    }

    /**
     * 已经实现了窗口的滚动
     */
    @Composable
    override fun windowUi() {
        Text("MyPlugin", color = Theme.getInstance().fontColor)
    }

    /**
     *  窗口位置
     */
    override fun position(): WindowPosition {
        return WindowPosition.LEFT_TOP
    }

    override fun iconPath(): String {
        return ThemeIcon.getInstance().plugin
    }


    override fun iconColor(): Color {
        return Color(252, 198, 42)
    }
}