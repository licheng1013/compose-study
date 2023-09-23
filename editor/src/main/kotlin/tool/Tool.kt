package tool

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import Editor
import data.DataManager
import open.FileUtil
import theme.Theme
import ui.Ui
import window.FileWindow

class Tool(private var name: String) : Ui {

    private val methodItems = listOf("Open Folder", "Exit")
    private val methodIndex = mutableStateOf(0)
    private var expanded = mutableStateOf(false)

    @Composable
    override fun ui() {
        val interactionSource = remember { MutableInteractionSource() }
        val isHovered by interactionSource.collectIsHoveredAsState()
        Box(
            modifier = Modifier.background(if (isHovered) Theme.getInstance().hoverColor else Color.Transparent)
                .padding(4.dp)
                .pointerHoverIcon(icon = PointerIcon.Hand).pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        //调用文件窗口
                        val singleDir = FileUtil.openSingleDir()
                        if (singleDir.isNotEmpty()) {
                            val dataManager = DataManager.getInstance()
                            dataManager.openPath = singleDir
                            dataManager.save()
                            Editor.editor.windowList.forEach {
                                if (it is FileWindow) {
                                    it.path.value = singleDir
                                    Editor.editor.openWindowById(it.id())
                                }
                            }
                        }
                    })
                }.hoverable(interactionSource = interactionSource)
        ) {
            Text(
                name,
                color = fontColor
            )
            DropdownMenu(
                modifier = Modifier.background(color = Theme.getInstance().lightGery),
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
            ) {
                methodItems.forEachIndexed { index, item ->
                    DropdownMenuItem(onClick = {
                        methodIndex.value = index
                        expanded.value = false
                    }, modifier = Modifier.pointerHoverIcon(icon = PointerIcon.Hand).height(30.dp).width(300.dp)) {
                        Text(text = item, color = Color.White, fontSize = 12.sp)
                    }
                }
            }
        }

    }
}