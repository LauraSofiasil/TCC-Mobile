package br.senai.sp.jandira.tcc.screens

import android.util.Log
import android.util.Patterns
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import br.senai.sp.jandira.tcc.model.Usuario
import br.senai.sp.jandira.tcc.service.Conexao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await

@Composable
fun TelaCadastro(navegacao: NavHostController?) {

    var nomeUsuario by remember { mutableStateOf("Digite seu nome completo...") }
    var telefone by remember { mutableStateOf("Digite seu telefone...") }
    var tutelado by remember { mutableStateOf("Digite o nome completo...") }
    var email by remember { mutableStateOf("Digite seu email...") }
    var senha by remember { mutableStateOf("Digite sua senha...") }
    var confirmar by remember { mutableStateOf("Digite sua senha...") }

    var isNomeError by remember { mutableStateOf(false) }
    var isEmailError by remember { mutableStateOf(false) }

    //Variável determina se a menssagem deve aparecer
    var mostrarMenssagemSucesso by remember { mutableStateOf(false) }

    fun validar(): Boolean{
        isNomeError = nomeUsuario.length < 3
        isEmailError = !Patterns.EMAIL_ADDRESS.matcher(email).matches()
        return !isNomeError && !isEmailError
    }

    //Criar uma estância da conexão com a API
    val usuarioAPI = Conexao().getUsuarioService()

    //Inicio da TELA
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
//Nome
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
                        value = nomeUsuario,
                        onValueChange = {nomeUsuario = it},
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
//Telefone
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
                        value = telefone,
                        onValueChange = {telefone = it},
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
//Tutelado
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
                        value = tutelado,
                        onValueChange = {tutelado = it},
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
//Email
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
                        value = email,
                        onValueChange = {email = it},
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
//Senha
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
                        value = senha,
                        onValueChange = {senha = it},
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
//Confirmar
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
                        value = confirmar,
                        onValueChange = {confirmar = it},
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
                    onClick = {
                        if(validar()){
                            val usuario = Usuario(
                                id = null,
                                nome = nomeUsuario,
                                telefone = telefone,
                                tutelado = tutelado,
                                email = email,
                                senha = senha
                            )
                            GlobalScope.launch(Dispatchers.IO) {
                                val usuarioNovo = usuarioAPI
                                    .cadastrarUsuario(usuario)
                                    .await()

                                mostrarMenssagemSucesso = true
                            }
                        }else{
                            println("*********Dados incorretos!")
                        }
                    },
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
        if (mostrarMenssagemSucesso){
            AlertDialog(
                onDismissRequest = {
                    mostrarMenssagemSucesso = false
                    nomeUsuario = ""
                    email = ""
                },
                title = {
                    Text(text = "Sucesso")
                },
                text = {
                    Text(text = "Usuario $nomeUsuario gravado com sucesso!\nDeseja cadastrar outro cliente?")
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            nomeUsuario = ""
                            email = ""
                            mostrarMenssagemSucesso = false
                        }
                    ) {
                        Text(text = "Sim")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            navegacao!!.navigate("inicio2")
















                        }
                    ) {
                        Text(text = "Não")
                    }
                }
            )
        }
    }
}

@Preview
@Composable
private fun TelaCadastroPreview() {
    TelaCadastro(null)
}