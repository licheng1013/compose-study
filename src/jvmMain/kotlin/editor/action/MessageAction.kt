package editor.action

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import editor.Editor

class MessageAction() : Action() {
    override fun icon(): ImageVector {
        return Icons.Default.Notifications
    }

    override fun desc(): String {
        return "message"
    }

    override fun actionColor(): Color {
        return Color(234, 63, 122)
    }
}