package open;

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput

class PointerUtil {
    companion object {
        fun onTap(onTap: (Offset) -> Unit = {}): Modifier {
            return Modifier.pointerInput(Unit) {
                detectTapGestures(onTap = onTap)
            }
        }
    }
}
