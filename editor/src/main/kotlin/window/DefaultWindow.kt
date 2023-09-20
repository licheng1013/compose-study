package window

import Editor
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
                color = if (selected.value || isHovered) Theme.selectedColor else Theme.unselectedColor
            )
                .pointerHoverIcon(icon = PointerIcon.Hand).hoverable(interactionSource)
                .height(30.dp),
            onClick = {
                Editor.editor.offAll(position(), id())
                selected.value = !selected.value
                val id = if (selected.value) id() else ""

                when(position()){
                    WindowPosition.RIGHT_TOP->{
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

    @Composable
    abstract fun windowUi()
}