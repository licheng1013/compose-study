import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun Body(){
    var text by remember { mutableStateOf("Hello, World!") }
    Row(modifier = Modifier.fillMaxWidth()) {
        // 左边的组件
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier =Modifier.weight(2f).fillMaxHeight(),
            placeholder = { Text("请输入内容") },
            trailingIcon = {
                IconButton(onClick = { text = "" }) {
                    Icon(Icons.Default.Clear, "清除")
                }
            }
        )
        // 右边的组件
        Text("Right", modifier = Modifier.weight(2f))
    }
}