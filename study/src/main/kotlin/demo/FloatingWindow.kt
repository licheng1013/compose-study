package demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState


fun main() = application {
    Window(
        title = "Demo Sync",
        state = rememberWindowState(
            height = 100.dp,
            width = 200.dp,
        ),
        onCloseRequest = ::exitApplication,
       // resizable = false,
       // undecorated = true,
        alwaysOnTop = true,
    ) {
        MyFloatingWindow()
    }
}

@Composable
fun MyFloatingWindow() {
    Box(
        modifier = Modifier.height(100.dp).width(200.dp)
    ) {
        Text("Hello, World!")
    }
}