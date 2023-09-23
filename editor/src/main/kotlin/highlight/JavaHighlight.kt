package highlight;

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString

class JavaHighlight : Highlight {

    override fun highlight(code: String): AnnotatedString {
        val whiteStyle = SpanStyle(color = Color.White)
        val stringStyle = SpanStyle(color = Color(204, 108, 61))
        var annotatedCode = buildAnnotatedString {
            append(code)
            addStyle(stringStyle, start = 0, end = 3)
            addStyle(whiteStyle, start = 3, end = code.length)
        }
        return annotatedCode
    }

    override fun suffix(): String {
        return ".java"
    }

}


