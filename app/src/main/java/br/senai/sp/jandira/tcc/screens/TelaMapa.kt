package br.senai.sp.jandira.tcc.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.tcc.R


@Composable
fun TelaMapa(navegacao: NavHostController?) {



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0XFFADD8FF))
        ) {}
        Image(
            painter = painterResource(R.drawable.icons_fundo),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(
                    color = Color(0xFF42A5F5),
                    shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                )
                .align (Alignment.TopCenter)
        ) {
            Row (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.sininho),
                    contentDescription = "Notificações",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {navegacao!!.navigate("notificacao")}
                )

                Icon(
                    painter = painterResource(id = R.drawable.perfilicon),
                    contentDescription = "Perfil",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {navegacao!!.navigate("perfil")}
                )
            }
        }

        Box(
            modifier = Modifier
                .align (Alignment.TopCenter)
                .padding(top = 80.dp)
                .fillMaxWidth()
        ){
            var textoPesquisa = remember { mutableStateOf("") }

            TextField(
                value = textoPesquisa.value,
                onValueChange = {textoPesquisa.value = it},
                placeholder = { Text("digite sua localização...", color = Color.Gray) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Buscar",
                        tint = Color.Gray
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                    ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(52.dp)
                    .align (Alignment.Center)
                    .background(Color.White, RoundedCornerShape(16.dp))
                )
        }

        Column (
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 160.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){}
    }
}

@Preview
@Composable
private fun TelaMapaPreview() {
    TelaMapa(null)
}