package com.alientech.gosouth

import android.Manifest
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import kotlin.math.cos
import kotlin.math.sin

class MainActivity : AppCompatActivity() {
    lateinit var locationText: TextView

    lateinit var locationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    lateinit var locationCallback: LocationCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationText = findViewById(R.id.locationText)

        locationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                Log.d("MainActivity", "Received location result")

                locationResult ?: return

                for (location in locationResult.locations) {
                    locationText.text = "Latitude: ${location.latitude}\nLongitude: ${location.longitude}"
                }
            }
        }

        if (
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ) {
            Log.d("MainActivity", "Permission granted")

            locationProviderClient.lastLocation.addOnSuccessListener { location : Location? ->
                Log.d("MainActivity", "Last location request succeeded")

                location!!

                locationText.text = "Latitude: ${location.latitude}\nLongitude: ${location.longitude}"

                locationRequest = LocationRequest.create().apply {
                    interval = 10000
                    fastestInterval = 5000
                    priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                }

                val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
                val client: SettingsClient = LocationServices.getSettingsClient(this)
                val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())

                task.addOnSuccessListener {
                    Log.d("MainActivity", "Location settings correct")

                    locationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())

                    Log.d("MainActivity", "Requested location updates")
                }

                task.addOnFailureListener { exception ->
                    if (exception is ResolvableApiException){
                        try {
                            exception.startResolutionForResult(this@MainActivity, 0x1)
                        } catch (sendEx: IntentSender.SendIntentException) { }
                    }
                }
            }

            locationProviderClient.lastLocation.addOnFailureListener {
                Log.d("MainActivity", "Last location request failed: ${it.message} (${it.cause})")
            }
        } else {
            Log.d("MainActivity", "Permission denied")
        }
    }
}