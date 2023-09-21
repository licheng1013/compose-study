package ui.document

import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import theme.Theme

abstract class DefaultDocument : DocumentUi {

    @Composable
    override fun layout() {
        Box {
            // 构建一个滚动列表
            val scrollState = rememberScrollState()
            Box(Modifier.verticalScroll(scrollState).fillMaxWidth()) {
                ui()
            }
            VerticalScrollbar(
                adapter = rememberScrollbarAdapter(scrollState),
                Modifier.align(Alignment.CenterEnd),
                style = Theme.getInstance().scrollbarStyle()
            )
        }
    }


}