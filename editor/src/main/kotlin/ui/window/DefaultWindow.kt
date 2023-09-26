package ui.window

import Editor
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import open.IconUtil
import open.PointerUtil
import theme.Theme
import theme.ThemeIcon


/**
 * 窗口实现功能
 */
abstract class DefaultWindow : Window {

    var selected = mutableStateOf(false)

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun ui() {

        val interactionSource = remember { MutableInteractionSource() }
        val isHovered by interactionSource.collectIsHoveredAsState()
        TooltipArea({
            Theme.getInstance().tipPanel {
                Text(desc(), color = Theme.getInstance().fontColor, modifier = Modifier.padding(6.dp))
            }
        }) {
            IconButton(
                modifier = PointerUtil.onTap {
                    click()
                }.background(
                    color = if (selected.value || isHovered) Theme.getInstance().selectedColor else Theme.getInstance().unselectedColor
                )
                    .pointerHoverIcon(icon = PointerIcon.Hand).hoverable(interactionSource)
                    .height(30.dp),
                onClick = {
                    click()
                }) {
                icon()
            }
        }
    }

    // 强力打开，不管是否选中，都打开
    open fun click(force: Boolean = false) {
        Editor.offAll(position(), id())

        selected.value = !selected.value
        var id = if (selected.value) id() else ""

        if (force) {
            id = id()
        }

        when (position()) {
            WindowPosition.RIGHT_TOP -> {
                Editor.rightTopWindowId.value = id
            }

            WindowPosition.LEFT_TOP -> {
                Editor.leftTopWindowId.value = id
            }

            WindowPosition.LEFT_BOTTOM -> {
                Editor.leftBottomWindowId.value = id
            }

            WindowPosition.RIGHT_BOTTOM -> {

            }
        }
    }


    open fun iconColor(): Color {
        return Color.White
    }

    @Composable
    private fun icon() {
        IconUtil.icon(iconPath(), size = 16, color = iconColor())
    }

    open fun iconPath(): String {
        return ThemeIcon.getInstance().plugin
    }

    open fun desc(): String {
        return "Not Desc"
    }

    abstract fun id(): String

    /**
     * 已经实现了窗口的滚动
     */
    @Composable
    abstract fun windowUi()

    private val scrollStateY = ScrollState(0)

    @Composable
    open fun layout() {
        // 构建一个滚动列表
        Box(modifier = PointerUtil.onTap {
            Editor.closeContextMenu()
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