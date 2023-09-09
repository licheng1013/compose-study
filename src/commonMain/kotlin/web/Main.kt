package web

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import log.myPrintln
import web.model.okMsg


fun web(){
    // web 框架
    // 子线程启动
    myPrintln("web 服务启动")
    embeddedServer(Netty, port = 8000) {
        extracted()
    }.start(wait = true)

}

private fun Application.extracted() {
    install(ContentNegotiation){
        json()
    }
    routing {
        get("/") {
            call.respondText("Hello, world!")
        }
        get("/uploadFile") {
            call.respond(okMsg("成功"))
        }
    }
}

fun main() {
    web()
}