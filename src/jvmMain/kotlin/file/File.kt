package file

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateSizeAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
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
            Row(
                Modifier.height(headerHeight).background(color = Color.White)
                    .padding(horizontal = 10.dp),
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
                // 填充
                Box(Modifier.width(16.dp))
                Text("连接状态:已连接")
            }
            // 水平灰色分割线
            Spacer(
                modifier = Modifier.height(1.dp).fillMaxWidth().background(color = Color.Gray)
            )
            // 构建一个滚动列表
            val scrollState = rememberScrollState()
            Box {
                Box {
                    Column(
                        Modifier.verticalScroll(scrollState)
                    ) {
                        repeat(1000) {
                            Text("index #$it",Modifier.background(color = Color.Yellow).fillMaxWidth())
                        }
                    }
                }
                VerticalScrollbar(
                    adapter = rememberScrollbarAdapter(scrollState),
                    Modifier.align(Alignment.CenterEnd)
                )
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