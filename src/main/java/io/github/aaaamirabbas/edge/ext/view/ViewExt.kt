package io.github.aaaamirabbas.edge.ext.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View

fun View.toShow() {
    this.visibility = View.VISIBLE
}

fun View.toHide() {
    this.visibility = View.INVISIBLE
}

fun View.isHide(): Boolean {
    return this.visibility == View.INVISIBLE
}

fun View.toGone() {
    this.visibility = View.GONE
}

fun View.isGone(): Boolean {
    return this.visibility == View.GONE
}

fun View.toEnable() {
    this.isEnabled = true
}

fun View.toDisable() {
    this.isEnabled = false
}

fun View.toggleVisibility() {
    if (this.visibility == View.VISIBLE)
        this.toHide()
    else this.toShow()
}

fun View.toggleEnable() {
    this.isEnabled = !this.isEnabled
}

fun View.smoothShow(duration: Long, endListener: (animator: Animator) -> Unit) {
    animate().alpha(1f).setDuration(duration)
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                endListener(animation)

            }
        })
}

fun View.smoothHide(duration: Long, endListener: (animator: Animator) -> Unit) {
    animate().alpha(0f).setDuration(duration)
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                endListener(animation)
            }
        })
}