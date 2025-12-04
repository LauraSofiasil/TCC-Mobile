package br.senai.sp.jandira.tcc.screens

import android.content.Context
import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
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
import br.senai.sp.jandira.tcc.service.RetrofitFactory
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.platform.LocalContext
import br.senai.sp.jandira.tcc.model.LoginUsuario
import br.senai.sp.jandira.tcc.model.Registro
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.await


@Composable
fun TelaRegistro(navegacao: NavHostController?) {

    var texto by remember { mutableStateOf("") }
    var data by remember { mutableStateOf("") }
    var isTextoError by remember { mutableStateOf(false) }
    var isDataError by remember { mutableStateOf(false) }

    fun validar(): Boolean{
        isTextoError = texto.length <3
        isDataError = data.length < 3
        return !isTextoError && !isDataError
    }

    val context = LocalContext.current

    //Armazena os dados localmente, salva no SharedPreferences assim que o login é sucedido
    fun salvarRegistro(context: Context, texto: String, data: String) {
        val dados = context.getSharedPreferences("registro_dados", Context.MODE_PRIVATE)
        dados.edit()
            .putString("registro_texto", texto)
            .putString("registro_data", data)
            .apply()
    }

    var mostrarMenssagemSucesso by remember { mutableStateOf(false) }

    //Criar uma estância da conexão com a API
    val registroAPI = RetrofitFactory().getRegistroService()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFDDE7F0))
    ){

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 70.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Registre",
                color = Color(0XFF042947),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Formulário de Cadastro
            OutlinedTextField(
                value = texto,
                onValueChange = { texto = it },
                label = { Text("") },
                modifier = Modifier.width(360.dp).height(540.dp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = Color.Gray,
                    unfocusedLabelColor = Color.Gray,
                    focusedLabelColor = Color.Gray
                ),
                isError = isTextoError, //Estilo de erro vermelho
                supportingText = {
                    if (isTextoError){
                        Text(text = "Texto é obrigatório!")
                    }
                },
                trailingIcon = {
                    if(isTextoError){
                        Icon(imageVector = Icons.Default.Info, contentDescription = "Alerta")
                    }
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                OutlinedTextField(
                    value = data,
                    onValueChange = { data = it },
                    label = { Text("AAAA-MM-DD", fontSize = 10.sp) },
                    modifier = Modifier.width(125.dp).height(40.dp).padding(end = 15.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Gray,
                        unfocusedBorderColor = Color.Gray,
                        cursorColor = Color.Gray,
                        unfocusedLabelColor = Color.Gray,
                        focusedLabelColor = Color.Gray
                    ),
                    isError = isDataError, //Estilo de erro vermelho
                    supportingText = {
                        if (isDataError){
                            Text(text = "Data é obrigatório!")
                        }
                    },
                    trailingIcon = {
                        if(isDataError){
                            Icon(imageVector = Icons.Default.Info, contentDescription = "Alerta")
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if(validar()){
                            val body = Registro(
                                texto = texto,
                                data = data
                            )
                            GlobalScope.launch(Dispatchers.IO) {
                                val registro = registroAPI
                                    .cadastrarRegistro(body)
                                    .await()
                                println("Sucesso uhuuuull")
                                if (registro.status_code == 201) {
                                    salvarRegistro(context, texto, data)
                                    withContext(Dispatchers.Main) {
                                        navegacao?.navigate("listaRegistro")
                                    }
                                }else{
                                    println("Erro")
                                }
                            }
                        }else{
                            println("Deu ERRADOOO")
                        }
                    },
                    modifier = Modifier
                        .width(110.dp).padding(end = 15.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1892FF))
                ) {
                    Text("Salvar")
                }
            }
        }

        //Barras
        Column(
            modifier = Modifier
                .fillMaxSize()
        )
        {
            //Barra superior
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(
                    bottomStart = 10.dp,
                    bottomEnd = 10.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xff1892FF)
                )
            )
            {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.perfilicon),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(start = 380.dp)
                            .clickable { navegacao!!.navigate("perfil") }
                    )
                }
            }

            Spacer(modifier = Modifier.height(730.dp))

            //Barra inferior
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp),
                shape = RoundedCornerShape(
                    topEnd = 10.dp,
                    topStart = 10.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF1892FF)
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
                            .clickable { navegacao!!.navigate("calendario") }
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
                            .clickable { navegacao!!.navigate("registro") }
                    )
                    Spacer(modifier = Modifier.width(80.dp))

                    Image(
                        painter = painterResource(R.drawable.local),
                        contentDescription = "",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { navegacao!!.navigate("mapa") }
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun TelaRegistroPreview() {
    TelaRegistro(null)
}