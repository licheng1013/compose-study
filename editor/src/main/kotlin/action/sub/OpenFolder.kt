package action.sub;

import Editor
import action.ActionGroup
import action.DefaultAction
import data.DataManager
import open.FileUtil
import window.FileWindow

class OpenFolder : DefaultAction() {
    override fun id(): String {
        return "OPEN_FOLDER"
    }

    override fun group(): String {
        return ActionGroup.FILE_GROUP
    }

    override fun name(): String {
        return "Open Folder"
    }

    override fun action() {
        //调用文件窗口
        val singleDir = FileUtil.openSingleDir()
        if (singleDir.isNotEmpty()) {
            val dataManager = DataManager.getInstance()
            dataManager.openPath = singleDir
            dataManager.save()
            Editor.editor.windowList.forEach {
                if (it is FileWindow) {
                    it.selectPath(singleDir)
                    Editor.editor.openWindowById(it.id())
                }
            }
        }
    }
}
