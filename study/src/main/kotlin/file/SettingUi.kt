package file

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import log.myPrintln
import util.MyUtil
import java.awt.FileDialog

@Composable
@Preview
fun settingUi() {
    // 构建一个滚动列表
    val scrollState = rememberScrollState()
    Column(
        Modifier.verticalScroll(scrollState).fillMaxSize()
    ) {
        subItem("Save File Path") {
            Column {
                Row {
                    Text("Path: ", fontSize = subUiFontSize)
                    Text(MyUtil.getDownloadDir(), fontSize = subUiFontSize)
                }
                // 选择目录按钮
                Button(
                    onClick = {
                        val dialog = FileDialog(null as ComposeWindow?)
                        dialog.isVisible = true
                        val dir = dialog.directory
                        val file = dialog.file
                        myPrintln("dir:${dir},file:${file}")
                    },
                ) {
                    Text("更改目录")
                }
            }
        }
        subItem("Language") {
            var expanded by remember { mutableStateOf(false) }
            var selectedIndex by remember { mutableStateOf(0) }
            val items = listOf("Chinese", "English", "Japanese")
            Box(modifier = Modifier.fillMaxSize()) {
                Row (
                    Modifier.clickable(onClick = { expanded = true })
                ){
                    Text(
                        text = items[selectedIndex],
                        fontSize = subUiFontSize,
                    )
                    // 下拉箭头
                    Text(
                        text = "▼",
                        fontSize = subUiFontSize,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    items.forEachIndexed { index, item ->
                        DropdownMenuItem(onClick = {
                            selectedIndex = index
                            expanded = false
                        }) {
                            Text(text = item)
                        }
                    }
                }
            }
        }
        subItem("Theme") {
            Row (
                // 剧中
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.offset(y = (-16).dp)
            ){
                Text("Mode: ", fontSize = subUiFontSize)
                var themeMode by remember { mutableStateOf(false) }
                Switch(
                    checked = themeMode,
                    onCheckedChange = {
                        themeMode = it
                    }
                )
            }
        }
    }

}

var subUiFontSize = TextUnit(20f, TextUnitType.Sp)


@Composable
inline fun subItem(title: String, ui: () -> Unit) {
    var offsetX = 8.dp
    Column {
        Text(
            title,
            // 设置字体大小
            fontSize = TextUnit(26f, TextUnitType.Sp),
            modifier = Modifier.offset(x = offsetX, y = offsetX)
        )
        Spacer(modifier = Modifier.height(offsetX))
        Box(
            Modifier.offset(x = offsetX * 3)
        ) {
            ui()
        }
    }

}