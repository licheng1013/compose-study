package ui.window

import androidx.compose.runtime.Composable

/**
 * 窗口
 */
interface Window {
    /**
     *  窗口位置
     */
    fun position(): WindowPosition

    @Composable
    fun ui()
}