package demo

import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
fun SidebarDemo() {
    // 定义标签列表和内容列表
    val tabs = listOf("水平垂直", "顶部导航", "设置")
    //val contents = listOf(RowDemo(), TabDemo(), Text("Ok"))
    // 定义一个状态变量来存储当前选中的标签索引，默认为0
    val selectedIndex = remember { mutableStateOf(0) }

    Row {
        Box(modifier = Modifier.weight(0.2f)) {
            Column {
                tabs.forEachIndexed { index, e ->
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            println("点击了:${e}")
                        }) {
                        Text(e)
                        selectedIndex.value = index
                    }
                }
            }
        }
        Box(modifier = Modifier.weight(0.8f).fillMaxSize().background(color = Color.Yellow)) {//容器
            // 使用 when 表达式来根据 selectedIndex 的值来显示对应的内容，也可以用 if-else 语句实现同样的效果
            when (selectedIndex.value) {
                0 -> Button(onClick = {}){ Text("按钮") }
                1 -> TabDemo()
                2 -> Text("Ok")
            }

        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        SidebarDemo()
    }
}