import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import file.home
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import log.myPrintln


fun main() = application {
    // web 框架
    // 子线程启动
    Thread{
        myPrintln("web 服务启动")
        embeddedServer(Netty, port = 8000) {
            routing {
                get ("/") {
                    call.respondText("Hello, world!")
                }
            }
        }.start(wait = true)
    }.start()


    Window(
        title = "File Sync",
        onCloseRequest = ::exitApplication) {
        home()
    }



}