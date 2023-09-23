package action;

class HelpAction : DefaultAction() {
    override fun id(): String {
        return "HELP"
    }

    override fun group(): String {
        return ActionGroup.HELP_GROUP
    }

    override fun name(): String {
        return "Help"
    }
}
