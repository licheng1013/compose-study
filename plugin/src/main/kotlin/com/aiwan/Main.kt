package com.aiwan
import androidx.compose.material.Text
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application


fun main() = application {

    Window(
        title = "File Sync",
        onCloseRequest = ::exitApplication
    ) {
        Text("HelloWorld")
    }

}