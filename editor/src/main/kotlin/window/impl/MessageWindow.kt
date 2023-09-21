package window.impl

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import theme.Theme
import window.DefaultWindow
import window.WindowPosition

class MessageWindow : DefaultWindow() {

    @Composable
    override fun windowUi() {
        Column(

        ) {
            repeat(1000) {
                Text("Message #$it", color = Theme.fontColor)
            }
        }
    }

    override fun id(): String {
        return "MESSAGE"
    }

    /**
     *  窗口位置
     */
    override fun position(): WindowPosition {
        return WindowPosition.LEFT_TOP
    }

    override fun color(): Color {
        return Color(109, 213, 128)
    }

    override fun icon(): ImageVector {
        return Icons.Default.Notifications
    }
}