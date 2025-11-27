package br.senai.sp.jandira.tcc

import android.content.Intent
import android.media.Rating
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.tcc.screens.CodigoVerificacao
import br.senai.sp.jandira.tcc.screens.NovaSenha
import br.senai.sp.jandira.tcc.screens.TelaCadastro
import br.senai.sp.jandira.tcc.screens.TelaHome
import br.senai.sp.jandira.tcc.screens.TelaInicial
import br.senai.sp.jandira.tcc.screens.TelaInicial2
import br.senai.sp.jandira.tcc.screens.TelaLogin
import br.senai.sp.jandira.tcc.screens.TelaPerfil
import br.senai.sp.jandira.tcc.screens.RecuperacaoSenha
import br.senai.sp.jandira.tcc.screens.TelaCalendario
import br.senai.sp.jandira.tcc.screens.TelaMapa
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import okhttp3.Address

class MainActivity : AppCompatActivity() {

    private val places = arrayListOf(
        Place("Aeroporto de São Paulo/Congonhas", LatLng(-23.6253367,-46.6623943), "Av. Washington Luís, s/n - Campo Belo, São Paulo - SP, 04626-911", 4.8f),
        Place("Terminal Santo Amaro", LatLng(-23.6543091,-46.7148871), "Santo Amaro, São Paulo - SP, 04753-060", 4.8f),
        Place("Estação Barra Funda", LatLng(-23.52565,-46.6714554), "R. Dr. Bento Teobaldo Ferraz, 119 - Barra Funda, São Paulo - SP, 01140-070", 4.8f)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activy_main)
        setContent(){
            val navegacao = rememberNavController()

            NavHost(
            navController = navegacao,
            startDestination = "inicio"
        ) {
            composable(route = "inicio") { TelaInicial(navegacao) }
            composable(route = "inicio2") { TelaInicial2(navegacao) }
            composable(route = "login") { TelaLogin(navegacao) }
            composable(route = "cadastro") { TelaCadastro(navegacao) }
            composable(route = "home") { TelaHome(navegacao) }
            composable(route = "perfil") { TelaPerfil(navegacao) }
            composable(route = "recuperacao") { RecuperacaoSenha(navegacao) }
            composable(route = "codigo") { CodigoVerificacao(navegacao) }
            composable(route = "novaSenha") { NovaSenha(navegacao) }
            composable(route = "calendario") { TelaCalendario(navegacao) }
            composable(route = "mapa") { TelaMapa(navegacao) }
        }
        }
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

data class Place(
    val name: String,
    val latLng: LatLng,
    val address: String,
    val rating: Float
)

//val navegacao = rememberNavController()
//NavHost(
//navController = navegacao,
//startDestination = "inicio"
//) {
//    composable(route = "inicio") { TelaInicial(navegacao) }
//    composable(route = "inicio2") { TelaInicial2(navegacao) }
//    composable(route = "login") { TelaLogin(navegacao) }
//    composable(route = "cadastro") { TelaCadastro(navegacao) }
//    composable(route = "home") { TelaHome(navegacao) }
//    composable(route = "perfil") { TelaPerfil(navegacao) }
//    composable(route = "recuperacao") { RecuperacaoSenha(navegacao) }
//    composable(route = "codigo") { CodigoVerificacao(navegacao) }
//    composable(route = "novaSenha") { NovaSenha(navegacao) }
//    composable(route = "calendario") { TelaCalendario(navegacao) }
//    composable(route = "mapa") { TelaMapa(navegacao) }
//}




