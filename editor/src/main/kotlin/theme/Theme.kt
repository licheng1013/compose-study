package theme

import androidx.compose.foundation.LocalScrollbarStyle
import androidx.compose.foundation.ScrollbarStyle
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import java.awt.Cursor

class Theme {


    // 用于非主要区域 - 浅灰色
    val lightGery = Color(43, 45, 48)

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

    companion object {
        private var Theme = Theme()
        fun getInstance(): Theme {
            return Theme
        }

        fun setInstance(theme: Theme) {
            Theme = theme
        }
    }


    var dragHeight = 2
    var dragWidth = 4
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

    // 上下光标
    var nIcon: PointerIcon = PointerIcon(Cursor(Cursor.N_RESIZE_CURSOR))

    // 左右光标
    var wIcon: PointerIcon = PointerIcon(Cursor(Cursor.W_RESIZE_CURSOR))
}

