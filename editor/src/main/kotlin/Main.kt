import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.antlr.v4.parse.ANTLRLexer


fun main() = application {
    Window(
        title = "My Editor",
        onCloseRequest = ::exitApplication
    ) {
        Editor.editor.ui()
    }
}