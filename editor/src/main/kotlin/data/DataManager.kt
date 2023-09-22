package data

import com.alibaba.fastjson2.JSON
import open.FileUtil
import window.file.FileTree

class DataManager {
    var openPath = ""

    companion object {
        private var savePath = FileUtil.home + "/.my_compose.json";
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