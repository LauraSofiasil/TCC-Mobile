package br.senai.sp.jandira.tcc.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
fun TelaInicial(navegacao: NavHostController?) {
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
                .padding(top = 81.dp)
        ) {
            Card(
                modifier = Modifier
                    .width(291.dp)
                    .height(311.dp),
                shape = RoundedCornerShape(
                    topEnd = 64.dp,
                    bottomEnd = 64.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {}
        }
        Image(
            painter = painterResource(R.drawable.cll),
            contentDescription = "",
            modifier = Modifier
                .size(435.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .padding(top = 500.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Tenha tudo sobre\ncontrole em casa!",
                color = Color(0xffFFFFFF),
                fontFamily = FontFamily(Font(R.font.heptaslab_regular)),
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(60.dp))

            Text(
                text = "DA MELHOR FORMA!",
                color = Color(0xffFFFFFF),
                fontFamily = FontFamily(Font(R.font.anton_regular)),
                fontSize = 36.sp,
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 780.dp)
                .background(Color.Transparent),
            horizontalAlignment = Alignment.End
        ) {
            Button(
                onClick = {
                    navegacao?.navigate("inicio2")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff1892FF)
                ),
                shape = RoundedCornerShape(
                    10.dp
                ),
                modifier = Modifier
                    .width(125.dp)
                    .height(43.dp)
                    .padding(end = 10.dp)
            ) {
                Text(
                    text = "PRÓXIMO",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontSize = 15.sp
                )
            }
        }
    }
}

@Preview
@Composable
private fun telaInicialPreview() {
    TelaInicial(null)
}