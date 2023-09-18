package editor.action

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import editor.Editor
import editor.ui.Ui

abstract class Action : Ui {
    // selected color
    private val selectedColor: Color get() = Color(78, 81, 87)
    // unselected color
    private val unselectedColor: Color get() = Color(43, 45, 48)
    var selected: Boolean = false
    var index: Int = 0
    var editor: Editor ?= null

    abstract fun icon() : ImageVector
    abstract fun desc() : String
    open fun actionColor(): Color {
        return Color.White
    }

    @Composable
    override fun ui() {
        IconButton(
            modifier = Modifier.background(color = if (selected && editor!!.leftActionShow.value) selectedColor else unselectedColor)
                .pointerHoverIcon(icon = PointerIcon.Hand)
                .height(30.dp).padding(horizontal = 5.dp),
            onClick = {
                if (editor!!.leftActionShow.value && index == editor!!.leftActionIndex.value) {
                    editor!!.leftActionShow.value = false
                }else {
                    editor!!.leftActionIndex.value = index
                    editor!!.leftActionShow.value = true
                }
            }) {
            Icon(icon(),contentDescription = desc(),tint = actionColor())
        }
    }

}