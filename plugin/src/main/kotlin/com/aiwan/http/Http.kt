package com.aiwan.http

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.aiwan.PluginUi
import ui.Ui

object Http : PluginUi {

    @Composable
    override fun ui() {
        Text("Hello Http")
    }
}



fun main() = application {
    Window(
        title = "Http Util",
        onCloseRequest = ::exitApplication
    ) {
        Http.ui()
    }
}