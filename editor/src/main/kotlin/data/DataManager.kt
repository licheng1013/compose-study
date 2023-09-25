package data

import com.alibaba.fastjson2.JSON
import open.FileUtil

class DataManager {
    var openPath = ""

    companion object {

        private var basePath = "/.my_compose"
        private var savePath = FileUtil.home + "$basePath/.my_compose.json";

        init {
            // 创建目录
            FileUtil.createDir(FileUtil.home + basePath)
        }

        fun getInstance(): DataManager {
            if (FileUtil.isExist(savePath)) {
                val json = FileUtil.loadFile(savePath)
                return JSON.parseObject(json, DataManager::class.java)
            }
            return DataManager()
        }
    }

    fun save() {
        val json = JSON.toJSONString(this)
        FileUtil.saveFile(savePath, json!!)
    }

}