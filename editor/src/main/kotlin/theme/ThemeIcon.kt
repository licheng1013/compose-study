package theme

class ThemeIcon {
    val message = "/icon/message.png"
    val close = "/icon/close.png"
    var folder = "/icon/folder.png"
    var file = "/icon/file.png"
    var box = "/icon/box.png"
    var arrowDown = "/icon/arrow_down.png"
    var arrowRight = "/icon/arrow_right.png"
    var settings = "/icon/settings.png"
    var plugin = "/icon/plugin.png"

    companion object {
        private var ThemeIcon = ThemeIcon()
        fun getInstance(): ThemeIcon {
            return ThemeIcon
        }

        fun setInstance(themeIcon: ThemeIcon) {
            ThemeIcon = themeIcon
        }
    }
}