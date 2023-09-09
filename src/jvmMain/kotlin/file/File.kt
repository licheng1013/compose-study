package file

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateSizeAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import log.myPrintln

@Composable
fun File() {
    // 头部高度
    val headerHeight = 50.dp
    val isHovering = remember { mutableStateOf(false) }
    // 设备列表
    val tabs = remember {  mutableStateListOf("当前设备") }
    val selectedIndex = remember { mutableStateOf(0) }

    Box(
        Modifier.pointerInput(Unit) {
            detectTapGestures(
                onTap = {
                    // 关闭二维码
                    isHovering.value = false
                }
            )
        }
    ){
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
                        if (isHovering.value) {
                            tabs.add("设备${tabs.size}")
                        }
                    }) {
                    Text("二维码")
                }
                // 填充
                Box(Modifier.width(16.dp))
                Text("连接状态:已连接")
                Box(Modifier.width(16.dp))
                // 选择文件按钮
                Button(
                    modifier = Modifier.run {
                        width(100.dp).height(40.dp)
                    },
                    onClick = {
                        myPrintln("选择文件")


                    }) {
                    Text("选择文件")
                }
            }
            // 水平灰色分割线
            Spacer(
                modifier = Modifier.height(1.dp).fillMaxWidth().background(color = Color.Gray)
            )

            Row {
                Column(
                    Modifier.width(125.dp).background(color = Color.White)
                        .padding(horizontal = 6.dp)
                ) {
                    // 构建 tabs
                    tabs.forEachIndexed { index, title ->
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                myPrintln("点击:${title}")
                                selectedIndex.value = index
                            }) {
                            Text(title)
                        }
                    }
                }
                verticalSpacer()
                Box {
                    // 构建一个滚动列表
                    val scrollState = rememberScrollState()
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