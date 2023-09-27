package com.aiwan.web

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.onDrag
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.aiwan.PluginUi
import com.aiwan.http.Http
import open.PointerUtil
import theme.Theme
import ui.Ui


object Web : PluginUi {


    private var windowSize: IntSize = IntSize.Zero;

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun box() {
        var offset by remember { mutableStateOf(Offset.Zero) }
        Box(
            Modifier.offset(
                x = offset.x.dp,
                y = offset.y.dp
            )
        ) {
            Box(
                modifier = border().onDrag {
                    offset += it
                    // check
                    if (offset.x < 0) {
                        offset = Offset(0f, offset.y)
                    }
                    if (offset.y < 0) {
                        offset = Offset(offset.x, 0f)
                    }
                    if (offset.x > windowSize.width-100) {
                        //println("offset: $offset, windowSize: $windowSize")
                        offset = Offset(windowSize.width.toFloat()-100, offset.y)
                    }
                    if (offset.y > windowSize.height-100) {
                        offset = Offset(offset.x, windowSize.height.toFloat()-100)
                    }
                }
                    .height(100.dp)
                    .width(100.dp)
                    .background(Color.Green)
                    .pointerHoverIcon(icon = PointerIcon.Hand)
            ) {
                Text("Hello Web")
            }
        }

    }


    @Composable
    override fun ui() {

        Row {
            Box(modifier = Modifier.weight(1f).fillMaxSize()) {
                ContextMenuArea(
                    items = {
                        listOf(
                            ContextMenuItem("创建Api") {

                            },
                        )
                    },
                ) {
                    Box(modifier = Modifier.fillMaxSize()
                        .onGloballyPositioned { layoutCoordinates ->
                            windowSize = layoutCoordinates.size
                        }
                    ) {
                        box()
                    }
                }
            }
            // 分割线
            Spacer(
                Modifier
                    .width(1.dp)
                    .fillMaxHeight()
                    .background(Color(69, 69, 71))
            )
            Box(modifier = Modifier.width(50.dp)) {
                // 滚动条
                val scrollStateY = rememberScrollState()
                Box(
                    Modifier.verticalScroll(scrollStateY).fillMaxSize()
                ) {
                    Column {
                        repeat(100) {
                            Row {
                                Spacer(modifier = Modifier.weight(1f))
                                Text("Api", modifier = Modifier.padding(4.dp))
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
                VerticalScrollbar(
                    adapter = rememberScrollbarAdapter(scrollStateY),
                    Modifier.align(Alignment.CenterEnd),
                )
            }
        }
    }
}

fun main() = application {
    Window(
        title = "Http Util",
        onCloseRequest = ::exitApplication
    ) {
        Web.ui()
    }
}
