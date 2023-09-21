package ui.document

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import theme.Theme

class FileDocument(val id: String) : DefaultDocument() {
    override fun id(): String {
        return id
    }

    override fun title(): String {
        return "Demo.kt"
    }

    @Composable
    override fun ui() {
        val code = """
        fun main() {
            val message = "Hello, world!"
            println(message)
        }
    """.trimIndent()

        val whiteStyle = SpanStyle(color = Color.White)
        val stringStyle = SpanStyle(color = Color.Red)
        val annotatedCode = buildAnnotatedString {
            append(code)
            addStyle(stringStyle, start = 0, end = 3)
            addStyle(whiteStyle, start = 3, end = code.length)
        }


        val lineNumber = "100"
        val width = (lineNumber.length * 8).dp
        Row(Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.width(width)) {
                repeat(100) {
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
            Box{
                Text(annotatedCode)
            }
        }
    }


}