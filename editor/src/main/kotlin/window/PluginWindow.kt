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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import open.IconUtil
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
            Theme.getInstance().scrollBarXY {
                Column {
                    repeat(pluginList.size) { index ->
                        Box(Modifier.width(250.dp)){
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Spacer(Modifier.width(10.dp))
                                IconUtil.icon(ThemeIcon.getInstance().plugin, color = Color.Gray, size = 32)
                                Spacer(Modifier.width(10.dp))
                                Column {
                                    Text("HttpUtil", color = Theme.getInstance().fontColor, fontSize = 16.sp)
                                    Text(
                                        "一个简单的Http插件",
                                         overflow = TextOverflow.Ellipsis,
                                        color = Theme.getInstance().fontColor,
                                        fontSize = 14.sp
                                    )
                                }
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Spacer(Modifier.weight(1f))
                                Checkbox(checked = pluginList[index], onCheckedChange = {
                                    pluginList[index] = it
                                })
                                Spacer(Modifier.width(10.dp))
                            }
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