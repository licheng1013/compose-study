package action;

class CodeAction : DefaultAction() {
    override fun id(): String {
        return "CODE"
    }

    override fun group(): String {
        return ActionGroup.CODE_GROUP
    }

    override fun name(): String {
        return "Code"
    }
}
