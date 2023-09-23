package ui.document

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import theme.Theme

abstract class DefaultDocument : DocumentUi {
    // 构建一个滚动列表
    val scrollStateY = ScrollState(0)
    @Composable
    override fun layout() {
        Box {
            Box(Modifier.verticalScroll(scrollStateY).fillMaxWidth()) {
                ui()
            }
            VerticalScrollbar(
                adapter = rememberScrollbarAdapter(scrollStateY),
                Modifier.align(Alignment.CenterEnd),
                style = Theme.getInstance().scrollbarStyle()
            )
        }
    }

}