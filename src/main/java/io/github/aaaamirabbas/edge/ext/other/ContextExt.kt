package io.github.aaaamirabbas.edge.ext.other

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat


@SuppressLint("QueryPermissionsNeeded")
fun Context.openURL(url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    }
}

fun Context.openCall(phoneNumber: String) {
    ContextCompat.startActivity(
        this,
        Intent(
            Intent.ACTION_DIAL,
            Uri.fromParts("tel", phoneNumber, null)
        ),
        null
    )
}

fun Context.openSMS(mobile: String, body: String = "") {
    ContextCompat.startActivity(this, Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse("sms:$mobile")
        putExtra("sms_body", body)
    }, null)
}

fun Context.share(title: String, text: String) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, title)
    startActivity(shareIntent)
}


fun Context.getColorID(attrID: Int): Int {
    val typedValue = TypedValue()
    val theme = this.theme
    theme.resolveAttribute(attrID, typedValue, true)
    return typedValue.data
}

@ColorInt
fun Context.getAttrColor(@AttrRes attrID: Int): Int {
    val typedValue = TypedValue()
    val theme = this.theme
    theme.resolveAttribute(attrID, typedValue, true)
    return typedValue.data
}

fun Context.getAttr(attrID: Int): Int {
    val typedValue = TypedValue()
    val theme = this.theme
    theme.resolveAttribute(attrID, typedValue, true)
    return typedValue.data
}

fun Context.getDrawableCompat(res: Int): VectorDrawableCompat? {
    return VectorDrawableCompat.create(resources, res, theme)
}

fun Context.inflateView(@LayoutRes layoutRes: Int): View {
    return LayoutInflater.from(this)
        .inflate(layoutRes, null)
}