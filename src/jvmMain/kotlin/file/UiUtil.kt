package file

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun  verticalSpacer() {
    // 垂直灰色分割线
    Spacer(
        modifier = Modifier.width(1.dp).fillMaxHeight().background(color = Color.Gray)
    )
}