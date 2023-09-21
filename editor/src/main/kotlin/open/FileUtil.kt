package open

import androidx.compose.ui.awt.ComposeWindow
import java.awt.FileDialog
import java.io.File
import javax.swing.JFileChooser


class FileUtil {

    companion object {
        val home: String = System.getProperty("user.home")

        fun openFile() {
            val dialog = FileDialog(null as ComposeWindow?)
            dialog.isVisible = true
            val dir = dialog.directory
            val file = dialog.file
            println("dir:${dir},file:${file}")
        }

        fun openSingleDir(): String {
            val fileChooser = JFileChooser()
            fileChooser.currentDirectory = File(home)
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY)
            val option = fileChooser.showOpenDialog(null as ComposeWindow?)
            if (option == JFileChooser.APPROVE_OPTION) {
                val file = fileChooser.selectedFile
                //println("dir:${file.absolutePath}")
                return file.absolutePath
            }
            return ""
        }

        fun isDirectory(path: String): Boolean {
            val file = File(path)
            return file.exists() && file.isDirectory
        }

        fun isFile(path: String): Boolean {
            val file = File(path)
            return file.exists() && file.isFile
        }

        fun getFileOrDirName(path: String): String {
            val file = File(path)
            return file.name
        }

        fun listFile(file: String): MutableList<File> {
            val files = File(file).listFiles()
            return files.toMutableList()
        }
    }
}