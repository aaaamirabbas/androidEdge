package io.github.aaaamirabbas.edge.ext

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.net.ConnectivityManager
import android.net.Uri
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatDialog
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.google.android.material.bottomsheet.BottomSheetDialog


fun Context.pxToDp(px: Int): Int {
    return (px.toFloat() / (Resources.getSystem().displayMetrics.densityDpi.toFloat()
            / DisplayMetrics.DENSITY_DEFAULT)).toInt()
}

fun Context.dpToPx(dp: Int): Int {
    return (dp.toFloat() * (Resources.getSystem().displayMetrics.densityDpi.toFloat()
            / DisplayMetrics.DENSITY_DEFAULT)).toInt()
}

fun Context.inflateView(@LayoutRes layoutRes: Int): View {
    return LayoutInflater.from(this)
        .inflate(layoutRes, null)
}

fun Context.makeDialog(
    @LayoutRes layoutRes: Int,
    @StyleRes themRes: Int,
    isBottom: Boolean = false
): AppCompatDialog {
    return AppCompatDialog(this, themRes)
        .apply {
            setContentView(inflateView(layoutRes))
            if (isBottom) {
                val wManager: WindowManager.LayoutParams = this.window?.attributes!!
                wManager.gravity = Gravity.BOTTOM or Gravity.CENTER
            }
        }
}

fun Context.makeSheet(
    @LayoutRes layoutRes: Int,
    @StyleRes themRes: Int
): BottomSheetDialog {
    return BottomSheetDialog(this).apply {
        setContentView(inflateView(layoutRes))
        window?.setWindowAnimations(themRes)
        window?.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
                    or WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN
        )
    }
}

fun Context.openURL(url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    }
}

fun Context.share(text: String) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, null)
    startActivity(shareIntent)
}


fun Context.getColorID(attrID: Int): Int {
    val typedValue = TypedValue()
    val theme = this.theme
    theme.resolveAttribute(attrID, typedValue, true)
    return typedValue.data
}

fun Context.getDrawableCompat(res: Int): VectorDrawableCompat? {
    return VectorDrawableCompat.create(resources, res, theme)
}

fun Context.isNetworkAvailable(): Boolean {
    val connectivityMgr = getSystemService(Context.CONNECTIVITY_SERVICE)
            as ConnectivityManager
    val networkInfo = connectivityMgr.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}