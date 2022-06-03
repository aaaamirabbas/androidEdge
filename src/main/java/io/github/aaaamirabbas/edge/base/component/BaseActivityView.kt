package io.github.aaaamirabbas.edge.base.component

import android.os.Bundle

interface BaseActivityView {
    fun onViewHandler(savedInstanceState: Bundle?) {}
    fun onLifeCycleHandler() {}
}