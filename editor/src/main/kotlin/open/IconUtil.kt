package open

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import theme.ThemeIcon

object IconUtil {
    @Composable
    fun defaultIcon(path: String = ThemeIcon.getInstance().box, color: Color = Color.White, size: Int = 16) {
        Image(
            painter = painterResource(path),
            contentDescription = null,
            modifier = Modifier
                .size(size.dp),
            colorFilter = ColorFilter.tint(color)
        )
    }

    @Composable
    fun icon(path: String = ThemeIcon.getInstance().box, color: Color? = null, size: Int = 16) {
        Image(
            painter = painterResource(path),
            contentDescription = null,
            modifier = Modifier
                .size(size.dp),
            colorFilter = if (color == null) null else ColorFilter.tint(color)
        )
    }
}