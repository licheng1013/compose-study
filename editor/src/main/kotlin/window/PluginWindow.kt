package window

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.Theme
import theme.ThemeIcon
import ui.window.DefaultWindow
import ui.window.WindowPosition

object PluginWindow : DefaultWindow() {

    override fun id(): String {
        return "PLUGIN"
    }

    private val pluginList = mutableStateListOf<Boolean>()
    private val searchText = mutableStateOf("")

    init {
        repeat(100) {
            pluginList.add(true)
        }
    }


    /**
     * 已经实现了窗口的滚动
     */
    @Composable
    override fun windowUi() {
        // 构建一个滚动列表

        Column {
            // 搜索插件
            OutlinedTextField(
                value = searchText.value,
                colors = Theme.textFieldColorsInput(),
                onValueChange = {
                    searchText.value = it
                },
                modifier = Modifier.fillMaxWidth().padding(6.dp),
                label = {
                    Text("Search Plugin", color = Theme.getInstance().fontColor)
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = Theme.getInstance().hoverColor
                    )
                }
            )
            Theme.getInstance().scrollBar{
                Column {
                    repeat(pluginList.size) { index ->
                        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                            Spacer(Modifier.weight(1f))
                            Column(modifier = Modifier.width(100.dp)) {
                                Text("Plugin #$index", color = Theme.getInstance().fontColor, fontSize = 16.sp)
                                Text("Desc Info ....", color = Theme.getInstance().fontColor, fontSize = 14.sp)
                            }
                            Switch(checked = pluginList[index], onCheckedChange = {
                                pluginList[index] = it
                            })
                            Spacer(Modifier.weight(1f))
                        }
                    }
                }
            }


        }
    }


    @Composable
    override fun layout() {
        windowUi()
    }

    /**
     *  窗口位置
     */
    override fun position(): WindowPosition {
        return WindowPosition.LEFT_TOP
    }

    override fun iconPath(): String {
        return ThemeIcon.getInstance().plugin
    }


    override fun iconColor(): Color {
        return Color(252, 198, 42)
    }
}