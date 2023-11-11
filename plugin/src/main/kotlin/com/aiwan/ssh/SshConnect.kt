package com.aiwan.ssh

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun sshConnectDialog() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        textWithCompose("主机", SshState.hostInput)
        Spacer(modifier = Modifier.height(8.dp))
        textWithCompose("密码", SshState.passwordInput)
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Spacer(modifier = Modifier.width(8.dp))
            textWithBtn("测试") {
                println("测试: ${SshState.hostInput.value} ${SshState.passwordInput.value}")
            }
            Spacer(modifier = Modifier.weight(1f))
            textWithBtn("确认") {
                SshState.openConnectDialog.value = false
                println("确认: ${SshState.hostInput.value} ${SshState.passwordInput.value}")
            }
            Spacer(modifier = Modifier.width(8.dp))
            textWithBtn("取消") {
                SshState.openConnectDialog.value = false
            }
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}


@Composable
fun textWithCompose(text: String, textState: MutableState<String>) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(8.dp))
        Box(modifier = Modifier.width(50.dp), contentAlignment = Alignment.Center) {
            Text(text = text)
        }
        Spacer(modifier = Modifier.width(8.dp))
        Box(
            modifier = Modifier
                .weight(1f)
                .height(55.dp)
                .border(1.dp, green200)
        ) {
            TextField(
                value = textState.value,
                singleLine = true,
                onValueChange = {
                    textState.value = it
                },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                // 密码
                visualTransformation = if (text == "密码") {
                    PasswordVisualTransformation()
                } else {
                    VisualTransformation.None
                },
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
    }
}