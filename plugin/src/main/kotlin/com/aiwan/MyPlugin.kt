package com.aiwan

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import plugin.DefaultPlugin
import theme.Theme
import ui.window.DefaultWindow
import ui.window.WindowPosition

class MyPlugin : DefaultPlugin() {
    override fun id(): String {
        return "com.aiwan.http"
    }

    override fun window(): ArrayList<DefaultWindow> {
        return arrayListOf(MyWindow())
    }
}

class MyWindow : DefaultWindow() {
    override fun id(): String {
        return "com.aiwan.http"
    }

    /**
     * 已经实现了窗口的滚动
     */
    @Composable
    override fun windowUi() {
        Text("HelloWorld", color = Theme.getInstance().fontColor)
    }

    /**
     *  窗口位置
     */
    override fun position(): WindowPosition {
        return WindowPosition.RIGHT_TOP
    }
}