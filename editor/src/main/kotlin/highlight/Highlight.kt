package highlight

import androidx.compose.ui.text.AnnotatedString

interface Highlight {

    fun highlight(code: String): AnnotatedString

    fun suffix(): String

    companion object {
        private val highlightList = mutableListOf(XmlHighlight, JavaHighlight)

        fun highlight(code: String, fileType: String): AnnotatedString {
            for (highlight in highlightList) {
                if (fileType.endsWith(highlight.suffix())) {
                    return highlight.highlight(code)
                }
            }
            return DefaultHighlight().highlight(code)
        }
    }


}