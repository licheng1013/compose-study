package file

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp




object MyUi{
    @Composable
    fun  verticalSpacer() {
        // 垂直灰色分割线
        Spacer(
            modifier = Modifier.width(1.dp).fillMaxHeight().background(color = Color.Gray)
        )
    }
    // 水平线
    @Composable
    fun  horizontalSpacer() {
        // 水平线
        Spacer(
            modifier = Modifier.height(1.dp).fillMaxWidth().background(color = Color.Gray)
        )
    }
}