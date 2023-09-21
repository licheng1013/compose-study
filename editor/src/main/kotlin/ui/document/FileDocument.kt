package ui.document

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
        Column {
            repeat(100) {
                Text("Hello body $it", color = Theme.getInstance().fontColor)
            }
        }
    }
}