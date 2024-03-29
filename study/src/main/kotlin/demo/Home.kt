package demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import log.myPrintln

@Composable
fun sidebarDemo() {
    // 定义标签列表和内容列表
    val tabs = listOf("水平垂直", "顶部导航", "登入实例", "顶部组件")
    //val contents = listOf(RowDemo(), TabDemo(), Text("Ok"))
    // 定义一个状态变量来存储当前选中的标签索引，默认为0
    val selectedIndex = remember { mutableStateOf(0) }

    Row {
        Box(modifier = Modifier.width(150.dp)) {
            Column() {
                tabs.forEachIndexed { index, e ->
                    Button(
                        modifier = Modifier.fillMaxWidth().padding(8.dp, 0.dp),
                        onClick = {
                            myPrintln("点击了:${e}")
                            selectedIndex.value = index
                        }) {
                        Text(e)
                    }
                }
            }
        }
        // 垂直黑色分割线
        Spacer(
            modifier = Modifier.width(1.dp).fillMaxHeight().background(color = Color.Gray)
        )

        Box(modifier = Modifier.weight(0.8f).fillMaxSize()) {//容器
            // 使用 when 表达式来根据 selectedIndex 的值来显示对应的内容，也可以用 if-else 语句实现同样的效果
            when (selectedIndex.value) {
                0 -> LayoutDemo()
                1 -> TabRowDemo()
                2 -> LoginDemo()
                3 -> TopAppBarDemo()
            }
        }
    }
}