package demo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
fun TabRowDemo() {
    // 定义标签列表和内容列表
    val tabs = listOf("Tab1", "Tab2", "Tab3")
    val contents = listOf("Tab1", "Tab2", "Tab3")

    // 定义一个状态变量来存储当前选中的标签索引，默认为0
    val selectedIndex = remember { mutableStateOf(0) }

    Column {
        // 使用 TabRow 组件来创建标签导航，传入 selectedIndex 和 onTabSelected 参数
        TabRow(selectedTabIndex = selectedIndex.value,
            // 可以通过 backgroundColor 参数来自定义背景颜色，还有其他参数可以调整样式和动画效果
            backgroundColor = Color.LightGray) {
            // 使用 Tab 组件来定义每个标签的样式和内容，例如文字、图标等
            tabs.forEachIndexed { index, title ->
                Tab(selected = index == selectedIndex.value,
                    modifier = Modifier.height(50.dp),
                    onClick = { selectedIndex.value = index }) {
                    Text(text = title)
                }
            }
        }

        // 使用 when 表达式来根据 selectedIndex 的值来显示对应的内容，也可以用 if-else 语句实现同样的效果
        when (selectedIndex.value) {
            0 -> Text(text = contents[0])
            1 -> Text(text = contents[1])
            2 -> Text(text = contents[2])
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        TabRowDemo()
    }
}