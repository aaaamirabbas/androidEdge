package io.github.aaaamirabbas.edge.ext

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import io.github.aaaamirabbas.edge.utils.glide.GlideRequestBuilder

fun ImageView.clear() {
    this.setImageDrawable(null)
}

fun ImageView.loadCompat(
    url: Any,
    @DrawableRes placeholderRes: Int? = null,
    @DrawableRes errorRes: Int? = null,
    requestOptions: RequestOptions? = null
) {
    if (url is String && url.toString().contains(".svg"))
        loadSVG(url, placeholderRes, errorRes, requestOptions)
    else loadDrawable(url, placeholderRes, errorRes, requestOptions)
}

@SuppressLint("CheckResult")
private fun ImageView.loadSVG(
    url: Any,
    @DrawableRes placeholderRes: Int? = null,
    @DrawableRes errorRes: Int? = null,
    requestOptions: RequestOptions? = null
) {
    GlideRequestBuilder.getSVGRequestBuilder(context, requestOptions)
        .load(url)
        .apply {
            placeholderRes?.let { placeholder(it) }
            errorRes?.let { error(it) }
        }.into(this)
}


private fun ImageView.loadDrawable(
    url: Any,
    @DrawableRes placeholderRes: Int? = null,
    @DrawableRes errorRes: Int? = null,
    requestOptions: RequestOptions? = null
) {
    GlideRequestBuilder.getDrawableRequestBuilder(context, requestOptions)
        .load(url)
        .apply {
            placeholderRes?.let { placeholder(it) }
            errorRes?.let { error(it) }
        }.into(this)
}

fun Context.getBitmap(
    url: Any,
    result: ((Bitmap) -> Unit),
    @DrawableRes placeholderRes: Int? = null,
    @DrawableRes errorRes: Int? = null,
    requestOptions: RequestOptions? = null
) {
    GlideRequestBuilder.getBitmapRequestBuilder(this, requestOptions)
        .load(url)
        .apply {
            placeholderRes?.let { placeholder(it) }
            errorRes?.let { error(it) }
        }.into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                result.invoke(resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) {}

        })
}