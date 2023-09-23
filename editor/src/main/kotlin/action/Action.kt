package action

import ui.Ui

interface Action : Ui {
    fun id(): String
    fun group(): String
    fun name(): String
    fun action()
}