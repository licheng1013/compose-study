package editor.tool

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.input.pointer.pointerInput
import editor.ui.Ui

class Tool(private var name: String) : Ui {
    @Composable
    override fun ui() {
        val interactionSource = remember { MutableInteractionSource() }
        val isHovered by interactionSource.collectIsHoveredAsState()

        Box(
            modifier = Modifier.background(if (isHovered) Color(53, 116, 240) else Color.Transparent)
                .pointerHoverIcon(icon = PointerIcon.Hand).pointerInput(Unit) {
                detectTapGestures(onTap = {
                    println("Tool")
                })
            }.hoverable(interactionSource = interactionSource)
        ) {
            Text(
                name,
                color = fontColor
            )
        }

    }
}