package io.github.aaaamirabbas.edge.base.component

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ContextThemeWrapper
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBSDFragment : BottomSheetDialogFragment() {

    lateinit var activityContext: AppCompatActivity

    @LayoutRes
    abstract fun layoutRes(): Int

    @StyleRes
    abstract fun themeRes(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, themeRes())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val contextThemeWrapper = ContextThemeWrapper(activity, activity?.theme)
        return inflater.cloneInContext(contextThemeWrapper).inflate(
            layoutRes(), container, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewHandler(view, savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityContext = context as AppCompatActivity
    }

    abstract fun viewHandler(view: View, savedInstanceState: Bundle?)
}