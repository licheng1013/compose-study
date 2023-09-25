package plugin

import open.FileUtil
import java.net.URL
import java.net.URLClassLoader


object PluginManager {
    fun loadPlugin() {
        val urls = arrayOf(URL("file:///path/to/your/jar/file.jar"))
        val classLoader = URLClassLoader(urls, PluginManager::class.java.getClassLoader())
        val inputStream = classLoader.getResourceAsStream("path/to/your/resource/file.txt")
        val file = FileUtil.loadFile(inputStream)
        println("file:${file}")
    }
}