import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import open.IconUtil
import open.PointerUtil
import theme.Theme
import theme.ThemeIcon
import ui.Ui
import ui.document.DefaultDocument

class FileBar(
    private val doc: DefaultDocument,
    val documentId: MutableState<String>,
) : Ui {

    // font color
    private var fontSize = 16.sp

    @Composable
    override fun horizontalSpacer() {
        Spacer(
            modifier = Modifier.height(1.dp).fillMaxWidth().background(color = Color(53, 116, 240))
        )
    }

    @Composable
    override fun ui() {
        val fileName = doc.title()
        val length = fileName.length * 12
        val selectedFile = documentId.value == doc.id()
        Column(
            Modifier.width(length.dp).pointerHoverIcon(icon = PointerIcon.Hand).pointerInput(Unit) {
                detectTapGestures(onTap = {
                    documentId.value = doc.id()
                })
            },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    fileName,
                    color = if (selectedFile) selectedColor else fontColor,
                    textAlign = TextAlign.Center,
                    fontSize = fontSize
                )
                if (selectedFile) {
                    Spacer(Modifier.width(6.dp))

                    val interactionSource = remember { MutableInteractionSource() }
                    val isHovered by interactionSource.collectIsHoveredAsState()
                    Box(
                        PointerUtil.onTap {
                            Editor.editor.removeDocument(documentId.value)
                        }.hoverable(interactionSource).background(
                            if (isHovered) Theme.getInstance().selectedColor else Color.Transparent
                        )
                    ) {
                        IconUtil.defaultIcon(
                            ThemeIcon.getInstance().close, size = 10,
                        )
                    }
                }
            }
            // 水平线
            Spacer(modifier = Modifier.weight(1f))
            if (selectedFile) {
                horizontalSpacer()
            }
        }
    }

    val selectedColor = Color(0xffa5d6a7)
}