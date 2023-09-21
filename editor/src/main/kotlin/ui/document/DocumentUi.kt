package ui.document

import androidx.compose.runtime.Composable
import ui.Ui

interface DocumentUi : Ui {
    fun id(): String
    @Composable
    fun layout()
    fun title(): String
}