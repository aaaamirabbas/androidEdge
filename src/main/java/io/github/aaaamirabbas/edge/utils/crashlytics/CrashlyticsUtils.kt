package io.github.aaaamirabbas.edge.utils.crashlytics

import io.github.aaaamirabbas.edge.ext.other.logE


object CrashlyticsUtils {
    fun capture(e: Throwable, parent: Any = Any()) {
        e.printStackTrace()
        "${parent::class.simpleName ?: 'X'}: ${e.stackTraceToString()}".logE()
    }
}