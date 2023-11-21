package demo

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application


object LayoutState{
    val inputText = mutableStateOf("")
}


// 这个示例展示了基本布局
@Composable
@Preview
fun LayoutDemo() {
    MaterialTheme { // Material 主题
        Row { // 水平布局组件
            // 文字组件，Modifier.weight(1f) 设置组件大小权重
            Text(LayoutState.inputText.value, modifier = Modifier.weight(1f))
            // 垂直布局组件
            Column(modifier = Modifier.weight(1f)) {
                Text("垂直布局")
                Text("Hello World")
                OutlinedTextField(LayoutState.inputText.value, onValueChange = {
                    LayoutState.inputText.value = it
                })
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        LayoutDemo()
    }
}

