package action;

class EditAction : DefaultAction() {
    override fun id(): String {
        return "EDIT"
    }

    override fun group(): String {
        return ActionGroup.EDIT_GROUP
    }

    override fun name(): String {
        return "Edit"
    }
}
