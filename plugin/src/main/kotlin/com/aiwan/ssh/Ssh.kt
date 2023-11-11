package com.aiwan.ssh

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import java.awt.Scrollbar

fun main() = application {
    Window(
        title = "Ssh",
        onCloseRequest = ::exitApplication
    ) {
        ssh()

        val state = rememberDialogState(size = DpSize(400.dp, 220.dp))
        if (SshState.openConnectDialog.value) {
            DialogWindow(
                state = state,
                resizable = false,
                // 设置窗口大小
                onCloseRequest = {
                    SshState.openConnectDialog.value = false
                    println("close")
                },
                title = "连接Ssh",
            ) {
                sshConnectDialog()
            }
        }
    }


}

@Preview
@Composable
fun ssh() {
    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Text("Welcome to Ssh")

        Row(
            modifier = Modifier.height(50.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f), contentAlignment = Alignment.CenterStart
            ) {
                Text(SshState.currentPath.value)
            }
            textWithBtn("选择目录")
            Spacer(modifier = Modifier.width(8.dp))
            textWithBtn("上传文件")
        }

        Row {
            Box(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                ) {
                    textWithBtn("返回上级")
                    Spacer(modifier = Modifier.width(8.dp))
                    textWithBtn("主目录")
                    Spacer(modifier = Modifier.width(8.dp))
                    textWithBtn("根目录")
                }
            }
            textWithBtn("新建连接") {
                SshState.openConnectDialog.value = true
            }
            Spacer(modifier = Modifier.width(8.dp))
            textWithBtn("连接")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Box(modifier = Modifier.weight(1f)) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(SshState.currentFileList.size) { index ->
                    val info = SshState.currentFileList[index]
                    Box(modifier = Modifier
                        .clickable {
                            println("select ${info.name}")
                        }
                        .background(green200)
                        .border(1.dp, green500)
                        .fillMaxWidth()
                        .padding(8.dp)) {
                        Text(info.name)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}

@Composable
fun textWithBtn(text: String, click: () -> Unit = {}) {
    Box(modifier = Modifier
        .clickable { click() }
        .background(green200)
        .border(1.dp, green500)
        .padding(horizontal = 16.dp, vertical = 8.dp)) {
        Text(text)
    }
}


val green200 = Color(0xffa5d6a7)
val green500 = Color(0xff4caf50)
val green700 = Color(0xff388e3c)
val teal200 = Color(0xff80deea)