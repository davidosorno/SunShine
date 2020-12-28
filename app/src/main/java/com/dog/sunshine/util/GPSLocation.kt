package com.dog.sunshine.util

import android.Manifest
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dog.sunshine.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlin.reflect.jvm.internal.impl.metadata.jvm.`JvmProtoBuf$StringTableTypes$RecordOrBuilder`

const val PERMISSION_REQUEST_LOCATION = 0

class GPSLocation(
    private val context: Context
){
    private val fusedLocation = FusedLocationProviderClient(context)

    private val _location = MutableLiveData<Location>()
    val location: LiveData<Location>
        get() = _location


    fun getLocationCoordinates(): Boolean{
        if(checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocation.lastLocation.addOnCompleteListener {
                it.result?.let { lastLocation ->
                    Log.i("DEBUGGING", lastLocation.toString())
                    _location.value = lastLocation
                }
            }
            return true
        }
        return false
    }
}