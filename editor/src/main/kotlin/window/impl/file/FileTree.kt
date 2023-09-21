package window.impl.file

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import open.FileUtil
import theme.Theme


@Composable
fun FileTree(file: String, offsetX: Int = 0) {
    if (!FileUtil.isDirectory(file)) {
        throw RuntimeException("file is not directory")
    }
    val selected = mutableStateOf(file)
    Column {
        Row {
            Spacer(Modifier.width(offsetX.dp))
            text(file, selected)
        }
        Row(Modifier.fillMaxHeight()) {
            Spacer(Modifier.width((offsetX + 8).dp))
            Column(modifier = Modifier.weight(1f)) {
                val list = FileUtil.listFile(file)
                list.forEach {
                    if (it.isDirectory) {
                        text(it.path, selected)
                    } else {
                        text(it.path, selected)
                    }
                }
            }
        }
    }
}


@Composable
fun text(path: String, selected: MutableState<String>) {
    var name = FileUtil.getFileOrDirName(path)
    var selectedColor = if (selected.value == path) Theme.getInstance().selectedColor else Theme.getInstance().lightGery
    Text(
        name,
        color = Theme.getInstance().fontColor,
        modifier = Modifier.pointerHoverIcon(PointerIcon.Hand)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        selected.value = path
                    }
                )
            }
            .background(selectedColor).fillMaxWidth()
    )
}