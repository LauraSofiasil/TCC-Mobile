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
fun TelaPerfil(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0XFFADD8FF))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .padding(top = 23.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.perfil),
                    contentDescription = "",
                    modifier = Modifier
                        .size(150.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Perfil",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontSize = 40.sp
                )

                Spacer(modifier = Modifier.height(40.dp))

                Card(
                    modifier = Modifier
                        .width(300.dp)
                        .height(499.dp),
                    shape = RoundedCornerShape(
                        topEnd = 10.dp,
                        topStart = 10.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Column() {
                        Text(
                            text = "Login",
                            color = Color(0xff000000),
                            style = MaterialTheme.typography.titleLarge,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                                .padding(start = 5.dp)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        TextField(
                            value = "",
                            onValueChange = {},
                            colors = TextFieldDefaults.colors(
                                focusedTextColor = Color(0xff949494), //Cor do texto digitado - usuário clicou
                                unfocusedTextColor = Color(0xff949494), //Cor do texto digitado - usuário não clicou
                                cursorColor = Color.Black, //Cor do cursor enquanto digita
                                focusedContainerColor = Color(0xffffffff), //Cor do fundo - usuário clicou
                                unfocusedContainerColor = Color(0xffffffff), //Cor do fundo - usuário não clicou
                                focusedIndicatorColor = Color.Transparent, //Cor da linha de baixo - usuário clicou
                                unfocusedIndicatorColor = Color.Transparent //Cor da linha de baixo - usuário não clicou
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
                                        .size(20.dp)
                                )
                            },
                            modifier = Modifier
                                .width(300.dp),
                            shape = RoundedCornerShape(12.dp),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            )
                        )
                    }

                    Column() {
                        Text(
                            text = "Senha",
                            color = Color(0xff000000),
                            style = MaterialTheme.typography.titleLarge,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                                .padding(start = 5.dp)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        TextField(
                            value = "",
                            onValueChange = {},
                            colors = TextFieldDefaults.colors(
                                focusedTextColor = Color(0xff949494), //Cor do texto digitado - usuário clicou
                                unfocusedTextColor = Color(0xff949494), //Cor do texto digitado - usuário não clicou
                                cursorColor = Color.Black, //Cor do cursor enquanto digita
                                focusedContainerColor = Color(0xffffffff), //Cor do fundo - usuário clicou
                                unfocusedContainerColor = Color(0xffffffff), //Cor do fundo - usuário não clicou
                                focusedIndicatorColor = Color(0xff0D5692), //Cor da linha de baixo - usuário clicou
                                unfocusedIndicatorColor = Color(0xff0D5692) //Cor da linha de baixo - usuário não clicou
                            ),
                            placeholder = {
                                Text("Digite sua senha...", color = Color(0xff949494))
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Lock,
                                    contentDescription = "",
                                    tint = Color(0x52000000),
                                    modifier = Modifier
                                        .size(20.dp)
                                )
                            },
                            modifier = Modifier
                                .width(300.dp),
                            shape = RoundedCornerShape(12.dp),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(50.dp))


                }

                Spacer(modifier = Modifier.height(20.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(65.dp),
                    shape = RoundedCornerShape(
                        topEnd = 10.dp,
                        topStart = 10.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xBA1892FF)
                    )
                ) { }
            }
        }
    }
}

@Preview
@Composable
private fun TelaHomePreview() {
    TelaPerfil()
}