package io.github.aaaamirabbas.edge.utils.glide

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.graphics.drawable.PictureDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

object GlideRequestBuilder {
    private val defaultBitmapOptions by lazy {
        RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
    }

    private val defaultDrawableOptions by lazy {
        RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
    }

    private val defaultSvgOption by lazy {
        RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.DATA)
    }

    fun getDrawableRequestBuilder(
        context: Context,
        requestOptions: RequestOptions? = null
    ): RequestBuilder<Drawable> {
        val requestOptionsTemp: RequestOptions =
            requestOptions?.apply(defaultDrawableOptions) ?: defaultDrawableOptions

        return Glide.with(context)
            .asDrawable()
            .apply(requestOptionsTemp)
    }

    fun getSVGRequestBuilder(
        context: Context,
        requestOptions: RequestOptions? = null
    ): RequestBuilder<PictureDrawable> {
        val requestOptionsTemp: RequestOptions =
            requestOptions?.apply(defaultSvgOption) ?: defaultSvgOption

        return Glide.with(context)
            .`as`(PictureDrawable::class.java)
            .apply(requestOptionsTemp)
            .listener(SvgSoftwareLayerSetter())
    }

    fun getBitmapRequestBuilder(
        context: Context, requestOptions: RequestOptions? = null
    ): RequestBuilder<Bitmap> {
        val requestOptionsTemp: RequestOptions =
            requestOptions?.apply(defaultBitmapOptions) ?: defaultBitmapOptions

        return Glide.with(context)
            .asBitmap()
            .apply(requestOptionsTemp)
    }
}