package window.file

import Editor
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.background
import androidx.compose.foundation.contextMenuOpenDetector
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
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
import ui.Ui
import ui.document.FileDocument


class FileTree(var file: String) : Ui {

    companion object {
        private const val OFFSET_X = 8
    }

    val selected = mutableStateOf("")
    val openList = mutableStateListOf<String>()
    val defaultOpen = "0${file}"

    init {
        selected.value = defaultOpen
        openList.add(defaultOpen)
    }

    @Composable
    override fun ui() {
        if (file.isNotEmpty()) {
            fileTree(file)
        }
    }

    @Composable
    fun fileTree(file: String, offsetX: Int = 0) {
        if (!FileUtil.isDirectory(file)) {
            throw RuntimeException("file is not directory")
        }
        Column {

            if (offsetX == 0) {
                Row {
                    text(file, selected, offsetX)
                }
            }

            if (openList.contains(defaultOpen)) {
                Row(Modifier.fillMaxHeight()) {
                    Column(modifier = Modifier.weight(1f)) {
                        val list = FileUtil.listFile(file)
                        val offset = offsetX + OFFSET_X
                        list.forEach {
                            if (it.isDirectory) {
                                text(it.path, selected, offset)
                                if (openList.contains("$offset${it.path}")) {
                                    fileTree(it.path, offset)
                                }
                            } else {
                                text(it.path, selected, offset)
                            }
                        }
                    }
                }
            }

        }
    }


    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun text(path: String, selected: MutableState<String>, offsetX: Int) {
        var name = FileUtil.getFileOrDirName(path)

        val index = "${offsetX}${path}"
        val select = selected.value == index
        val isDirectory = FileUtil.isDirectory(path)
        var selectedColor = if (select) Theme.getInstance().selectedColor else Theme.getInstance().lightGery
        TooltipArea(
            tooltip = {
                Theme.tipPanel{
                    Text(path, color = Theme.getInstance().fontColor, modifier = Modifier.padding(8.dp))
                }
            },
        ) {
            Row(modifier = Modifier.pointerHoverIcon(PointerIcon.Hand)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            selected.value = index
                            //println("Click $index")
                            if (isDirectory) {
                                if (openList.contains(index)) {
                                    openList.remove(index)
                                } else {
                                    openList.add(index)
                                }
                            } else {
                                Editor.editor.addDocumentWithSelect(FileDocument(path))
                            }
                        }
                    )

                }.contextMenuOpenDetector {
                    Editor.editor.openContextMenu(it)
                }
                .height(26.dp)
                .background(selectedColor).fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(Modifier.width(3.dp))
                Spacer(Modifier.width(offsetX.dp))

                // 是否已经打开
                val isOpen = openList.contains(index)

                if (isDirectory) {
                    if (isOpen) {
                        IconUtil.icon(ThemeIcon.getInstance().arrowDown)
                    } else {
                        IconUtil.icon(ThemeIcon.getInstance().arrowRight)
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

    }
}



