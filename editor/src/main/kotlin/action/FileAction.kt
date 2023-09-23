package action;

class FileAction : DefaultAction() {
    override fun id(): String {
        return "FILE"
    }

    override fun group(): String {
        return ActionGroup.FILE_GROUP
    }

    override fun name(): String {
        return "File"
    }
}
