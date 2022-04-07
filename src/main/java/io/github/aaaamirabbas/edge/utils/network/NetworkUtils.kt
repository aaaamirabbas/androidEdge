package io.github.aaaamirabbas.edge.utils.network

import android.content.Context
import android.net.ConnectivityManager
import io.github.aaaamirabbas.edge.utils.crashlytics.CrashlyticsUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
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

    suspend fun hasOnline(serverURL: String) = flow {
        while (true) {
            try {
                val timeoutMs = 1500
                val socket = Socket()
                val socketAddress = InetSocketAddress(serverURL, 80)

                socket.connect(socketAddress, timeoutMs)
                socket.close()
                emit(true)
            } catch (e: IOException) {
                CrashlyticsUtils.captureException(e, this::class.simpleName)
                emit(false)
            }

            delay(1000)
        }
    }.flowOn(Dispatchers.IO)
}