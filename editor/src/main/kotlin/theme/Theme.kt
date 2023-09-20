package theme

import androidx.compose.foundation.LocalScrollbarStyle
import androidx.compose.foundation.ScrollbarStyle
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class Theme {
    companion object {
        val fontColor = Color(255, 255, 255)
        /**
         * 浅灰色
         */
        val lightGery = Color(43, 45, 48)

        /**
         * 深灰色
         */
        val darkGery = Color(30, 31, 34)

        /**
         * hover color
         */
        val hoverColor = Color(65, 66, 69)

        /**
         * hover edit color
         */
        val hoverEditColor = Color(46, 67, 110)


        // selected color
        val selectedColor: Color get() = Color(78, 81, 87)

        // unselected color
        val unselectedColor = Color(43, 45, 48)


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
    }
}