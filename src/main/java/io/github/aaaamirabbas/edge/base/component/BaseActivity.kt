package io.github.aaaamirabbas.edge.base.component

import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import io.github.aaaamirabbas.edge.utils.language.LocaleUtils

abstract class BaseActivity : AppCompatActivity() {

    @LayoutRes
    abstract fun layoutRes(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocaleUtils.setLocale(this)
        resetTitle()
        setContentView(layoutRes())
        viewHandler(savedInstanceState)
    }

    private fun resetTitle() {
        try {
            val label = packageManager.getActivityInfo(
                componentName, PackageManager.GET_META_DATA
            ).labelRes
            if (label != 0) {
                setTitle(label)
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleUtils.setLocale(newBase))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleUtils.setLocale(this)
    }

    abstract fun viewHandler(savedInstanceState: Bundle?)
}