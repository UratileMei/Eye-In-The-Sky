package com.example.eyeinthesky
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapView) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        mMap.isMyLocationEnabled = true  // Show user location if permission granted

        val birdingHotspots = getHotspots()  // Mock data for now
        val maxDistance = getSharedPreferences("UserSettings", Context.MODE_PRIVATE).getInt("maxDistance", 50)

        // Show hotspots within user's chosen distance
        birdingHotspots.filter{it.distance <= maxDistance }.forEach { hotspot ->
            val position = LatLng(hotspot.lat, hotspot.lng)
            mMap.addMarker(MarkerOptions().position(position).title(hotspot.name))
        }

        // Set a click listener for getting directions to a hotspot
        mMap.setOnMarkerClickListener { marker ->
            getDirectionsToHotspot(marker.position)  // Implement directions fetching
            true
        }
    }

    private fun getHotspots(): List<BirdingHotspot> {
        // Replace with actual API data fetching from eBird API or other service
        return listOf(
            BirdingHotspot("Hotspot 1", 40.712776, -74.005974, 25),
            BirdingHotspot("Hotspot 2", 34.052235, -118.243683, 30),
            BirdingHotspot("Hotspot 3", 51.507351, -0.127758, 60)
        )
    }

    private fun getDirectionsToHotspot(destination: LatLng) {
        // Logic to calculate and display the route
        // Can be implemented using Google Directions API or your own route algorithm
    }
}
