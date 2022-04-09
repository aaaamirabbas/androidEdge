package io.github.aaaamirabbas.edge.utils.network

import android.content.Context
import android.net.ConnectivityManager
import io.github.aaaamirabbas.edge.ext.other.flowOnIO
import kotlinx.coroutines.delay
import java.net.InetSocketAddress
import java.net.Socket


object NetworkUtils {

    fun isConnected(context: Context): Boolean {
        val connectivityMgr =
            context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE)
                    as ConnectivityManager
        val networkInfo = connectivityMgr.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    suspend fun subscribeOnlineStatus(url: String) = flowOnIO<Boolean> {
        while (true) {
            runCatching {
                Socket().apply {
                    connect(InetSocketAddress(url, 80), 1000)
                    close()
                }
            }.onSuccess {
                emit(true)
            }.onFailure {
                emit(false)
            }

            delay(1010)
        }
    }
}