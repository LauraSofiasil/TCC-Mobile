package br.senai.sp.jandira.tcc.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.tcc.R

@Composable
fun TelaInicial2() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        Image(
            painter = painterResource(R.drawable.fundo),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
            )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            Image(
                painter = painterResource(R.drawable.icons_puzzles),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 60.dp)
                .background(Color.Transparent),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(R.drawable.logo2),
                contentDescription = "",
                modifier = Modifier
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                shape = RoundedCornerShape(
                    topEnd = 90.dp,
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ){}
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(R.drawable.icon),
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 210.dp)
                    .size(350.dp)
            )
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff1892FF)
                ),
                shape = RoundedCornerShape(
                    10.dp
                ),
                modifier = Modifier
                    .width(140.dp)
                    .height(43.dp)
                    .shadow(
                        elevation = 7.dp, // Altura da sombra
                        shape = RoundedCornerShape(10.dp), // Formato da sombra
                        clip = false, // Mantenha a sombra visível
                    )
            ) {
                Text(
                    text = "LOGIN",
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.White,
                    fontSize = 15.sp
                )
            }
            Spacer(modifier = Modifier.height(41.dp))

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff1892FF)
                ),
                shape = RoundedCornerShape(
                    10.dp
                ),
                modifier = Modifier
                    .width(140.dp)
                    .height(43.dp)
            ) {
                Text(
                    text = "CADASTRAR",
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.White,
                    fontSize = 15.sp
                )
            }
        }
    }
}

@Composable
@Preview
private fun TelaInicial2Preview() {
    TelaInicial2()
}