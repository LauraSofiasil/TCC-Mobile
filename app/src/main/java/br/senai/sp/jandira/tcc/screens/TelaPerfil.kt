package br.senai.sp.jandira.tcc.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.tcc.R
import br.senai.sp.jandira.tcc.model.LoginUsuario

@Composable
fun TelaPerfil(navegacao: NavHostController?) {

    val context = LocalContext.current

    val dados = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    val emailSalvo = dados.getString("user_email", "") ?: ""
    val senhaSalva = dados.getString("user_password", "") ?: ""

    var nomeUsuario = remember{ mutableStateOf(value = "Carolina Silva")}
    var emailUsuario = remember{ mutableStateOf(value = emailSalvo) }
    var senhaUsuario = remember{ mutableStateOf(value = senhaSalva) }


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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            //Foto perfil
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .padding(top = 23.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(R.drawable.perfil),
                    contentDescription = "",
                    modifier = Modifier
                        .size(150.dp)
                )

                Text(
                    text = "Perfil",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontSize = 40.sp,
                    modifier = Modifier
                        .padding(top = 10.dp)
                )

                //Campos
                Card(
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .width(300.dp)
                        .height(499.dp),
                    shape = RoundedCornerShape(
                        topEnd = 10.dp,
                        topStart = 10.dp,
                        bottomEnd = 10.dp,
                        bottomStart = 10.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White), //Do tamanho do card
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Column(
                            modifier = Modifier
                                .padding(top = 70.dp)
                                .fillMaxHeight()
                                .width(250.dp)
                                .background(Color.White) //Contém os campos
                        ) {
                            Column() {
                                Text(
                                    text = "Nome",
                                    color = Color(0xFF000000),
                                    fontFamily = FontFamily(Font(R.font.inter_normal)),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier
                                        .padding(start = 10.dp)
                                )

                                Spacer(modifier = Modifier.height(5.dp))

                                TextField(
                                    value = nomeUsuario.value,
                                    onValueChange = {nomeUsuario.value = it},
                                    colors = TextFieldDefaults.colors(
                                        focusedTextColor = Color(0xff949494), //Cor do texto digitado - usuário clicou
                                        unfocusedTextColor = Color(0xff949494), //Cor do texto digitado - usuário não clicou
                                        cursorColor = Color.Black, //Cor do cursor enquanto digita
                                        focusedContainerColor = Color(0xffffffff), //Cor do fundo - usuário clicou
                                        unfocusedContainerColor = Color(0xffffffff), //Cor do fundo - usuário não clicou
                                        focusedIndicatorColor = Color(0xff0D5692), //Cor da linha de baixo - usuário clicou
                                        unfocusedIndicatorColor = Color(0xff0D5692), //Cor da linha de baixo - usuário não clicou

                                    ),
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
                                        keyboardType = KeyboardType.Text,
                                        imeAction = ImeAction.Next
                                    )
                                )
                            }

                            Spacer(modifier = Modifier.height(33.dp))

                            Column() {
                                Text(
                                    text = "Email",
                                    color = Color(0xff000000),
                                    fontFamily = FontFamily(Font(R.font.inter_normal)),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier
                                        .padding(start = 5.dp)
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                TextField(
                                    value = emailUsuario.value,
                                    onValueChange = {emailUsuario.value = it},
                                    colors = TextFieldDefaults.colors(
                                        focusedTextColor = Color(0xff949494), //Cor do texto digitado - usuário clicou
                                        unfocusedTextColor = Color(0xff949494), //Cor do texto digitado - usuário não clicou
                                        cursorColor = Color.Black, //Cor do cursor enquanto digita
                                        focusedContainerColor = Color(0xffffffff), //Cor do fundo - usuário clicou
                                        unfocusedContainerColor = Color(0xffffffff), //Cor do fundo - usuário não clicou
                                        focusedIndicatorColor = Color(0xff0D5692), //Cor da linha de baixo - usuário clicou
                                        unfocusedIndicatorColor = Color(0xff0D5692) //Cor da linha de baixo - usuário não clicou
                                    ),
                                    leadingIcon = {
                                        Icon(
                                            imageVector = Icons.Default.Email,
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
                                        keyboardType = KeyboardType.Email,
                                        imeAction = ImeAction.Next
                                    )
                                )
                            }

                            Spacer(modifier = Modifier.height(33.dp))

                            Column() {
                                Text(
                                    text = "Senha",
                                    color = Color(0xff000000),
                                    fontFamily = FontFamily(Font(R.font.inter_normal)),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier
                                        .padding(start = 5.dp)
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                TextField(
                                    value = senhaUsuario.value,
                                    onValueChange = {senhaUsuario.value = it},
                                    colors = TextFieldDefaults.colors(
                                        focusedTextColor = Color(0xff949494), //Cor do texto digitado - usuário clicou
                                        unfocusedTextColor = Color(0xff949494), //Cor do texto digitado - usuário não clicou
                                        cursorColor = Color.Black, //Cor do cursor enquanto digita
                                        focusedContainerColor = Color(0xffffffff), //Cor do fundo - usuário clicou
                                        unfocusedContainerColor = Color(0xffffffff), //Cor do fundo - usuário não clicou
                                        focusedIndicatorColor = Color(0xff0D5692), //Cor da linha de baixo - usuário clicou
                                        unfocusedIndicatorColor = Color(0xff0D5692) //Cor da linha de baixo - usuário não clicou
                                    ),
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
                                    visualTransformation = PasswordVisualTransformation(), //Teste para esconder senha
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Password,
                                        imeAction = ImeAction.Next
                                    )
                                )
                            }
                        }
                    }
                }

                //Barra Inferior
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
}

@Preview
@Composable
private fun TelaHomePreview() {
    TelaPerfil(null)
}