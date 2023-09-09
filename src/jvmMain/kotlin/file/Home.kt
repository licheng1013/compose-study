package file

import androidx.compose.desktop.ui.tooling.preview.Preview
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

// 定义一个compose函数

@Composable
@Preview
fun home() {
    val tabs = listOf("文件列表", "设置")
    val selectedIndex = remember { mutableStateOf(0) }

    Row {
        Box(
            modifier = Modifier.width(150.dp).fillMaxHeight().background(color = Color.White).padding(horizontal = 6.dp)
        ) {
            Column() {
                tabs.forEachIndexed { index, e ->
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            myPrintln("点击了:${e}")
                            selectedIndex.value = index
                        }) {
                        Text(e)
                    }
                }
            }
        }
        verticalSpacer()
        Box(modifier = Modifier.fillMaxSize()) {//容器
            when (selectedIndex.value) {
                0 -> File()
                1 -> FileSetting()
            }
        }
    }
}