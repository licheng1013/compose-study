package file

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateSizeAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import log.myPrintln

@Composable
fun File() {
    // 头部高度
    val headerHeight = 50.dp
    val isHovering = remember { mutableStateOf(false) }

    Box {
        Column {
            Box(Modifier.height(headerHeight).fillMaxWidth().background(color = Color.White)) {
                Row(
                    // 剧中
                    Modifier.fillMaxSize().padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Button(
                        modifier = Modifier.run {
                            width(100.dp).height(40.dp)
                        },
                        onClick = {
                            isHovering.value = !isHovering.value
                            myPrintln("二维码${isHovering.value}")
                        }) {
                        Text("二维码")
                    }
                }
            }
            // 水平灰色分割线
            Spacer(
                modifier = Modifier.height(1.dp).fillMaxWidth().background(color = Color.Gray)
            )
            // 构建一个滚动列表
            val scrollState = rememberScrollState()
            Column(
                Modifier.verticalScroll(scrollState).fillMaxSize()
            ) {
                repeat(100) {
                    Text("Item #$it")
                }
            }
        }
    }

    // 二维码动画
    var qrCodeSize = 150f
    val boxSize by animateSizeAsState(if (isHovering.value) Size(qrCodeSize, qrCodeSize) else Size(0f, 0f)) {
        myPrintln("动画结束")
    }

    if (isHovering.value) {
        Box(
            // 向下偏移50dp
            Modifier.offset(
                y = headerHeight - 5.dp,
                x = 10.dp
            ).width(boxSize.width.dp).height(boxSize.height.dp).background(color = Color.White).border(
                width = 1.dp,
                color = Color.Gray
            )
        ) {
            Column {
                Text("Hello, world!")
            }
        }
    }

}