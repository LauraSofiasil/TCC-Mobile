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
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.tcc.R

@Composable
fun telaCadastro(modifier: Modifier = Modifier) {
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
        ) {
            //Logo
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .padding(top = 21.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.logo2),
                    contentDescription = "",
                    modifier = Modifier
                        .size(100.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "TEAjuda",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontSize = 35.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Cadaste-se",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            //Conteúdo
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column() {
                    Text(
                        text = "Nome",
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .padding(start = 5.dp)
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    TextField(
                        value = "Digite seu nome completo...",
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
                            .width(285.dp)
                            .height(48.dp),
                        shape = RoundedCornerShape(12.dp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        )
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Column() {
                    Text(
                        text = "Telefone",
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .padding(start = 5.dp)
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    TextField(
                        value = "Digite seu telefone...",
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
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Call,
                                contentDescription = "",
                                tint = Color(0x52000000),
                                modifier = Modifier
                                    .size(20.dp)
                            )
                        },
                        modifier = Modifier
                            .width(285.dp)
                            .height(48.dp),
                        shape = RoundedCornerShape(12.dp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        )
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Column() {
                    Text(
                        text = "Nome do tutelado",
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .padding(start = 5.dp)
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    TextField(
                        value = "Digite o nome completo...",
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
                            .width(285.dp)
                            .height(48.dp),
                        shape = RoundedCornerShape(12.dp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        )
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Column() {
                    Text(
                        text = "Email",
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .padding(start = 5.dp)
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    TextField(
                        value = "Digite seu email...",
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
                            .width(285.dp)
                            .height(48.dp),
                        shape = RoundedCornerShape(12.dp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        )
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Column() {
                    Text(
                        text = "Senha",
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .padding(start = 5.dp)
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    TextField(
                        value = "Digite sua senha...",
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
                            .width(285.dp)
                            .height(48.dp),
                        shape = RoundedCornerShape(12.dp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        )
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Column() {
                    Text(
                        text = "Confirmar Senha",
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .padding(start = 5.dp)
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    TextField(
                        value = "Digite sua senha...",
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
                            .width(285.dp)
                            .height(48.dp),
                        shape = RoundedCornerShape(12.dp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        )
                    )
                }
            }

            //Botão
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .background(Color.Transparent),
                horizontalAlignment = Alignment.End
            ) {
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xffffffff)
                    ),
                    shape = RoundedCornerShape(
                        10.dp
                    ),
                    modifier = Modifier
                        .width(135.dp)
                        .height(42.dp)
                        .padding(end = 20.dp)

                ) {
                    Text(
                        text = "Cadastrar",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color(0xff0D0D0C),
                        fontSize = 15.sp,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun telaCadastroPreview() {
    telaCadastro()
}