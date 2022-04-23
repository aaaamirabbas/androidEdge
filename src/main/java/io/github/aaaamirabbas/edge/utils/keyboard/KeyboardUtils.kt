package io.github.aaaamirabbas.edge.utils.keyboard

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import io.github.aaaamirabbas.edge.ext.other.logE

object KeyboardUtils {

    fun open(view: View) {
        view.requestFocus()
        try {
            val inputMethodManager =
                view.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        } catch (e: java.lang.Exception) {
            "showKeyboard failed, error: $e".logE("showKeyboard")
        }
    }

    fun hideKeyboard(view: View) {
        val inputMethodManager =
            view.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}