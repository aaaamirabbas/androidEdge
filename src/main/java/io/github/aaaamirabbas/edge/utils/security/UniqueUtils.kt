package io.github.aaaamirabbas.edge.utils.security

import android.annotation.SuppressLint
import android.content.Context

object UniqueUtils {
    @SuppressLint("HardwareIds")
    fun getAndroidId(context: Context): String {
        return android.provider.Settings.Secure.getString(
            context.contentResolver,
            android.provider.Settings.Secure.ANDROID_ID
        )
    }
}