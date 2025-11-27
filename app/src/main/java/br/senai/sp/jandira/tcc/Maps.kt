package br.senai.sp.jandira.tcc

import android.content.Context
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions

@Composable
fun Maps(navegacao: NavHostController?) {
    val context = LocalContext.current
    
    AndroidView(
        factory = { ctx ->
            MapView(ctx).apply {
                // Inicializa o mapa
                onCreate(null)
                getMapAsync { googleMap ->
                    val places = listOf(
                        Place(
                            "Aeroporto de São Paulo/Congonhas",
                            LatLng(-23.6253367, -46.6623943),
                            "Av. Washington Luís, s/n - Campo Belo, São Paulo - SP, 04626-911",
                            4.8f
                        ),
                        Place(
                            "Terminal Santo Amaro",
                            LatLng(-23.6543091, -46.7148871),
                            "Santo Amaro, São Paulo - SP, 04753-060",
                            4.8f
                        ),
                        Place(
                            "Estação Barra Funda",
                            LatLng(-23.52565, -46.6714554),
                            "R. Dr. Bento Teobaldo Ferraz, 119 - Barra Funda, São Paulo - SP, 01140-070",
                            4.8f
                        )
                    )

                    // Configura o InfoWindowAdapter se necessário
                    googleMap.setInfoWindowAdapter(MarkerInfoAdapter(context))

                    // Adiciona os marcadores
                    places.forEach { place ->
                        val marker = googleMap.addMarker(
                            MarkerOptions()
                                .title(place.name)
                                .snippet(place.address)
                                .position(place.latLng)
                        )
                        marker?.tag = place
                    }

                    // Ajusta a câmera para mostrar todos os pontos
                    googleMap.setOnMapLoadedCallback {
                        val bounds = LatLngBounds.builder()
                        places.forEach { bounds.include(it.latLng) }
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 100))
                    }
                }
            }
        }
    )
}


