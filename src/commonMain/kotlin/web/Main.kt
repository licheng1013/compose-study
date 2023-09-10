package web

import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import log.myPrintln
import util.MyUtil
import web.model.okMsg
import java.io.File


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
            val header = call.request.header("Token")
            myPrintln("header:${header}")
            call.respond(okMsg("成功"))
        }
        post("/login") {
            val header = call.request.header("Token")
            myPrintln("header:${header}")
            // 获取表单数据
            val params = call.receiveParameters()
            val username = params["userName"]
            myPrintln("username:${username}")


            call.respond(okMsg("成功"))
        }
        // 下载文件
        get("/download") {
            val file = File("C:\\Users\\lc\\Downloads\\aa.mp4")
            call.response.header(
                HttpHeaders.ContentDisposition,
                ContentDisposition.Attachment.withParameter(ContentDisposition.Parameters.FileName, file.name)
                    .toString()
            )
            call.respondFile(file)
        }

        post("/uploadFile") {
            // 文件上传成功后，保存到本地
            var fileDescription = MyUtil.getDownloadDir()
            // 解析多部分表单数据
            val multipartData = call.receiveMultipart()
            // 遍历所有的表单项
            multipartData.forEachPart { part ->
                when (part) {
                    // 判断表单内容是否为文件
                    is PartData.FileItem -> {
                        var fileName = part.originalFileName as String
                        val fileBytes = part.streamProvider().readBytes()
                        File("$fileDescription$fileName").writeBytes(fileBytes)
                    }else -> {}
                }
                part.dispose()
            }

            call.respond(okMsg("上传成功"))
        }
    }
}

fun main() {
    println(MyUtil.getIp())
    web()
}