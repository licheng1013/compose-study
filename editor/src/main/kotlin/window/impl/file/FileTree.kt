package window.impl.file

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import open.FileUtil
import theme.Theme


@Composable
fun FileTree(file: String, offsetX: Int = 0) {
    if (!FileUtil.isDirectory(file)) {
        throw RuntimeException("file is not directory")
    }
    val name = FileUtil.getFileOrDirName(file)
    Column {
        Row {
            Spacer(Modifier.width(offsetX.dp))
            Text(name, color = Theme.fontColor)
        }
        Row(Modifier.fillMaxHeight()) {
            Spacer(Modifier.width((offsetX + 8).dp))
            Column(modifier = Modifier.weight(1f)) {
                val list = FileUtil.listFile(file)
                list.forEach {
                    if (it.isDirectory) {
                        Text(it.name, color = Theme.fontColor)
                    } else {
                        Text(it.name, color = Theme.fontColor)
                    }
                }
            }
        }

    }

}