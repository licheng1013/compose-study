package web.model
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable


@Serializable
data class JsonResult(
    var code: Int = 0,
    var msg: String = "成功",
    @Contextual
    var data: Any? = null
)

fun ok(data: Any? = null): JsonResult {
    return JsonResult(
        data = data
    )
}

fun okMsg(msg: String): JsonResult {
    return JsonResult(
        msg = msg
    )
}

fun failMag(msg: String): JsonResult {
    return JsonResult(
        code = -1,
        msg = msg
    )
}

