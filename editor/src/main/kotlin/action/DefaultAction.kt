package action

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.DataManager
import open.FileUtil
import theme.Theme
import window.FileWindow

abstract class DefaultAction : Action {
    private var expanded = mutableStateOf(false)


    override fun name(): String {
        return "Empty"
    }

    @Composable
    override fun ui() {
        val interactionSource = remember { MutableInteractionSource() }
        val isHovered by interactionSource.collectIsHoveredAsState()


        Box(
            modifier = Modifier.background(if (isHovered) Theme.getInstance().hoverColor else Color.Transparent)
                .padding(4.dp)
                .pointerHoverIcon(icon = PointerIcon.Hand).pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        expanded.value = !expanded.value
                    })
                }.hoverable(interactionSource = interactionSource)
        ) {

            Text(
                name(),
                color = fontColor
            )

            if (actionList().isNotEmpty()) {
                DropdownMenu(
                    modifier = Modifier.background(color = Theme.getInstance().lightGery),
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false },
                ) {
                    actionList().forEachIndexed { index, item ->
                        DropdownMenuItem(onClick = {
                            expanded.value = false
                            actionList[index].action()
                        }, modifier = Modifier.pointerHoverIcon(icon = PointerIcon.Hand).height(30.dp).width(300.dp)) {
                            Text(text = item.name(), color = Color.White, fontSize = 12.sp)
                        }
                    }
                }
            }


        }
    }

    val actionList = mutableListOf<DefaultAction>()
    open fun actionList(): MutableList<DefaultAction> {
        return actionList
    }


    override fun action() {
        println("Not implement")
    }
}