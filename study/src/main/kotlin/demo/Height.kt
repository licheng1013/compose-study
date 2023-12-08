package demo

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        height()
    }
}

@Composable
@Preview
fun height() {
    Column {
        Text("Hello", modifier = Modifier.weight(1f).fillMaxSize())
        LazyColumn(modifier = Modifier.weight(1f).fillMaxWidth()){
            items(10000) {
                Text("Item $it")
            }
        }
    }
}
