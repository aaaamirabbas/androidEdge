package io.github.aaaamirabbas.edge.ext.view

import android.view.View
import android.widget.AdapterView
import androidx.appcompat.widget.AppCompatSpinner

fun AppCompatSpinner.observeSelected(result: (Int, Long) -> Unit) {
    this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {}

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            result(position, id)
        }
    }
}