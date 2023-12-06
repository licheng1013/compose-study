package demo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
fun DialogDemo() {
    val state = remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxWidth().height(500.dp)) {
        Button(onClick = { state.value = true }) {
            Text("Import")
        }
        importDialog(state)
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        DialogDemo()
    }
}

/** 对话窗口 */
@Composable
fun importDialog(state: MutableState<Boolean>) {
    if (state.value) {
        val json = remember { mutableStateOf("") }
        AlertDialog(
            onDismissRequest = { state.value = false },
            title = {
                Text(
                    text = "Import Json"
                )
            },
            text = {
                Box(modifier = Modifier) {
                    OutlinedTextField(value = json.value, onValueChange = {
                        json.value = it
                    }, label = { Text("Json") },
                        // 可滚动
                        modifier = Modifier.fillMaxWidth().height(300.dp)
                    )
                }
            },
            confirmButton = {
                OutlinedButton(onClick = {
                    println("json:${json.value}")
                    state.value = false
                }) {
                    Text("Ok")
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { state.value = false }) {
                    Text("No", color = Color.Black)
                }
            }
        )
    }
}