package io.github.aaaamirabbas.edge.ext

import android.widget.ViewFlipper

fun ViewFlipper.go(index: Int) {
    this.displayedChild = index
}