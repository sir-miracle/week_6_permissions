package com.example.week_6_permissions_storage_database

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//
//        Intent(this, MainActivity2::class.java).also {
//            startActivity(it)
//        }

        val btn = findViewById<Button>(R.id.permission)
        btn.setOnClickListener {
            requestPermissions()
        }
    }

    private fun isWriteExternalStorageGranted() =
         ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED

    private fun isForeGroundLocationGranted() =ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
    private fun isBackGroundLocationGranted() =ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED
    private fun isContactsAccessGranted() = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED

    private fun requestPermissions(){
        val permissionsToRequest = mutableListOf<String>()
        if( ! isWriteExternalStorageGranted()){
            permissionsToRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if(! isForeGroundLocationGranted()){
            permissionsToRequest.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
        if (! isBackGroundLocationGranted()){
            permissionsToRequest.add(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        }

        if (! isContactsAccessGranted()){
            permissionsToRequest.add(Manifest.permission.READ_CONTACTS)
        }

        if (permissionsToRequest.isNotEmpty()){
            ActivityCompat.requestPermissions(this, permissionsToRequest.toTypedArray(),0)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode==0 && grantResults.isNotEmpty()){
            for( i in grantResults.indices){
                if(grantResults[i] == PackageManager.PERMISSION_GRANTED){

                    Toast.makeText(this, "${permissions[i]} granted", Toast.LENGTH_SHORT).show()
                }


            }
        }
    }
}