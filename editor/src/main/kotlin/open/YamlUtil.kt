package open

import org.yaml.snakeyaml.Yaml

object YamlUtil {
    var yaml = Yaml()
    fun <T> load(str: String, clazz: Class<T>): T {
        return yaml.loadAs(str, clazz)
    }
}