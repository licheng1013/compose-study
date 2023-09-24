package ui.window

import Editor
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import open.PointerUtil
import theme.Theme


/**
 * 窗口实现功能
 */
abstract class DefaultWindow : Window {

    var selected = mutableStateOf(false)

    @Composable
    override fun ui() {

        val interactionSource = remember { MutableInteractionSource() }
        val isHovered by interactionSource.collectIsHoveredAsState()
        IconButton(
            modifier = Modifier.background(
                color = if (selected.value || isHovered) Theme.getInstance().selectedColor else Theme.getInstance().unselectedColor
            )
                .pointerHoverIcon(icon = PointerIcon.Hand).hoverable(interactionSource)
                .height(30.dp),
            onClick = {
                Editor.editor.offAll(position(), id())
                selected.value = !selected.value
                val id = if (selected.value) id() else ""

                when (position()) {
                    WindowPosition.RIGHT_TOP -> {
                        Editor.editor.rightTopWindowId.value = id
                    }

                    WindowPosition.LEFT_TOP -> {
                        Editor.editor.leftTopWindowId.value = id
                    }

                    WindowPosition.LEFT_BOTTOM -> {
                        Editor.editor.leftBottomWindowId.value = id
                    }

                    WindowPosition.RIGHT_BOTTOM -> {

                    }
                }

            }) {
            Icon(icon(), contentDescription = desc(), tint = color())
        }
    }

    open fun color(): Color {
        return Color.White
    }

    open fun icon(): ImageVector {
        return Icons.Default.Home
    }

    open fun desc(): String {
        return ""
    }

    abstract fun id(): String

    /**
     * 已经实现了窗口的滚动
     */
    @Composable
    abstract fun windowUi()

    val scrollStateY = ScrollState(0)

    @Composable
    open fun layout() {
        // 构建一个滚动列表
        Box(modifier = PointerUtil.onTap {
            Editor.editor.closeContextMenu()
        }.fillMaxSize()) {
            Box(
                Modifier.verticalScroll(scrollStateY).fillMaxSize()
                    .background(color = Theme.getInstance().lightGery)
            ) {
                windowUi()
            }
            VerticalScrollbar(
                adapter = rememberScrollbarAdapter(scrollStateY),
                Modifier.align(Alignment.CenterEnd),
                style = Theme.getInstance().scrollbarStyle()
            )
        }
    }
}