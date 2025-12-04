package br.senai.sp.jandira.tcc.screens

import android.util.Log
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.tcc.R
import br.senai.sp.jandira.tcc.model.Registro
import br.senai.sp.jandira.tcc.model.RegistroResult // Importe o modelo da lista
import br.senai.sp.jandira.tcc.service.RetrofitFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.await


@Composable
fun ListarRegistro(navegacao: NavHostController?) {

    // 1. Estados Reativos para a UI
    var registroList by remember { mutableStateOf(listOf<Registro>()) }
    var isLoading by remember { mutableStateOf(true) }
    var hasError by remember { mutableStateOf(false) }

    val registroAPI = RetrofitFactory().getRegistroService()

    // 2. Chamada à API (Executada Apenas uma Vez)
    LaunchedEffect(true) {
        // Garante que a chamada de rede está fora da Main Thread
        withContext(Dispatchers.IO) {
            try {
                // Chama a função da API e usa .await()
                val responseBody = registroAPI
                    .listarRegistros() // Use a função correta
                    .await()

                // Verifica o corpo da resposta
                if (responseBody.status_code == 200 && responseBody.registros != null) {
                    registroList = responseBody.registros
                    Log.e("Listagem", "Registros carregados: ${registroList.size}")
                    hasError = false
                } else {
                    Log.e("Listagem", "Erro: Código ${responseBody.status_code}")
                    hasError = true
                    registroList = emptyList()
                }

            } catch (e: Exception) {
                // Trata falhas de rede/deserialização
                Log.e("Listagem", "Falha na requisição: ${e.message}")
                hasError = true
                registroList = emptyList()
            } finally {
                isLoading = false // Finaliza o carregamento
            }
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFDDE7F0))
            .padding(top = 70.dp, bottom = 65.dp) // Adiciona padding para as barras
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Seus Registros",
                color = Color(0XFF042947),
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 20.dp)
            )

            // 3. Exibição da Lista com Tratamento de Estado
            when {
                isLoading -> {
                    // Indicador de Carregamento
                    CircularProgressIndicator(modifier = Modifier.padding(top = 50.dp))
                    Text("Carregando...", modifier = Modifier.padding(top = 16.dp))
                }
                hasError -> {
                    Text("Não foi possível carregar os dados. Verifique a conexão.", color = Color.Red, modifier = Modifier.padding(24.dp))
                }
                registroList.isEmpty() -> {
                    Text("Nenhum registro encontrado.", modifier = Modifier.padding(24.dp))
                }
                else -> {
                    // LazyColumn para a lista de registros
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(registroList) { registro ->
                            CardRegistro(registro = registro)
                        }
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 10.dp),
            ) {
                Button(
                    onClick = { navegacao?.navigate("registro") },
                    shape = RoundedCornerShape(100.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1892FF)),
                    modifier = Modifier
                        .padding(top = 30.dp, start = 340.dp)
                ) {
                    Text("+")
                }
            }
        }
    }

    // Componentes de Barra (mantidos fora do Box para controle de layout)
    BarrasUI(navegacao = navegacao)
}

// Composable para o item individual (melhorado para exibir mais dados)
@Composable
fun CardRegistro(registro: Registro) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 20.dp)
            ){
                Image(
                    painter = painterResource(id = R.drawable.fotocard),
                    contentDescription = "Imagem",
                    modifier = Modifier.size(40.dp).padding(top = 10.dp)
                )
            }
            Column(modifier = Modifier.padding(12.dp)) {
                // Exibe o texto
                Text(
                    text = registro.texto,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(4.dp))
                // Exibe a data (que já vem formatada do seu Model Node.js)
                Text(
                    text = "Data: ${registro.data}",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

// Extraído as barras para manter a UI principal mais limpa
@Composable
fun BarrasUI(navegacao: NavHostController?) {
    // A Box principal (ou um Scaffold) seria mais apropriado aqui para barras fixas.
    // Adaptando ao seu estilo:
    Column(modifier = Modifier.fillMaxSize()) {
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
                        .clickable { navegacao!!.navigate("") }
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

@Preview
@Composable
private fun ListarRegistroPreview() {
    ListarRegistro(null)
}