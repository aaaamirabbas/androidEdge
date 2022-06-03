package io.github.aaaamirabbas.edge.base.component

import android.os.Bundle
import android.view.View

interface BaseFragmentView {
    fun onViewHandler(view: View, savedInstanceState: Bundle?) {}
    fun onLifeCycleHandler() {}
}