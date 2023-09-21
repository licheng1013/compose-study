package window.file

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import open.FileUtil
import open.IconUtil
import theme.Theme
import theme.ThemeIcon


@Composable
fun FileTree(file: String, offsetX: Int = 0) {
    if (!FileUtil.isDirectory(file)) {
        throw RuntimeException("file is not directory")
    }
    val selected = mutableStateOf(file)
    Column {
        Row {
            text(file, selected, offsetX)
        }
        Row(Modifier.fillMaxHeight()) {
            Column(modifier = Modifier.weight(1f)) {
                val list = FileUtil.listFile(file)
                list.forEach {
                    text(it.path, selected, offsetX + 8)
                }
            }
        }
    }
}


@Composable
fun text(path: String, selected: MutableState<String>, offsetX: Int) {
    var name = FileUtil.getFileOrDirName(path)

    val select = selected.value == path
    val isDirectory = FileUtil.isDirectory(path)
    var selectedColor = if (select) Theme.getInstance().selectedColor else Theme.getInstance().lightGery

    Row(modifier = Modifier.pointerHoverIcon(PointerIcon.Hand)
        .pointerInput(Unit) {
            detectTapGestures(
                onTap = {
                    selected.value = path
                }
            )
        }
        .height(26.dp)
        .background(selectedColor).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(Modifier.width(3.dp))
        Spacer(Modifier.width(offsetX.dp))

        if (isDirectory) {
            when (select) {
                true -> {
                    IconUtil.icon(ThemeIcon.getInstance().arrowDown)
                }

                false -> {
                    IconUtil.icon(ThemeIcon.getInstance().arrowRight)
                }
            }
        } else {
            IconUtil.icon(ThemeIcon.getInstance().box, Color.Transparent)
        }


        when (isDirectory) {
            true -> {
                IconUtil.icon(ThemeIcon.getInstance().folder)
            }

            false -> {
                IconUtil.icon(ThemeIcon.getInstance().file)
            }
        }
        Spacer(Modifier.width(3.dp))
        Text(
            name,
            color = Theme.getInstance().fontColor,
        )
    }

}