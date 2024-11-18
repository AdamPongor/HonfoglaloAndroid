package bme.aut.sza.honfoglalo.data.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import java.net.Inet4Address
import java.net.NetworkInterface

fun getDeviceIPAddress(context: Context): String? {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = connectivityManager.activeNetwork ?: return null
    val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return null

    return when {
        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
            getLocalIPAddress(true) // true for IPv4
        }
        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
            getLocalIPAddress(true) // cellular IP
        }
        else -> null
    }
}

private fun getLocalIPAddress(useIPv4: Boolean): String? {
    try {
        val interfaces = NetworkInterface.getNetworkInterfaces()
        for (networkInterface in interfaces) {
            val addresses = networkInterface.inetAddresses
            for (address in addresses) {
                if (!address.isLoopbackAddress) {
                    val sAddr = address.hostAddress
                    val isIPv4 = address is Inet4Address
                    if (useIPv4) {
                        if (isIPv4) return sAddr
                    } else {
                        if (!isIPv4) return sAddr
                    }
                }
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}