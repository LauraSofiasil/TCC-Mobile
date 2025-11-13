package br.senai.sp.jandira.tcc.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.tcc.R

@Composable
fun TelaMapa(navegacao: NavHostController?) {

    val locais = listOf(
        Local(
            nome = "Sala Sensorial Estação Santa Cruz (Metrô/ViaMobilidade)",
            endereco = "Estação Santa Cruz (Linha 5-Lilás), Vila Mariana, São Paulo/SP",
            telefone = "(11) 3116-7406",
            lat = -23.52332,
            lng = -46.6385,
            descricao = "Atendimento com psicólogos e terapeutas especializados"
        ),
        Local(
            nome = "Unidade de Referência em Autismo Prof. Marcos Tomanik Mercadante (AMA - Vila Mariana)",
            endereco = "Rua Capitão Cavalcanti, 268 – Vila Mariana, São Paulo/SP",
            telefone = "(11) 3466-2600",
            lat = -23.5878,
            lng = -46.6371,
            descricao = "Unidade que oferece atendimento e serviços especializados para pessoas com Transtorno do Espectro Autista (AMA)."
        ),
        Local(
            nome = "TEAjudo SabiaMente",
            endereco = "Rua Cantagalo, 678. Tatuapé – São Paulo – SP. CEP 03319-000",
            telefone = "(11) 2096-4938 / (11) 99486-0408 (WhatsApp)",
            lat = -23.5459,
            lng = -46.5683,
            descricao = "Unidade especializada do Governo de SP com serviços e apoio para autistas, incluindo sala de interação multissensorial e espaço de acolhimento para crises."
        ),
        Local(
            nome = "Centro TEA Paulista",
            endereco = "Rua Galileo Emendabili, 99, Jardim Humaitá – São Paulo/SP",
            telefone = "(11) 3116-7406",
            lat = -23.52332,
            lng = -46.74369,
            descricao = "Unidade especializada do Governo de SP com serviços e apoio para autistas, incluindo sala de interação multissensorial e espaço de acolhimento para crises."
        ),
        Local(
            nome = "Sala Sensorial Estação Tatuapé (CPTM/Metrô)",
            endereco = "Estação Tatuapé (Linhas 11, 12 da CPTM e Linha 3-Vermelha do Metrô), Tatuapé, São Paulo/SP",
            telefone = "Não disponível publicamente para a Sala, mas a CPTM/Metrô possuem canais de contato.",
            lat = -23.5413,
            lng = -46.5779,
            descricao = "Espaço que oferece conforto visual e acústico para pessoas autistas e neurodivergentes em crises sensoriais ou com alta sensibilidade a estímulos externos."
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0XFFADD8FF))
        ) {}

        Image(
            painter = painterResource(R.drawable.icons_fundo),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // barra superior
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(
                    color = Color(0xFF42A5F5),
                    shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                )
                .align(Alignment.TopCenter)
        ) {
            Row(
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
                        .clickable { navegacao?.navigate("notificacao") }
                )

                Icon(
                    painter = painterResource(id = R.drawable.perfilicon),
                    contentDescription = "Perfil",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { navegacao?.navigate("perfil") }
                )
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 80.dp)
                .fillMaxWidth()
        ) {
            val textoPesquisa = remember { mutableStateOf("") }

            TextField(
                value = textoPesquisa.value,
                onValueChange = { textoPesquisa.value = it },
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
                    .align(Alignment.Center)
                    .background(Color.White, RoundedCornerShape(16.dp))
            )
        }

        LazyColumn(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 160.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = "Espaços TEA próximos",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .background(Color.White, shape = RoundedCornerShape(20.dp))
                        .shadow(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(20.dp),
                            ambientColor = Color.Black.copy(alpha = 0.2f)
                        )
                        .padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.maps),
                        contentDescription = "Mapa TEA",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1.3f)
                            .clip(RoundedCornerShape(16.dp))
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
            }

            items(locais) { local ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(vertical = 6.dp),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = "Localização",
                                tint = Color(0xFF42A5F5)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = local.nome,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "📍 ${local.endereco}", fontSize = 13.sp, color = Color.DarkGray)
                        Text(text = "📞 ${local.telefone}", fontSize = 13.sp, color = Color.DarkGray)
                        Text(text = "🧠 ${local.descricao}", fontSize = 13.sp, color = Color(0xFF1A237E))
                    }
                }
            }
        }
    }
}

data class Local(
    val nome: String,
    val endereco: String,
    val telefone: String,
    val lat: Double,
    val lng: Double,
    val descricao: String
)

@Preview
@Composable
private fun TelaMapaPreview() {
    TelaMapa(null)
}
