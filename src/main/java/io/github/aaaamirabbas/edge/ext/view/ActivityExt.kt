package io.github.aaaamirabbas.edge.ext.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle

/**
 * @param launcher: usually start activity
 */
fun <T> Activity.restartApp(launcher: Class<T>, bundle: Bundle? = null) {
    Intent(this, launcher).apply {
        bundle?.let {
            putExtras(it)
        }
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(this)
    }

    finish()
    Runtime.getRuntime().exit(0)
}

fun Activity.replaceWith(clazz: Class<*>) {
    startActivity(Intent(applicationContext, clazz).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }).also { finish() }
}