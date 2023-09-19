package editor.window.impl

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import editor.theme.Theme
import editor.window.DefaultWindow
import editor.window.WindowPosition

class FileWindow : DefaultWindow() {
    override fun id(): String {
        return "FILE"
    }

    @Composable
    override fun windowUi() {
        // 构建一个滚动列表
        Box(modifier = Modifier.fillMaxSize()){
            val scrollState = rememberScrollState()
            Box {
                Column(
                    Modifier.verticalScroll(scrollState).fillMaxSize()
                        .background(color = Theme.lightGery)
                ) {
                    repeat(1000) {
                        Text("Hello File #$it", color = Theme.fontColor)
                    }
                }
            }
            VerticalScrollbar(
                adapter = rememberScrollbarAdapter(scrollState),
                Modifier.align(Alignment.CenterEnd),
                style = Theme.scrollbarStyle()
            )
        }
    }

    /**
     *  窗口位置
     */
    override fun position(): WindowPosition {
        return WindowPosition.LEFT_TOP
    }

    override fun color(): Color {
        return Color(83,167,197)
    }
}