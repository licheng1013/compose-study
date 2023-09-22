package window

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import ui.window.DefaultWindow
import ui.window.WindowPosition
import window.file.FileTree

class FileWindow : DefaultWindow() {
    override fun id(): String {
        return "FILE"
    }

    var path = mutableStateOf("")

    @Composable
    override fun windowUi() {
        if (path.value.isNotEmpty()) {
            FileTree(path.value)
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