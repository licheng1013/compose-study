package com.aiwan

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

interface PluginUi {
    @Composable
    fun ui();

    @Composable
    fun border(): Modifier {
        // 圆角
        return Modifier.border(
            width = 1.dp, color = Color(227, 227, 227),
            shape = RoundedCornerShape(6.dp)
        )
    }
}