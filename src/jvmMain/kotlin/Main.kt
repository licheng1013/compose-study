
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import file.home


fun main() = application {
//    Thread{
//        web()
//    }.start()

    Window(
        title = "File Sync",
        onCloseRequest = ::exitApplication
    ) {
        home()
    }

}