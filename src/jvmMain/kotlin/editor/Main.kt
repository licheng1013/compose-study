package editor

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import file.home


fun main() = application {
    Window(
        title = "My Editor",
        onCloseRequest = ::exitApplication
    ) {
        Editor.editor.ui()
    }
}