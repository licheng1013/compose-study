package open

object DateUtil {
    // 获取时分
    fun getHourMinute(): String {
        val date = java.util.Date()
        val calendar = java.util.Calendar.getInstance()
        calendar.time = date
        val hour = calendar.get(java.util.Calendar.HOUR_OF_DAY)
        val minute = calendar.get(java.util.Calendar.MINUTE)
        return "$hour:$minute"
    }
}