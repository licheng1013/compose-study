package theme

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import open.PointerUtil
import java.awt.Cursor

class Theme {


    // 代码垂直线颜色
    val verticalLineColor = Color(57, 59, 64)

    // 用于非主要区域 - 浅灰色
    val lightGery = Color(43, 45, 48)

    // 提示颜色
    val tipColor = Color(57, 59, 64)

    // 用于主要区域 - 深灰色
    val darkGery = Color(30, 31, 34)

    // 选中颜色
    val selectedColor = Color(78, 81, 87)

    // 未选中颜色
    val unselectedColor = Color(43, 45, 48)

    // 字体颜色
    val fontColor = Color(255, 255, 255)

    // 鼠标移动上去颜色
    val hoverColor = Color(65, 66, 69)


    // 设置圆角
    fun border(shape: Int = 6): Modifier {
        return Modifier.border(
            1.dp, color = hoverColor,
            shape = RoundedCornerShape(shape.dp)
        )
    }

    @Composable
    fun tipPanel(tip: @Composable () -> Unit) {
        Column(
            getInstance().border()
                .background(getInstance().tipColor),
            verticalArrangement = Arrangement.Center,
        ) {
            tip()
        }
    }

    companion object {
        private var Theme = Theme()
        fun getInstance(): Theme {
            return Theme
        }

        fun setInstance(theme: Theme) {
            Theme = theme
        }


        @Composable
        fun textFieldColorsNo(): TextFieldColors {
            return TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.White,
                textColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        }

        @Composable
        fun textFieldColorsInput(): TextFieldColors {
            return TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.White,
                textColor = Color.White,
                focusedIndicatorColor = getInstance().selectedColor,
                unfocusedIndicatorColor = getInstance().selectedColor
            )
        }

    }


    var dragHeight = 2
    var dragWidth = 2
    var dragXColor = darkGery
    var dragYColor = darkGery


    @Composable
    fun scrollbarStyle(): ScrollbarStyle {
        return ScrollbarStyle(
            thickness = 8.dp,
            shape = RoundedCornerShape(0.dp),
            hoverDurationMillis = 1000,
            unhoverColor = Color(77, 78, 81),
            hoverColor = Color(92, 93, 94),
            minimalHeight = LocalScrollbarStyle.current.minimalHeight + 10.dp,
        )
    }


    @Composable
    fun dragY(y: (Float) -> Unit = {}) {
        Spacer(
            Modifier.fillMaxWidth().background(dragYColor).height(dragHeight.dp)
                .pointerHoverIcon(nIcon).pointerInput(Unit) {
                    detectDragGestures { _, dragAmount ->
                        y(dragAmount.y)
                    }
                })
    }

    @Composable
    fun dragX(x: (Float) -> Unit = {}) {
        Spacer(
            Modifier.fillMaxHeight().background(dragXColor).width(dragWidth.dp)
                .pointerHoverIcon(wIcon).pointerInput(Unit) {
                    detectDragGestures { _, dragAmount ->
                        x(dragAmount.x)
                    }
                })
    }

    @Composable
    inline fun scrollBar(compose: @Composable () -> Unit) {
        // 滚动条
        val scrollStateY = rememberScrollState()

        // 构建一个滚动列表
        Box(modifier = PointerUtil.onTap {
            Editor.closeContextMenu()
        }.fillMaxSize()) {
            Box(
                Modifier.verticalScroll(scrollStateY).fillMaxSize()
                    .background(color = getInstance().lightGery)
            ) {
                compose()
            }
            VerticalScrollbar(
                adapter = rememberScrollbarAdapter(scrollStateY),
                Modifier.align(Alignment.CenterEnd),
                style = getInstance().scrollbarStyle()
            )
        }
    }

    // 上下光标
    private var nIcon: PointerIcon = PointerIcon(Cursor(Cursor.N_RESIZE_CURSOR))

    // 左右光标
    private var wIcon: PointerIcon = PointerIcon(Cursor(Cursor.W_RESIZE_CURSOR))
}

