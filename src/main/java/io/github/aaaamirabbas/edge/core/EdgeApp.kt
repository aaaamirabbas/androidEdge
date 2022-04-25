package io.github.aaaamirabbas.edge.core

import android.app.Application

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import com.facebook.stetho.BuildConfig
import com.facebook.stetho.Stetho
import io.github.aaaamirabbas.edge.utils.language.LocaleUtils

open class EdgeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        LocaleUtils.setLocale(this)

        if (BuildConfig.DEBUG) {
            Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                    .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                    .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                    .build()
            )
        }
    }

    //config language for all application
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleUtils.setLocale(this)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }
}