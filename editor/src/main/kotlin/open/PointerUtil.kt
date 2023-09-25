package open;

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput

object PointerUtil {
    fun onTap(onTap: (Offset) -> Unit = {}): Modifier {
        return Modifier.pointerInput(Unit) {
            detectTapGestures(onTap = onTap)
        }
    }

    /**
     * 带提示的按下
     */
    fun onTapWithTip(onTap: () -> Unit = {}): Modifier {
        return Modifier.clickable(onClick = onTap)
    }
}
