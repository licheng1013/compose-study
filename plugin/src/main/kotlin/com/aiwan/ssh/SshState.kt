package com.aiwan.ssh

import androidx.compose.runtime.mutableStateOf

object SshState {
    val openConnectDialog = mutableStateOf(false)
    var currentPath = mutableStateOf("/usr/local/src/view/ssh")
    var currentRemotePath = mutableStateOf("/")
    var currentFileList = mutableListOf<FileInfo>()
    var hostInput = mutableStateOf("")
    var passwordInput = mutableStateOf("")

    init {
        repeat(100){
            val fileInfo = FileInfo()
            fileInfo.name = "file$it"
            currentFileList.add(fileInfo)
        }
    }
}

