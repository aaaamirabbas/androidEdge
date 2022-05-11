package io.github.aaaamirabbas.edge.ext.other

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File

fun File.toBitmap(): Bitmap {
    return BitmapFactory.decodeFile(this.absolutePath)
}