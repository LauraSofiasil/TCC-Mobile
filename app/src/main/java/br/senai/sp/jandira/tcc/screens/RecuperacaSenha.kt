package br.senai.sp.jandira.tcc.screens

import android.util.Patterns
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.tcc.R
import br.senai.sp.jandira.tcc.model.LoginUsuario
import br.senai.sp.jandira.tcc.service.RetrofitFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.await

@Composable
fun RecuperacaoSenha(navegacao: NavHostController?) {

    var emailUs by remember { mutableStateOf("") }
    var isEmailError by remember { mutableStateOf(false) }

    val usuarioApi = RetrofitFactory().getRecuperacaoService()

    fun validar(): Boolean{
        isEmailError = !Patterns.EMAIL_ADDRESS.matcher(emailUs).matches()
        return !isEmailError
    }

    var mostrarMenssagemSucesso by remember { mutableStateOf(false) }


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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .padding(top = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.logo2),
                    contentDescription = "",
                    modifier = Modifier
                        .size(200.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "TEAjuda",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontSize = 36.sp
                )
            }

            Spacer(modifier = Modifier.height(71.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column() {
                    Text(
                        text = "Login",
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .padding(start = 5.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    TextField(
                        value = emailUs,
                        onValueChange = {emailUs = it},
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
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        )
                    )
                }

                Spacer(modifier = Modifier.height(34.dp))

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
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        )
                    )
                }

                Spacer(modifier = Modifier.height(50.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = " Não possui uma conta?",
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 10.sp,
                        color = Color.White,
                        modifier = Modifier
                            .height(20.dp)
                    )
                    Text(
                        text = "Esqueci minha senha",
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 10.sp,
                        color = Color.White,
                        modifier = Modifier
                            .height(20.dp)
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 90.dp)
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
                            .width(120.dp)
                            .height(45.dp)
                            .padding(end = 20.dp)

                    ) {
                        Text(
                            text = "Entrar",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color(0xff0D0D0C),
                            fontSize = 19.sp
                        )
                    }
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x99000000)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
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
                        value = emailUs,
                        onValueChange = {emailUs = it},
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

                    Button(
                        onClick = {
                            if(validar()){
                                val body = LoginUsuario(
                                    email = emailUs
                                )
                                GlobalScope.launch(Dispatchers.IO) {
                                    val recuperacao = usuarioApi
                                        .recuperacaoSenha(body)
                                        .await()
                                    println("Sucesso uhuuuull")
                                    mostrarMenssagemSucesso = true
                                }
                            }else{
                                println("Deu ERRADOOO")
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xff1892FF)
                        ),
                        shape = RoundedCornerShape(
                            10.dp
                        ),
                        modifier = Modifier
                            .padding(top = 30.dp)
                            .width(120.dp)
                            .height(43.dp)
                    ) {
                        Text(
                            text = "Redefinir",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color(0xffffffff),
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }

        if (mostrarMenssagemSucesso){
            AlertDialog(
                onDismissRequest = {
                    mostrarMenssagemSucesso = false
                    emailUs = ""
                },
                text = {
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            emailUs = ""
                            mostrarMenssagemSucesso = false
                        }
                    ) {}
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            navegacao!!.navigate("codigo")
                        }
                    ) {
                        Text(text = "Próximo")
                    }
                }
            )
        }
    }
}



@Preview
@Composable
private fun RecuperacaoSenhaPreview() {
    RecuperacaoSenha(null)
}