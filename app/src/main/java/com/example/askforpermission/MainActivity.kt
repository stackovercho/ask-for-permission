package com.example.askforpermission

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // check if permission to use the camera has already been granted
        val grantedPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)

        if (grantedPermission == PackageManager.PERMISSION_GRANTED) {
            // permission has been granted, start using the camera
            Log.d("MainActivity", "permission was previously granted")
        } else {
            // need to ask for permission to use camera
            Log.d("MainActivity", "permission was NOT previously granted")
        }
    }
}