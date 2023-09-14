package demo

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application


fun main() = application {

    Window(
        title = "Demo Sync",
        onCloseRequest = ::exitApplication
    ) {
        sidebarDemo()
    }

}