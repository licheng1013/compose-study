import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NavigationDrawer() {
    // 定义一个状态变量来控制侧边栏的显示和隐藏
    var drawerState by remember { mutableStateOf(DrawerValue.Closed) }
    // 定义一个列表来存放侧边栏的菜单项
    val menuItems = listOf("主页", "配置", "设置", "关于")
    // 使用 ModalDrawer 布局来实现侧边栏和主内容区域
    ModalDrawer(
        drawerContent = {
            // 侧边栏的内容，可以自定义样式和逻辑
            Column {
                // 一个标题文本
                Text(
                    text = "Navigation Drawer",
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(16.dp)
                )
                // 一个分割线
                Divider(color = Color.Gray, thickness = 1.dp)
                // 一个列表，用来展示菜单项
                ListItems(menuItems) { item ->
                    // 点击菜单项时，关闭侧边栏并打印选中的菜单项
                    drawerState = DrawerValue.Closed
                    println("You selected $item")
                }
            }
        },
        drawerState = rememberDrawerState(drawerState),
        content = {
            // 主内容区域，可以自定义样式和逻辑
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = "内容区域") },
                        navigationIcon = {
                            IconButton(onClick = { drawerState =
                                DrawerValue.Open }) {
                                Icon(
                                    Icons.Filled.Menu, contentDescription =
                                "Menu")
                            }
                        }
                    )
                },
                content = { Text(text =
                "显示.") }
            )
        }
    )
}

// 一个封装好的列表组件，用来展示菜单项和点击事件回调函数
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListItems(items: List<String>, onItemClick: (String) -> Unit) {
    items.forEach { item ->
        ListItem(
            text = { Text(text =
            item) },
            icon = {
                Icon(Icons.Filled.Star,
                    contentDescription =
                    null)
            },
            modifier =
            Modifier.clickable(onClick =
            { onItemClick(item) })
        )
    }
}



