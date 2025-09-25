package br.senai.sp.jandira.tcc.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.tcc.R

@Composable
fun modalRecuperacao(
    email: String = "Digite seu email...",
){
        Card(
            modifier = Modifier
                .padding(bottom = 14.dp)
                .width(320.dp)
                .height(340.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xffFFFFFF))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                        .padding(top = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(R.drawable.logo2),
                        contentDescription = "",
                        modifier = Modifier
                            .size(74.dp)
                    )
                    Text(
                        text = "Esqueceu a senha?",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Enviaremos um E-mail com as instruções de",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.Black,
                            fontSize = 11.sp
                        )

                        Text(
                            text = "como redefinir senha",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.Black,
                            fontSize = 11.sp
                        )
                    }
                    TextField(
                        value = email,
                        onValueChange = {},
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color(0xff949494), //Cor do texto digitado - usuário clicou
                            unfocusedTextColor = Color(0xff949494), //Cor do texto digitado - usuário não clicou
                            cursorColor = Color.Black, //Cor do cursor enquanto digita
                            focusedContainerColor = Color(0xffffffff), //Cor do fundo - usuário clicou
                            unfocusedContainerColor = Color(0xffffffff), //Cor do fundo - usuário não clicou
                            focusedIndicatorColor = Color(0xFF949494), //Cor da linha de baixo - usuário clicou
                            unfocusedIndicatorColor = Color(0xFF949494) //Cor da linha de baixo - usuário não clicou
                        ),
                        placeholder = {
                            Text("Digite seu email...", color = Color(0xff949494))
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "",
                                tint = Color(0x52000000),
                                modifier = Modifier
                                    .size(30.dp)
                            )
                        },
                        modifier = Modifier
                            .width(285.dp)
                            .height(52.dp),
                        shape = RoundedCornerShape(12.dp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Next
                        )
                    )
                }
            }
        }
}

@Preview
@Composable
private fun modalRecuperacaoPreview() {
    modalRecuperacao()
}