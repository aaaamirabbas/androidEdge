package io.github.aaaamirabbas.edge.ext

import android.content.Context
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatDialog
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialog

fun View.showKeyboard() {
    this.requestFocus()
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.toggleSoftInput(
        InputMethodManager.SHOW_FORCED,
        InputMethodManager.HIDE_IMPLICIT_ONLY
    )
}

fun View.hideKeyboard() {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}

fun View.toShow() {
    this.visibility = View.VISIBLE
}

fun View.toHide() {
    this.visibility = View.INVISIBLE
}

fun View.toGone() {
    this.visibility = View.GONE
}

fun View.enable() {
    this.isEnabled = true
}

fun View.disable() {
    this.isEnabled = false
}

fun View.toggleVisibility() {
    if (this.visibility == View.VISIBLE)
        this.toHide()
    else this.toShow()
}

fun View.toggleUsable() {
    this.isEnabled = !this.isEnabled
}

/*
    @author : naqqdi@gmail.com
    create dialogSheet and setting theme
 */

fun View.createDialogSheet(@StyleRes themeRes: Int): AppCompatDialog {
    val dialog = BottomSheetDialog(context, themeRes)
    dialog.setContentView(this)
    return dialog
}


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun View.setForegroundClickable() {
    val outValue = TypedValue()
    context.theme.resolveAttribute(
        android.R.attr.selectableItemBackgroundBorderless, outValue, true
    )
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        foreground = ContextCompat.getDrawable(context, outValue.resourceId)
    }
}
