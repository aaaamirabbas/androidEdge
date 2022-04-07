package io.github.aaaamirabbas.edge.ext

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment

fun Fragment.appendFragment(frg: Fragment, frgId: Int) {
    requireActivity().supportFragmentManager.beginTransaction()
        .replace(frgId, frg)
        .addToBackStack(null)
        .commit()
}

fun Fragment.startActivity(activity: Activity) {
    startActivity(
        android.content.Intent(
            this.activity, activity::class.java
        )
    )
}

fun Fragment.startActivity(activity: Activity, bundle: Bundle?) {
    val intent = android.content.Intent(this.activity, activity::class.java)
    bundle?.let { intent.putExtras(it) }
    startActivity(intent)
}

fun Fragment.startActivity(activity: Activity, result: Int) {
    val intent = android.content.Intent(this.activity, activity::class.java)
    startActivityForResult(intent, result)
}