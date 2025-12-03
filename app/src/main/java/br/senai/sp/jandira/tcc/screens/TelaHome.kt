package br.senai.sp.jandira.tcc.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.tcc.R

@Composable
fun TelaHome(navegacao: NavHostController?) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        //Meio
        Column(
            modifier = Modifier.fillMaxWidth()
                .height(280.dp)
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF60A5FA),
                            Color(0xFF5EEAD4)
                        ),
                        start = Offset(0f, 0f),
                        end = Offset(300f, 900f)
                    )
                )
        )
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 70.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo2),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(170.dp)
                        .padding(start = 20.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "BEM-VINDO AO",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily(Font(R.font.inter_bold)),
                        color = Color.White
                    )
                    Text(
                        text = "TEAJUDA",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily(Font(R.font.inter_bold)),
                        color = Color.White
                    )
                }

            }
        }

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            //Barra superior
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp),
                shape = RoundedCornerShape(
                    bottomEnd = 10.dp,
                    bottomStart = 10.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF35A1FF)
                )
            )
            {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 25.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.perfilicon),
                        contentDescription = "Perfil",
                        modifier = Modifier
                            .size(40.dp)
                            .clickable { navegacao?.navigate("perfil")},
                        tint = Color.Unspecified
                    )
                }}

            //Meio do meio
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 200.dp)
                    .height(520.dp),
                shape = RoundedCornerShape(
                    topEnd = 20.dp,
                    topStart = 20.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFFFFFF)
                )
            )
            {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Transparent),
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    Text(
                        text = "Nossos Recursos",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily(Font(R.font.inter_bold)),
                        color = Color.Black,
                        modifier = Modifier
                            .padding(top = 20.dp) //
                    )

                    Spacer(modifier = Modifier.height(36.dp))

                    //Card mapa
                    Card(
                        modifier = Modifier
                            .width(360.dp)
                            .height(110.dp)
                            .clickable {navegacao!!.navigate("mapa")},
                        shape = RoundedCornerShape(
                            topEnd = 10.dp,
                            topStart = 10.dp,
                            bottomEnd = 10.dp,
                            bottomStart = 10.dp
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFEFEFEF)
                        )
                    )
                    {
                        Row(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(start = 10.dp)
                            )
                            {
                                Text(
                                    text = "Mapa com",
                                    fontSize = 22.sp,
                                    fontFamily = FontFamily(Font(R.font.inter_normal)),
                                    color = Color.Black,
                                    modifier = Modifier
                                        .padding(top = 20.dp)
                                )
                                Text(
                                    text = "Salas multissensoriais",
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily(Font(R.font.inter_normal)),
                                    color = Color.Black,
                                    modifier = Modifier
                                        .padding(top = 5.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(26.dp))

                            Image(
                                painter = painterResource(R.drawable.teteiconmap),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(90.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(27.dp))

                    //Card registros
                    Card(
                        modifier = Modifier
                            .width(360.dp)
                            .height(110.dp)
                            .clickable {navegacao!!.navigate("registro")},
                        shape = RoundedCornerShape(
                            topEnd = 10.dp,
                            topStart = 10.dp,
                            bottomEnd = 10.dp,
                            bottomStart = 10.dp
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFEFEFEF)
                        )
                    )
                    {
                        Row(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(start = 10.dp)
                            )
                            {
                                Text(
                                    text = "Registros",
                                    fontSize = 22.sp,
                                    fontFamily = FontFamily(Font(R.font.inter_normal)),
                                    color = Color.Black,
                                    modifier = Modifier
                                        .padding(top = 20.dp)
                                )
                                Text(
                                    text = "Comportamentais",
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily(Font(R.font.inter_normal)),
                                    color = Color.Black,
                                    modifier = Modifier
                                        .padding(top = 5.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(66.dp))

                            Image(
                                painter = painterResource(R.drawable.iconregistro2),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(90.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(27.dp))

                    //Card calendario
                    Card(
                        modifier = Modifier
                            .width(360.dp)
                            .height(110.dp)
                            .clickable {navegacao!!.navigate("calendario")},
                        shape = RoundedCornerShape(
                            topEnd = 10.dp,
                            topStart = 10.dp,
                            bottomEnd = 10.dp,
                            bottomStart = 10.dp
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFEFEFEF)
                        )
                    )
                    {
                        Row(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(start = 10.dp)
                            )
                            {
                                Text(
                                    text = "Calendário",
                                    fontSize = 22.sp,
                                    fontFamily = FontFamily(Font(R.font.inter_normal)),
                                    color = Color.Black,
                                    modifier = Modifier
                                        .padding(top = 20.dp)
                                )
                                Text(
                                    text = "Adicionar lembretes",
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily(Font(R.font.inter_normal)),
                                    color = Color.Black,
                                    modifier = Modifier
                                        .padding(top = 5.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(50.dp))

                            Image(
                                painter = painterResource(R.drawable.iconcalendar2),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(90.dp)
                            )
                        }
                    }

                }
            }

            //Barra Inferior
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
                    .height(65.dp),
                shape = RoundedCornerShape(
                    topEnd = 10.dp,
                    topStart = 10.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF35A1FF)
                )
            )
            {
                Row(
                    modifier = Modifier
                        .padding(top = 18.dp)
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




@Composable
@Preview()
private fun TelaHomePreview() {
    TelaHome(null)
}
