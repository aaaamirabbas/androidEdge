package io.github.aaaamirabbas.edge.ext

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
import android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
import android.widget.FrameLayout
import androidx.annotation.ColorRes
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Activity.changeNavigationColor(@ColorRes color: Int, isDarkText: Boolean) {
    window.navigationBarColor = ContextCompat.getColor(
        this, color
    )

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        if (isDarkText) {
            window.decorView.systemUiVisibility = SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        } else {
            window.decorView.systemUiVisibility = 0
        }
    }
}

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Activity.changeStatusColor(
    @ColorRes color: Int,
    isDarkText: Boolean
) {
    window.statusBarColor = ContextCompat.getColor(
        this, color
    )

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        if (isDarkText) {
            window.decorView.systemUiVisibility = SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            window.decorView.systemUiVisibility = 0
        }
    }
}


fun AppCompatActivity.appendFragment(fragment: Fragment, frameLayout: FrameLayout) {
    if (frameLayout.childCount == 0)
        supportFragmentManager
            .beginTransaction()
            .add(frameLayout.id, fragment, fragment.toString())
            .commit()
    else
        supportFragmentManager
            .beginTransaction()
            .replace(frameLayout.id, fragment, fragment.toString())
            .addToBackStack(null)
            .commit()
}

fun AppCompatActivity.appendFragment(
    fragment: Fragment,
    frameLayout: FrameLayout, bundle: Bundle?
) {
    fragment.arguments = bundle

    if (frameLayout.childCount == 0)
        supportFragmentManager
            .beginTransaction()
            .add(frameLayout.id, fragment, fragment.toString())
            .commit()
    else
        supportFragmentManager
            .beginTransaction()
            .replace(frameLayout.id, fragment, fragment.toString())
            .addToBackStack(null)
            .commit()
}

fun Activity.startActivity(activity: Activity, bundle: Bundle?) {
    val intent = Intent(this, activity::class.java)
    bundle?.let { intent.putExtras(it) }
    startActivity(intent)
}

fun Activity.startActivity(activity: Activity) {
    startActivity(Intent(this, activity::class.java))
}

fun Activity.startActivity(intent: Intent, result: Int) {
    startActivityForResult(intent, result)
}

fun Activity.isPermission(permissionArray: Array<String>): Boolean {
    if (Build.VERSION.SDK_INT < 23) return true

    val isPermissionList = mutableListOf<Boolean>()

    permissionArray.forEach {
        isPermissionList.add(
            ContextCompat.checkSelfPermission(this, it)
                    == PackageManager.PERMISSION_GRANTED
        )
    }

    return if (isPermissionList.any { isp -> !isp }) {
        ActivityCompat.requestPermissions(this, permissionArray, 0)
        false
    } else true
}