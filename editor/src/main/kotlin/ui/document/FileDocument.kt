package ui.document

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
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

    val textFieldValue = mutableStateOf("")

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
        textFieldValue.value = annotatedCode.toString()


        val lineNumber = FileUtil.loadFileLine(id).size.toString()
        val width = (lineNumber.length * 8).dp
        val lineStr = StringBuilder()
        repeat(lineNumber.toInt()) {
            lineStr.append("${it + 1}\n")
        }


        // 构建一个滚动列表
        Row(Modifier.fillMaxSize()) {
            Text(
                lineStr.toString(),
                color = Theme.getInstance().selectedColor,
                modifier = Modifier.width(width),
                textAlign = TextAlign.End
            )
            Spacer(Modifier.width(8.dp))
            TextField(
                textFieldValue.value,
                colors = colors(),
                onValueChange = {
                    textFieldValue.value = it
                    println(textFieldValue.value.length)
                },
                modifier = Modifier.fillMaxSize().background(Theme.getInstance().darkGery)
            )
//            Text(annotatedCode, color = Theme.getInstance().fontColor)
        }

    }

    companion object {

        @Composable
        fun colors(): TextFieldColors {
            return TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.White,
                textColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        }


    }

}