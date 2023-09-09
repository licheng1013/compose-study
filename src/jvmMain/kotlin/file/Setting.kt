package file

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FileSetting() {
    // 构建一个滚动列表
    val scrollState = rememberScrollState()
    Column(
        Modifier.verticalScroll(scrollState).fillMaxSize()) {
        repeat(100) {
            Text("Item #$it")
        }
    }

}