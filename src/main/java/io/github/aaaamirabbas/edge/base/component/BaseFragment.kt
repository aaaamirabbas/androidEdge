package io.github.aaaamirabbas.edge.base.component

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding


abstract class BaseFragment<VB : ViewBinding>(
    private val bindingFactory: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : Fragment(), BaseFragmentView {

    lateinit var binding: VB

    lateinit var baseActivity: BaseActivity<*>
    private var isExistInBackStack = false

    override fun onDestroy() {
        super.onDestroy()
        isExistInBackStack = false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = bindingFactory.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onViewHandler(view, savedInstanceState)
        onLifeCycleHandler()

        isExistInBackStack = true
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        baseActivity = context as BaseActivity<*>
    }

    fun isExistInBackStack(): Boolean {
        return isExistInBackStack
    }

    fun execWhenNotExistInStack(action: () -> Unit) {
        if (!isExistInBackStack) {
            action()
        }
    }
}