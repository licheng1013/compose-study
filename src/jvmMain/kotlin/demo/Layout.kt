package demo

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

// 这个示例展示了基本布局
// Modifier.weight(0.5f) 两个子组件都一样则两边分
@Composable
@Preview
fun LayoutDemo() {
    MaterialTheme {
        Row {
            Text("水平布局", modifier = Modifier.weight(0.5f))
            Column(modifier = Modifier.weight(0.5f)) {
                Text("垂直布局")
                Text("Hello World")
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        LayoutDemo()
    }
}
