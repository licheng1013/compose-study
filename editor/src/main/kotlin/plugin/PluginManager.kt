package plugin

import open.FileUtil
import open.YamlUtil
import java.net.URL
import java.net.URLClassLoader

fun main() {
    PluginManager.loadPlugin()
}


object PluginManager {
    fun loadPlugin() {
        val urls = arrayOf(URL("file:D:\\my-study\\compose-study\\plugin\\build\\libs\\plugin-1.0-SNAPSHOT.jar"))
        val classLoader = URLClassLoader(urls, PluginManager::class.java.getClassLoader())
        val inputStream = classLoader.getResourceAsStream("META-INF/plugin.yml")
        val file = FileUtil.loadFile(inputStream)
        val pluginConfig = YamlUtil.load(file, PluginConfig::class.java)
        checkConfig(pluginConfig)
        println(pluginConfig)
        tryLoadPlugin(pluginConfig.pluginClass!!,classLoader)
    }

    private fun tryLoadPlugin(classPath: String, classLoader: URLClassLoader) {
        try {
            val cls = classLoader.loadClass(classPath)
            val pluginInstance = cls.getDeclaredConstructor().newInstance()
            println("pluginInstance:${pluginInstance}")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    private fun checkConfig(config: PluginConfig) {
        if (config.id.isNullOrBlank()) {
            throw Exception("id is null")
        }
        if (config.name.isNullOrBlank()) {
            throw Exception("name is null")
        }
        if (config.version.isNullOrBlank()) {
            throw Exception("version is null")
        }
        if (config.minVersion.isNullOrBlank()) {
            throw Exception("minVersion is null")
        }
        if (config.maxVersion.isNullOrBlank()) {
            throw Exception("maxVersion is null")
        }
        if (config.author.isNullOrBlank()) {
            throw Exception("author is null")
        }
        if (config.pluginClass.isNullOrBlank()) {
            throw Exception("pluginClass is null")
        }
        if (config.desc.isNullOrBlank()) {
            throw Exception("desc is null")
        }
    }
}