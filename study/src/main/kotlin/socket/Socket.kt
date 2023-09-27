package socket

import org.java_websocket.WebSocket
import org.java_websocket.handshake.ClientHandshake
import org.java_websocket.server.WebSocketServer
import java.io.File
import java.io.FileReader
import java.net.InetSocketAddress
import java.net.UnknownHostException
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit


class Socket {

}

fun main() {
    val server = MyWebSocketServer(20020)
    server.start()
}


class MyWebSocketServer(port: Int) : WebSocketServer(InetSocketAddress(port)) {
    private val executorService: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor()
    override fun onOpen(conn: WebSocket, handshake: ClientHandshake?) {
        println("New connection from " + conn.remoteSocketAddress.address.hostAddress)
        conn.send("欢迎连接到Websocket!")
        val file = File("README.md")
        val text = FileReader(file).readText()
        conn.send(text)
    }

    override fun onClose(conn: WebSocket, code: Int, reason: String?, remote: Boolean) {
        println("Closed connection to " + conn.remoteSocketAddress.address.hostAddress)
    }

    override fun onMessage(conn: WebSocket, message: String) {
        println(("Received message from " + conn.remoteSocketAddress.address.hostAddress) + ": " + message)
        conn.send("You said: $message")
    }

    override fun onError(conn: WebSocket, ex: Exception) {
        println(("Error occurred on connection to " + conn.remoteSocketAddress.address.hostAddress) + ": " + ex)
    }

    override fun onStart() {
        println("Server started on port $port")
        //executorService.scheduleAtFixedRate({ broadcast("定时消息...") }, 0, 10, TimeUnit.SECONDS)
    }
}