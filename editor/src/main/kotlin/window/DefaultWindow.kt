package window

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import Editor
import theme.Theme


/**
 * 窗口实现功能
 */
abstract class DefaultWindow : Window {

    private var selected = mutableStateOf(false)

    @Composable
    override fun ui() {

        selected.value = Editor.editor.leftWindowId.value == id()

        val interactionSource = remember { MutableInteractionSource() }
        val isHovered by interactionSource.collectIsHoveredAsState()
        IconButton(
            modifier = Modifier.background(color = if (selected.value || isHovered) Theme.selectedColor else Theme.unselectedColor
            )
                .pointerHoverIcon(icon = PointerIcon.Hand).hoverable(interactionSource)
                .height(30.dp),
            onClick = {
                Editor.editor.leftWindowId.value = id()
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

    @Composable
    abstract fun windowUi()
}