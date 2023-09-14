package demo

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application


@Composable
@Preview
fun tableDemo() {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.height(40.dp).border(
                1.dp, Color.Gray
            ),
        ) {
            // table headers
            headers.forEach {
                // header name
                // 垂直线
                Spacer(modifier = Modifier.fillMaxHeight().width(1.dp).background(Color.Gray))
                Text(
                    it.name,
                    modifier = Modifier.weight(it.width),
                    // 剧中
                    textAlign = TextAlign.Center
                )
            }
        }

        var list = mutableStateListOf(ArrayList<TableHeader>())
        repeat(100){
            list.add(ArrayList(headers))
        }

        Box {
            // 构建一个滚动列表
            val scrollState = rememberScrollState()
            Box {
                Column(modifier = Modifier.fillMaxSize().verticalScroll(scrollState)) {
                    repeat(list.size) { index ->
                        Row {
                            // table data
                            list[index].forEach {
                                when (it.type) {
                                    2 -> {
                                        Text(
                                            "data$index",
                                            modifier = Modifier.weight(it.width),
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                    3 -> {
                                        OutlinedTextField(
                                            list[index][2].value.value,
                                            modifier = Modifier.weight(it.width),
                                            onValueChange = { value ->
                                                list[index][2].value.value = value
                                            })
                                    }
                                    5 -> {
                                        OutlinedButton(
                                            modifier = Modifier.weight(it.width),
                                            onClick = {
                                                //点击移除
                                            }) {
                                            Text("移除",  textAlign = TextAlign.Center)
                                        }
                                    }
                                    0 -> {
                                        Text(
                                            "data$index",
                                            modifier = Modifier.weight(it.width),
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                }

                            }
                        }
                    }
                }
            }
            VerticalScrollbar(
                adapter = rememberScrollbarAdapter(scrollState),
                Modifier.align(Alignment.CenterEnd)
            )
        }
    }
}

data class TableHeader(
    // name
    val name: String = "",
    // width
    val width: Float = 0f,
    // type
    val type: Int = 0,
    // value
    var value: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue(""))
)

var headers = listOf(
    TableHeader("Key", 0.2f),
    TableHeader("Type", 0.2f, 2),
    TableHeader("Value", 0.2f, 3),
    TableHeader("Comment", 0.2f),
    TableHeader("Edit", 0.2f, 5),
)

fun main() = application {
    Window(
        title = "Table",
        onCloseRequest = ::exitApplication
    ) {
        tableDemo()
    }
}