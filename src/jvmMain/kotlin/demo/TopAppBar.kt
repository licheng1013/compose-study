package demo

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun TopAppBarDemo() {
    TopAppBar(

        title = {
            Text("Study Compose")
        },
        backgroundColor = Color.White,


        actions = {
            Button(onClick = {}){
                Text("按钮1")
            }
        }
    )
}