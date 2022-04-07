package io.github.aaaamirabbas.edge.utils.network

import android.accounts.NetworkErrorException
import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkInterceptor @Inject constructor(
    private val context: Context
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!NetworkUtils.isConnected(context)) {
            throw NoConnectivityException(context)
        }

        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}

class NoConnectivityException(
    private val context: Context
) : NetworkErrorException() {
    override val message: String
        get() = "no network"
}
