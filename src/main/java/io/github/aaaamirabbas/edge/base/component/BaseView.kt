package io.github.aaaamirabbas.edge.base.component

import android.os.Bundle
import android.view.View

interface BaseView {
    fun applyView(savedInstanceState: Bundle?) {}
    fun applyView(view: View, savedInstanceState: Bundle?) {}
    fun applyStart() {}
    fun applyObserves() {}
}