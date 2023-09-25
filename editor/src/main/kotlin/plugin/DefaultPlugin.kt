package plugin

import action.DefaultAction
import ui.window.DefaultWindow

abstract class DefaultPlugin : Plugin {
    override fun window(): ArrayList<DefaultWindow> {
        return arrayListOf()
    }

    override fun action(): ArrayList<DefaultAction> {
        return arrayListOf()
    }
}