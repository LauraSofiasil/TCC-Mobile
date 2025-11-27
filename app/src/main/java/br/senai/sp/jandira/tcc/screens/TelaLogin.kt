package br.senai.sp.jandira.tcc.screens

import android.content.Context
import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Info
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
import androidx.compose.ui.platform.LocalContext
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
import br.senai.sp.jandira.tcc.model.Usuario
import br.senai.sp.jandira.tcc.service.RetrofitFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.await


@Composable
fun TelaLogin(navegacao: NavHostController?) {

    var emailUs by remember { mutableStateOf("") }
    var senhaUs by remember { mutableStateOf("") }

    var isEmailError by remember { mutableStateOf(false) }
    var isSenhaError by remember { mutableStateOf(false) }

    //Variável determina se a menssagem deve aparecer
    var mostrarMenssagemSucesso by remember { mutableStateOf(false) }

    //Faz a validação de email e senha
    fun validar(): Boolean{
        isEmailError = !Patterns.EMAIL_ADDRESS.matcher(emailUs).matches()
        isSenhaError = senhaUs.length < 3
        return !isSenhaError && !isEmailError
    }

    //Context -> objeto, possui SharedPreferences
    val context = LocalContext.current

    //Armazena os dados localmente, salva no SharedPreferences assim que o login é sucedido
    fun salvarUsuario(context: Context, email: String, senha: String) {
        val dados = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        dados.edit()
            .putString("user_email", email)
            .putString("user_password", senha)
            .apply()
    }

    fun lerUsuario(context: Context): Pair<String?, String?> {
        val dados = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val email = dados.getString("user_email", null)
        val senha = dados.getString("user_password", null)
        return Pair(email, senha)
    }

    val usuarioApi = RetrofitFactory().getUsuarioService()

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
                        ),
                        isError = isEmailError, //Estilo de erro vermelho
                        supportingText = {
                            if (isEmailError){
                                Text(text = "Email é obrigatório!")
                            }
                        },
                        trailingIcon = {
                            if(isEmailError){
                                Icon(imageVector = Icons.Default.Info, contentDescription = "Alerta")
                            }
                        }
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
                        value = senhaUs,
                        onValueChange = {senhaUs = it},
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
                        ),
                        isError = isSenhaError, //Estilo de erro vermelho
                        supportingText = {
                            if (isSenhaError){
                                Text(text = "Senha é obrigatório!")
                            }
                        },
                        trailingIcon = {
                            if(isSenhaError){
                                Icon(imageVector = Icons.Default.Info, contentDescription = "Alerta")
                            }
                        }
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
                            .clickable { navegacao!!.navigate("cadastro") }
                    )
                    Text(
                        text = "Esqueci minha senha",
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 10.sp,
                        color = Color.White,
                        modifier = Modifier
                            .height(20.dp)
                            .clickable { navegacao!!.navigate("recuperacao")}
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
                        onClick = {
                            if(validar()){
                                val body = LoginUsuario(
                                    email = emailUs,
                                    senha = senhaUs
                                )
                                GlobalScope.launch(Dispatchers.IO) {
                                    val usuario = usuarioApi
                                        .loginUsuario(body)
                                        .await()
                                    println("Sucesso uhuuuull")
                                    if (usuario.status_code == 200) {
                                    salvarUsuario(context, emailUs, senhaUs)
                                    withContext(Dispatchers.Main) {
                                        navegacao?.navigate("home")
                                    }
                                    }else{
                                        println("Erro")
                                    }
                                }
                            }else{
                                println("Deu ERRADOOO")
                            }
                        },
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
        if (mostrarMenssagemSucesso){
            AlertDialog(
                onDismissRequest = {
                    mostrarMenssagemSucesso = false
                    emailUs = ""
                    senhaUs = ""
                },
                text = {
                    Text(text = "Seja bem-vindo $emailUs!!")
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            emailUs = ""
                            senhaUs = ""
                            mostrarMenssagemSucesso = false
                        }
                    ) {}
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            navegacao!!.navigate("home")
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
private fun telaLoginPreview() {
    TelaLogin(null)
}