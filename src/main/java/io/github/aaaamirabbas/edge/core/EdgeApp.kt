package io.github.aaaamirabbas.edge.core

import android.app.Application

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import com.facebook.stetho.Stetho
import io.github.aaaamirabbas.edge.utils.language.LocaleUtils

open class EdgeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        LocaleUtils.setLocale(this)
        Stetho.initializeWithDefaults(this)
    }

    //config language for all application
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleUtils.setLocale(this)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }
}