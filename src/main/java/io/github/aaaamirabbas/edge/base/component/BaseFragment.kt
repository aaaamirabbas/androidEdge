package io.github.aaaamirabbas.edge.base.component

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding


abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    var binding: VB? = null
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    lateinit var activityContext: AppCompatActivity

    open fun applyView(view: View, savedInstanceState: Bundle?) {}
    open fun applyStart() {}
    open fun applyObserves() {}

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = bindingInflater.invoke(inflater, container, false)

        return requireNotNull(binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        applyView(view, savedInstanceState)
        applyStart()
        applyObserves()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityContext = context as BaseActivity<*>
    }
}