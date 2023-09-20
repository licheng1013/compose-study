import Editor.Companion.editor
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ui.Ui

class FileBar(
    private val fileName: String,
    private val selectedFile: Boolean = false,
    private val index: Int
) : Ui {

    // font color
    private var fontSize = 18.sp

    @Composable
    override fun horizontalSpacer() {
        Spacer(
            modifier = Modifier.height(1.dp).fillMaxWidth().background(color = Color(53, 116, 240))
        )
    }

    @Composable
    override fun ui() {
        var lenght = fileName.length * 12
        Column(
            Modifier.width(lenght.dp).pointerHoverIcon(icon = PointerIcon.Hand).pointerInput(Unit) {
                detectTapGestures(onTap = {
                    editor.selectedFileIndex.value = index
                })
            },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                fileName,
                color = if (selectedFile) selectedColor else fontColor,
                textAlign = TextAlign.Center,
                fontSize = fontSize
            )
            // 水平线
            Spacer(modifier = Modifier.weight(1f))
            if (selectedFile) {
                horizontalSpacer()
            }
        }
    }

    val selectedColor = Color(0xffa5d6a7)
}