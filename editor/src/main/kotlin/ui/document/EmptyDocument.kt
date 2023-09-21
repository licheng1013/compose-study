package ui.document

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import theme.Theme

class EmptyDocument : DefaultDocument() {
    override fun id(): String {
        return "EMPTY"
    }

    override fun title(): String {
        return "Empty"
    }

    @Composable
    override fun ui() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Not found document", color = Theme.getInstance().fontColor, fontSize = 36.sp)
        }
    }
}