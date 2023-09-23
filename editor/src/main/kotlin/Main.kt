import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import open.IconUtil
import org.antlr.v4.parse.ANTLRLexer
import theme.ThemeIcon


fun main() = application {
    Window(
        title = "My Editor",
        onCloseRequest = ::exitApplication,
        icon = painterResource(ThemeIcon.getInstance().settings)
    ) {
        Editor.editor.ui()
    }
}