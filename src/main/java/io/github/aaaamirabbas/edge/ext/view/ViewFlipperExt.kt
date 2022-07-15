package io.github.aaaamirabbas.edge.ext.view

import android.widget.ViewFlipper

fun ViewFlipper.currentIndex(): Int = displayedChild

fun ViewFlipper.switch(index: Int) {
    displayedChild = index
}
