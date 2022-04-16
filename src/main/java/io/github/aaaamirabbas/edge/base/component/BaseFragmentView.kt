package io.github.aaaamirabbas.edge.base.component

import android.os.Bundle
import android.view.View

interface BaseFragmentView : BaseView {
    fun applyView(view: View, savedInstanceState: Bundle?) {}
}