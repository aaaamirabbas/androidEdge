package io.github.aaaamirabbas.edge.base.component

import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.viewbinding.ViewBinding
import io.github.aaaamirabbas.edge.utils.crashlytics.CrashlyticsUtils
import io.github.aaaamirabbas.edge.utils.language.LocaleUtils


abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater) -> VB

    @Suppress("UNCHECKED_CAST")
    val binding: VB?
        get() = _binding as VB?

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocaleUtils.setLocale(this)
        resetTitle()

        _binding = bindingInflater.invoke(layoutInflater)
        ViewCompat.setLayoutDirection(
            requireNotNull(_binding).root,
            ViewCompat.LAYOUT_DIRECTION_RTL
        )

        setContentView(requireNotNull(_binding).root)

        viewHandler(savedInstanceState)

        initObservers()
    }

    /**
     * fixes android RTL
     */
    private fun resetTitle() {
        try {
            val label = packageManager.getActivityInfo(
                componentName, PackageManager.GET_META_DATA
            ).labelRes
            if (label != 0) {
                setTitle(label)
            }
        } catch (e: PackageManager.NameNotFoundException) {
            CrashlyticsUtils.captureException(e, this::class.simpleName)
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

    protected open fun initObservers() {}
}