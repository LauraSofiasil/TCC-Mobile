package br.senai.sp.jandira.tcc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import okhttp3.Address

class Maps : AppCompatActivity() {

    private val places = arrayListOf(
        Place("Aeroporto de São Paulo/Congonhas", LatLng(-23.6253367,-46.6623943), "Av. Washington Luís, s/n - Campo Belo, São Paulo - SP, 04626-911", 4.8f),
        Place("Terminal Santo Amaro", LatLng(-23.6543091,-46.7148871), "Santo Amaro, São Paulo - SP, 04753-060", 4.8f),
        Place("Estação Barra Funda", LatLng(-23.52565,-46.6714554), "R. Dr. Bento Teobaldo Ferraz, 119 - Barra Funda, São Paulo - SP, 01140-070", 4.8f)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activy_main)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync{ googleMap ->
            googleMap.setInfoWindowAdapter(MarkerInfoAdapter(this))
            addMarkers(googleMap)

            googleMap.setOnMapLoadedCallback {
                val bounds = LatLngBounds.builder()

                places.forEach{
                    bounds.include(it.latLng)
                }

                googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 100))
            }
        }


    }

    private fun addMarkers(googleMap: GoogleMap){
        places.forEach { place ->
            val marker = googleMap.addMarker(
                MarkerOptions()
                    .title(place.name)
                    .snippet(place.address)
                    .position(place.latLng)
            )

            marker?.tag = place
        }
    }
}

