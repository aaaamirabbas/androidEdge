package io.github.aaaamirabbas.edge.utils.crashlytics

import com.google.firebase.crashlytics.BuildConfig
import com.google.firebase.crashlytics.FirebaseCrashlytics
import io.github.aaaamirabbas.edge.ext.other.logI


object CrashlyticsUtils {
    fun capture(e: Throwable, parent: Any = Any()) {
        parent::class.simpleName?.let { e.stackTraceToString().logI(it) }
        e.printStackTrace()

        if (!BuildConfig.DEBUG) {
            FirebaseCrashlytics.getInstance().recordException(e)
        }
    }
}