package br.senai.sp.jandira.tcc.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.tcc.R

@Composable
fun TelaCalendario(navegacao: NavHostController?) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0XFFADD8FF))
        ) {}
        Image(
            painter = painterResource(R.drawable.icons_fundo),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .padding(horizontal = 20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.sininho),
                    contentDescription = "",
                    modifier = Modifier.size(28.dp)
                )
                Image(
                    painter = painterResource(R.drawable.perfilicon),
                    contentDescription = "",
                    modifier = Modifier.size(28.dp)
                        .clickable { navegacao!!.navigate("perfil") }

                )
            }

            Spacer(modifier = Modifier.height(25.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(15.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(10.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Setembro 2025",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily(Font(R.font.inter_normal))
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        listOf("DOM", "SEG", "TER", "QUA", "QUI", "SEX", "SAB").forEach {
                            Text(it, color = Color(0xFFB0B0B0), fontSize = 12.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    val dias = (1..30).toList()
                    Column {
                        dias.chunked(7).forEach { semana ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                horizontalArrangement = Arrangement.SpaceAround
                            ) {
                                semana.forEach { dia ->
                                    val isSelecionado = dia == 19
                                    Box(
                                        modifier = Modifier
                                            .size(36.dp)
                                            .clip(CircleShape)
                                            .background(
                                                if (isSelecionado) Color(0xFFE86C3F)
                                                else Color.Transparent
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = dia.toString(),
                                            color = if (isSelecionado) Color.White else Color.Black,
                                            fontSize = 14.sp,
                                            fontFamily = FontFamily(Font(R.font.inter_normal))
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // CARD AGENDA
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(15.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(10.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "19 de setembro de 2025",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.inter_normal))
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    repeat(10) { i ->
                        Text(
                            text = "${9 + i}h",
                            color = Color(0xFFB0B0B0),
                            fontSize = 13.sp,
                            modifier = Modifier.padding(vertical = 3.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF61A7FF)
                        ),
                        modifier = Modifier
                            .align(Alignment.End)
                            .clip(RoundedCornerShape(12.dp))
                    ) {
                        Text(
                            text = "Crise",
                            color = Color.White,
                            fontFamily = FontFamily(Font(R.font.inter_normal))
                        )
                    }
                }
            }

            Card(
                modifier = Modifier
                    .padding(top = 80.dp)
                    .fillMaxWidth()
                    .height(65.dp),
                shape = RoundedCornerShape(
                    topEnd = 10.dp,
                    topStart = 10.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xBA1892FF)
                )
            ) {
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
                            .clickable { navegacao!!.navigate("") }
                    )
                }
            }

        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCalendario() {
    TelaCalendario(null)
}
