package io.github.aaaamirabbas.edge.utils.permission

import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

object PermissionUtils {

    interface PermissionListener {
        fun observe(permissions: Map<String, Boolean>)
    }

    private lateinit var resultContract: ActivityResultLauncher<Array<String>>

    fun requestPermission(
        activity: AppCompatActivity, permissions: Array<String>,
    ) {
        val isNotGranted = permissions.any { !isGranted(activity, it) }
        if (isNotGranted) {
            request(permissions)
        }
    }

    fun register(
        activity: AppCompatActivity,
        listener: PermissionListener,
    ) {
        resultContract = activity.registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            listener.observe(permissions)
        }
    }

    private fun request(
        permissionList: Array<String>,
    ) {
        resultContract.launch(permissionList)
    }

    fun isGranted(context: Context, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            context, permission
        ) == PackageManager.PERMISSION_GRANTED
    }
}