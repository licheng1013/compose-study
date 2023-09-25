package plugin

import open.FileUtil
import open.YamlUtil
import org.yaml.snakeyaml.Yaml

data class PluginConfig(
    var id: String? = null,
    var name: String? = null,
    var version: String? = null,
    var minVersion: String? = null,
    var maxVersion: String? = null,
    var author: String? = null,
    var pluginClass: String? = null,
    var desc: String? = null,
)


fun main() {
    val file = FileUtil.loadFile("editor/src/main/resources/plugin/plugin.yml")
    println("file:${file}")
    val pluginConfig = YamlUtil.load(file, PluginConfig::class.java)
    println(pluginConfig)
}