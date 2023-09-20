package util

import java.net.Inet4Address
import java.net.NetworkInterface


// 静态类
object MyUtil {
    // 获取ip地址
    fun getIp(): List<String> {
        var listIp = mutableListOf<String>()
        val interfaces = NetworkInterface.getNetworkInterfaces()
        while (interfaces.hasMoreElements()) {
            val ni = interfaces.nextElement()
            val addresses = ni.inetAddresses
            while (addresses.hasMoreElements()) {
                val address = addresses.nextElement()
                if (!address.isLinkLocalAddress && !address.isLoopbackAddress && address is Inet4Address) {
                    // 移除虚拟网卡
                    if (ni.displayName.contains("VirtualBox") || ni.displayName.contains("VMware")) {
                        continue
                    }
                    listIp.add(address.getHostAddress())
                    // 检查是否存在
                }
            }
        }
        return listIp.toList()
    }

    // 获取系统下载目录
    fun getDownloadDir(): String {
        return System.getProperty("user.home") + "\\Downloads\\"
    }
}