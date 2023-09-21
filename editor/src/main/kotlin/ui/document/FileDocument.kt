package ui.document

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import open.FileUtil
import theme.Theme

// id 实际为文件路径
class FileDocument(val id: String) : DefaultDocument() {
    override fun id(): String {
        return id
    }

    override fun title(): String {
        return FileUtil.getFileOrDirName(id)
    }

    @Composable
    override fun ui() {

        val file = FileUtil.loadFile(id)
        val code = file.trimIndent()

        val whiteStyle = SpanStyle(color = Color.White)
        val stringStyle = SpanStyle(color = Color.Red)
        val annotatedCode = buildAnnotatedString {
            append(code)
            addStyle(stringStyle, start = 0, end = 3)
            addStyle(whiteStyle, start = 3, end = code.length)
        }

        val textFieldValue = mutableStateOf(TextFieldValue(annotatedCode))
        val lineNumber = FileUtil.loadFileLine(id).size.toString()
        val width = (lineNumber.length * 8).dp
        // 构建一个滚动列表
        Row(Modifier.fillMaxSize()) {
            Column(modifier = Modifier.width(width)) {
                repeat(lineNumber.toInt()) {
                    Row {
                        Text(
                            "${it + 1}",
                            color = Theme.getInstance().selectedColor,
                            modifier = Modifier.width(width),
                            textAlign = TextAlign.End
                        )
                    }
                }
            }

            Spacer(Modifier.width(8.dp))
            TextField(
                textFieldValue.value,

                shape = TextFieldDefaults.OutlinedTextFieldShape
                ,onValueChange = {
                    textFieldValue.value = it
                }, modifier = Modifier.fillMaxSize().background(Theme.getInstance().darkGery)
            )
        }

    }


}