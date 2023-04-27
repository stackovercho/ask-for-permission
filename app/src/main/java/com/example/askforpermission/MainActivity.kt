package com.example.askforpermission

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private var permission: String = Manifest.permission.CAMERA
    private lateinit var launcher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // check if permission to use the camera has already been granted
        val grantedPermission = ContextCompat.checkSelfPermission(this, permission)

        if (grantedPermission == PackageManager.PERMISSION_GRANTED) {
            // permission has been granted, start using the camera
            Log.d("MainActivity", "permission was previously granted")
        } else {
            // need to ask for permission to use camera
            Log.d("MainActivity", "permission was NOT previously granted")
            val contract = ActivityResultContracts.RequestPermission()
            // val result = Results()
            // launcher = registerForActivityResult(contract, result)
            launcher = registerForActivityResult(contract) {
                if (it) Log.d("MainActivity", "permission granted!")
                else Log.d("MainActivity", "sorry, permission not granted")
            }
            launcher.launch(permission)
        }
    }

    inner class Results : ActivityResultCallback<Boolean> {
        override fun onActivityResult(result: Boolean?) {
            result?.let {
                if (result) Log.d("MainActivity", "permission granted!")
                else Log.d("MainActivity", "sorry, permission not granted")
            }
        }
    }
}