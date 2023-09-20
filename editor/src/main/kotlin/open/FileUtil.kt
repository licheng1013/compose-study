package open

import androidx.compose.ui.awt.ComposeWindow
import java.awt.FileDialog

class FileUtil {

    companion object{
        val  home: String = System.getProperty("user.home")

        fun openFile() {
            val dialog = FileDialog(null as ComposeWindow?)
            dialog.isVisible = true
            val dir = dialog.directory
            val file = dialog.file
            println("dir:${dir},file:${file}")
        }

        fun openSingleDir(){
            val dialog = FileDialog(null as ComposeWindow?)
            // 只允许目录


            dialog.isVisible = true
            val dir = dialog.directory
            val file = dialog.file
        }
    }
}