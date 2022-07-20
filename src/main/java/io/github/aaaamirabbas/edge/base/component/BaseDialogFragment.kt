package io.github.aaaamirabbas.edge.base.component

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.appcompat.view.ContextThemeWrapper
import androidx.viewbinding.ViewBinding


abstract class BaseDialogFragment<VB : ViewBinding>(
    private val bindingFactory: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : AppCompatDialogFragment(), BaseFragmentView {

    lateinit var binding: VB

    lateinit var baseActivity: BaseActivity<*>
    private var isExistInBackStack = false

    override fun onDestroy() {
        super.onDestroy()
        isExistInBackStack = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialog?.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val contextThemeWrapper = ContextThemeWrapper(
            requireActivity(), requireActivity().theme
        )

        binding = bindingFactory.invoke(
            inflater.cloneInContext(contextThemeWrapper), container, false
        )

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