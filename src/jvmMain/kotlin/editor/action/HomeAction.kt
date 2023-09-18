package editor.action

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

class HomeAction : Action() {
    override fun icon(): ImageVector {
        return Icons.Default.Home
    }

    override fun desc(): String {
        return "home"
    }

    override fun actionColor(): Color {
        return Color(77, 182, 172)
    }
}