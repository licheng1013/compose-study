package open

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import theme.Theme
import theme.ThemeIcon

class IconUtil {
    companion object {
        @Composable
        fun icon(path: String = ThemeIcon.getInstance().box, color: Color = Color.White, size: Int = 16) {
            Image(
                painter = painterResource(path),
                contentDescription = null,
                modifier = Modifier
                    .size(size.dp),
                colorFilter = ColorFilter.tint(color)
            )
        }
    }
}