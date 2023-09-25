package plugin

import action.DefaultAction
import ui.window.DefaultWindow

interface Plugin {
    fun window(): ArrayList<DefaultWindow>
    fun action(): ArrayList<DefaultAction>
    fun id(): String
}