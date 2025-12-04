package br.senai.sp.jandira.tcc

import android.content.Context
import android.view.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.width
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
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

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            factory = { ctx ->
                MapView(ctx).apply {
                    onCreate(null)
                    getMapAsync { googleMap ->
                        val places = listOf(
                            Place(
                                "Aeroporto de São Paulo/Congonhas",
                                LatLng(-23.6253367, -46.6623943),
                                "Av. Washington Luís, s/n - Campo Belo, São Paulo - SP, 04626-911",
                                "(11) 5090-9000"
                            ),
                            Place(
                                "Terminal Santo Amaro",
                                LatLng(-23.6543091, -46.7148871),
                                "Santo Amaro, São Paulo - SP, 04753-060",
                                "(11) 5685-7071"
                            ),
                            //
                            Place(
                                "Estação Barra Funda",
                                LatLng(-23.52565, -46.6714554),
                                "R. Dr. Bento Teobaldo Ferraz, 119 - Barra Funda, São Paulo - SP, 01140-070",
                                "(11) 3392-2455"
                            ),
                            Place(
                                "Estação Tatuapé",
                                LatLng(-23.5402778,-46.5812951),
                                "R. Melo Freire - Tatuapé, São Paulo - SP, 03307-005",
                                "08007707722"
                            ),//
                            Place(
                                "Boulevard Shopping Bauru",
                                LatLng(-22.3164844,-49.0853087),
                                "R. Marcondes Salgado, Quadra 11 - Chácara das Flores, Bauru - SP, 17013-113",
                                "(14) 3500-8951"
                            ),
                        )

                        googleMap.setInfoWindowAdapter(MarkerInfoAdapter(context))

                        places.forEach { place ->
                            val marker = googleMap.addMarker(
                                MarkerOptions()
                                    .title(place.name)
                                    .snippet(place.address)
                                    .position(place.latLng)
                            )
                            marker?.tag = place
                        }

                        googleMap.setOnMapLoadedCallback {
                            val bounds = LatLngBounds.builder()
                            places.forEach { bounds.include(it.latLng) }
                            googleMap.moveCamera(
                                CameraUpdateFactory.newLatLngBounds(
                                    bounds.build(),
                                    100
                                )
                            )
                        }
                    }
                }
            }
        )
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(
                    bottomStart = 10.dp,
                    bottomEnd = 10.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF84C0FF)
                )
            )
            {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.perfilicon),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(start = 380.dp)
                            .clickable {navegacao!!.navigate("perfil")}
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier
                    .width(195.dp)
                    .height(30.dp)
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Salas Multissensoriais",
                    fontFamily = FontFamily(Font(R.font.inter_bold)),
                    fontSize = 15.sp,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(443.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp),
                shape = RoundedCornerShape(
                    topStart  = 10.dp,
                    topEnd =  10.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            )
            {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 10.dp, start = 10.dp)
                )
                {
                    Text(
                        text = "Sala Multissensorial Aeroporto de Congonhas",
                        color = Color(0xff000000),
                        fontFamily = FontFamily(Font(R.font.inter_bold)),
                        fontSize = 15.sp,
                    )
                    Row(
                        modifier = Modifier
                            .padding(start = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        Image(
                            painter = painterResource(R.drawable.mappin),
                            contentDescription = "",
                            modifier = Modifier
                                .size(10.dp)
                        )
                        Text(
                            text = "Av. Washington Luís, s/n - Campo Belo, São Paulo - SP, 04626-911",
                            color = Color(0xff000000),
                            fontFamily = FontFamily(Font(R.font.inter_normal)),
                            fontSize = 12.sp,
                        )

                    }
                    Row(
                        modifier = Modifier
                            .padding(start = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        Image(
                            painter = painterResource(R.drawable.phone),
                            contentDescription = "",
                            modifier = Modifier
                                .size(10.dp)
                        )
                        Text(
                            text = "(11) 5090-9000",
                            color = Color(0xff000000),
                            fontFamily = FontFamily(Font(R.font.inter_normal)),
                            fontSize = 12.sp,
                        )
                        //(11) 5090-9000
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Sala Multissensorial Terminal Santo Amaro",
                        color = Color(0xff000000),
                        fontFamily = FontFamily(Font(R.font.inter_bold)),
                        fontSize = 15.sp,
                    )
                    Row(
                        modifier = Modifier
                            .padding(start = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        Image(
                            painter = painterResource(R.drawable.mappin),
                            contentDescription = "",
                            modifier = Modifier
                                .size(10.dp)
                        )
                        Text(
                            text = "Santo Amaro, São Paulo - SP, 04753-060",
                            color = Color(0xff000000),
                            fontFamily = FontFamily(Font(R.font.inter_normal)),
                            fontSize = 12.sp,
                        )

                    }
                    Row(
                        modifier = Modifier
                            .padding(start = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        Image(
                            painter = painterResource(R.drawable.phone),
                            contentDescription = "",
                            modifier = Modifier
                                .size(10.dp)
                        )
                        Text(
                            text = "(11) 5685-7071",
                            color = Color(0xff000000),
                            fontFamily = FontFamily(Font(R.font.inter_normal)),
                            fontSize = 12.sp,
                        )
                    }
                    //

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Sala Multissensorial Estação Barra Funda",
                        color = Color(0xff000000),
                        fontFamily = FontFamily(Font(R.font.inter_bold)),
                        fontSize = 15.sp,
                    )
                    Row(
                        modifier = Modifier
                            .padding(start = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        Image(
                            painter = painterResource(R.drawable.mappin),
                            contentDescription = "",
                            modifier = Modifier
                                .size(12.dp)
                        )
                        Text(
                            text = "R. Dr. Bento Teobaldo Ferraz, 119 - Barra Funda, São Paulo - SP, 01140-070",
                            color = Color(0xff000000),
                            fontFamily = FontFamily(Font(R.font.inter_normal)),
                            fontSize = 12.sp,
                        )

                    }
                    Row(
                        modifier = Modifier
                            .padding(start = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        Image(
                            painter = painterResource(R.drawable.phone),
                            contentDescription = "",
                            modifier = Modifier
                                .size(12.dp)
                        )
                        Text(
                            text = "(11) 3392-2455",
                            color = Color(0xff000000),
                            fontFamily = FontFamily(Font(R.font.inter_normal)),
                            fontSize = 12.sp,
                        )
                    }
                }

            }

            //Barra inferior
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp),
                shape = RoundedCornerShape(
                    topEnd = 10.dp,
                    topStart = 10.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF84C0FF)
                )
            )
            {
                Row(
                    modifier = Modifier
                        .padding(top = 14.dp)
                        .fillMaxSize(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.calendario),
                        contentDescription = "",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {navegacao!!.navigate("calendario")}
                    )

                    Spacer(modifier = Modifier.width(80.dp))

                    Image(
                        painter = painterResource(R.drawable.casinha),
                        contentDescription = "",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { navegacao!!.navigate("home") }
                    )

                    Spacer(modifier = Modifier.width(80.dp))

                    Image(
                        painter = painterResource(R.drawable.medicina),
                        contentDescription = "",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { navegacao!!.navigate("listaRegistro") }
                    )
                    Spacer(modifier = Modifier.width(80.dp))

                    Image(
                        painter = painterResource(R.drawable.local),
                        contentDescription = "",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {navegacao!!.navigate("mapa")}
                    )
                }
            }


        }
    }
}

@Preview
@Composable
private fun MapsPreview() {
    Maps(null)
}


