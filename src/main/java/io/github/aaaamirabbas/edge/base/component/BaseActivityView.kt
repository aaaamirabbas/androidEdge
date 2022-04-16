package io.github.aaaamirabbas.edge.base.component

import android.os.Bundle

interface BaseActivityView : BaseView {
    fun applyView(savedInstanceState: Bundle?) {}
}