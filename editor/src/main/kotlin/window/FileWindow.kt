package window

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import ui.window.DefaultWindow
import ui.window.WindowPosition
import window.file.FileTree

object FileWindow : DefaultWindow() {
    override fun id(): String {
        return "FILE"
    }

    private var path = mutableStateOf("")

    private var fileTree = FileTree(path.value)

    fun selectPath(path: String) {
        fileTree = FileTree(path)
        this.path.value = path
    }


    @Composable
    override fun windowUi() {
        if (path.value.isNotEmpty()) {
            fileTree.ui()
        }
    }

    /**
     *  窗口位置
     */
    override fun position(): WindowPosition {
        return WindowPosition.LEFT_TOP
    }

    override fun iconColor(): Color {
        return Color(83, 167, 197)
    }

}