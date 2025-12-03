package br.senai.sp.jandira.tcc.screens

import androidx.compose.foundation.Image
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.ui.window.Dialog
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material3.IconButton
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.tcc.R
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.core.daysOfWeek
import java.time.LocalDate
import java.time.YearMonth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.*
import java.time.DayOfWeek
import java.time.temporal.WeekFields
import java.util.Locale
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Dialog

@Composable
fun TelaCalendario(navegacao: NavHostController?) {

    val currentMonth = YearMonth.now()
    val startMonth = currentMonth.minusMonths(100)
    val endMonth = currentMonth.plusMonths(100)
    val firstDayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek

    //Criar e lembrar o estado do calendário
    val state = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = firstDayOfWeek
    )

    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    var eventos by remember { mutableStateOf(eventosPorData.toMutableMap()) }
    var novoEventoTitulo by remember { mutableStateOf("") }
    var ultimoId by remember { mutableStateOf(4) }
    var showAddEventDialog by remember { mutableStateOf(false) }
    var novoEventoDescricao by remember { mutableStateOf("") }
    var eventoParaVisualizar by remember { mutableStateOf<Events?>(null) }

    val adicionarEvento: (String, String, LocalDate) -> Unit = { titulo, descricao, data ->
        if (titulo.isNotBlank()) {
            val id = ultimoId + 1
            val novoEvento = Events(id, titulo, Color.Magenta, descricao)

            //Atualiza a lista de eventos para aquela data
            eventos[data] = eventos[data].orEmpty() + novoEvento

            ultimoId = id
            novoEventoTitulo = ""
            novoEventoDescricao = "" // LIMPAR O CAMPO
        }
    }

    val removerEvento: (LocalDate, Events) -> Unit = { data, eventoParaRemover ->
        val dataAnterior = selectedDate
        val eventosAtuais = eventos[data].orEmpty()
        val novaLista = eventosAtuais.filter { it.id != eventoParaRemover.id }

        if (novaLista.isEmpty()) {
            eventos.remove(data)
        } else {
            eventos[data] = novaLista
        }

        selectedDate = null
        selectedDate = dataAnterior
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F6F6))
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 70.dp)
        )
        {
            // Exibir o nome do mês visível (o primeiro na tela)
            val formatter = remember { DateTimeFormatter.ofPattern("MMMM yyyy", Locale("pt", "BR")) }
            val visibleMonth = state.firstVisibleMonth.yearMonth
            Text(
                text = formatter.format(visibleMonth).uppercase(Locale("pt", "BR")),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Exibir os dias da semana (Seg, Ter, Qua, etc.)
            SemanaHeader(daysOfWeek = daysOfWeek())

            //Calendário
            HorizontalCalendar(
                modifier = Modifier.fillMaxWidth(),
                state = state,
                dayContent = { day ->

                    val dayEvents = eventos[day.date].orEmpty()

                    Day(
                        calendarDay = day,
                        isSelected = selectedDate == day.date, // Verifica se este dia é o selecionado
                        onDayClick = { date ->
                            selectedDate = if (selectedDate == date) null else date
                        },
                        events = dayEvents
                    )
                },
                monthHeader = { month ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Magenta)
                    ) {  }
                }
            )

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Button(
                    onClick = {
                        //Abre se uma data estiver selecionada
                        if (selectedDate != null) {
                            showAddEventDialog = true
                        }
                    },
                    enabled = selectedDate != null, // Desabilita se nenhuma data estiver selecionada
                    modifier = Modifier
                        .size(60.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1892FF))
                ) {
                    Text(text = "+")
                }
            }

            //Exibir detalhes do evento
            if (selectedDate != null) {
                Spacer(modifier = Modifier.height(16.dp))
                Divider()

                // Lista de eventos para o dia selecionado
                val eventosDoDia = eventos[selectedDate].orEmpty()

                Text(
                    text = "Eventos para ${selectedDate.toString()}:",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                if (eventosDoDia.isNotEmpty()) {
                    Column {
                        eventosDoDia.forEach { evento ->
                            ItemEvento(
                                evento = evento,
                                dataDoEvento = selectedDate!!, // É seguro usar !! aqui dentro do if(selectedDate != null)
                                onRemoverClick = removerEvento,
                                onEventClick = { clickedEvent ->
                                    eventoParaVisualizar = clickedEvent // Define o estado para mostrar o Dialog
                                }
                            )
                        }
                    }
                } else {
                    Text("Nenhum evento neste dia.")
                }
            }

            if (showAddEventDialog && selectedDate != null) {
                Dialog(onDismissRequest = { showAddEventDialog = false }) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.padding(16.dp),
                        color = Color.White
                    ) {
                        // Conteúdo do formulário de adição
                        AdicionarEventoForm(
                            titulo = novoEventoTitulo,
                            onTituloChange = { novoEventoTitulo = it },
                            descricao = novoEventoDescricao,
                            onDescricaoChange = { novoEventoDescricao = it },
                            dataSelecionada = selectedDate,
                            onAdicionarClick = {
                                adicionarEvento(novoEventoTitulo, novoEventoDescricao, selectedDate!!)
                                showAddEventDialog = false // Fecha o diálogo após salvar

                            },
                            onCancelarClick = {
                                showAddEventDialog = false
                                novoEventoTitulo = ""
                                novoEventoDescricao = "" // Limpa o campo ao cancelar
                            }
                        )
                    }
                }
            }

            if (eventoParaVisualizar != null && selectedDate != null) {
                DetalhesEventoDialog(
                    evento = eventoParaVisualizar!!,
                    dataSelecionada = selectedDate!!,
                    onDismiss = {
                        eventoParaVisualizar = null // Define como null para fechar o Dialog
                    }
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
        )
        {

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
                            .clickable {navegacao!!.navigate("perfil")}
                    )
                }
            }

            Spacer(modifier = Modifier.height(800.dp))

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

@Composable
fun Day(
    calendarDay: CalendarDay,
    isSelected: Boolean, //Recebe o estado de seleção
    onDayClick: (LocalDate) -> Unit,
    events: List<Events> = emptyList()
)
{
    val date = calendarDay.date
    val isToday = date == LocalDate.now()

    val isClickable = calendarDay.position == DayPosition.MonthDate



    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(2.dp)
            .clickable(enabled = isClickable) {
                onDayClick(date)
            }
            .background(
                when {
                    isSelected -> Color(0xFF1892FF) //Cor para a data selecionada
                    isToday -> Color.LightGray
                    else -> Color.Transparent
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = date.dayOfMonth.toString(),
            color = when (calendarDay.position) {
                DayPosition.MonthDate -> Color.Black // Dia dentro do mês
                else -> Color.Gray // Dias "out" de outros meses
            }
        )
        if (events.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 4.dp), // Empurra o Dot para baixo
                contentAlignment = Alignment.BottomCenter
            ) {
                // Se houver mais de um evento, você pode mostrar um ponto de uma cor genérica,
                // ou desenhar múltiplos pontos, ou usar a cor do primeiro evento.
                Box(
                    modifier = Modifier
                        .size(6.dp) // Tamanho do ponto
                        .background(
                            color = events.first().cor, // Usa a cor do primeiro evento
                            shape = CircleShape // Formato de círculo
                        )
                )
            }
        }
    }
}

@Composable
fun SemanaHeader(daysOfWeek: List<DayOfWeek>) {
    Row(modifier = Modifier.fillMaxWidth()) {
        for (dayOfWeek in daysOfWeek) {
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale("pt", "BR")).uppercase(Locale("pt", "BR"))
            )
        }
    }
}

fun daysOfWeek(firstDayOfWeek: DayOfWeek = WeekFields.of(Locale("pt", "BR")).firstDayOfWeek): List<DayOfWeek> {
    val days = DayOfWeek.values().toList()
    return days.drop(days.indexOf(firstDayOfWeek)) + days.take(days.indexOf(firstDayOfWeek))
}

data class Events(
    val id: Int, val titulo: String, val cor: Color, val descricao: String
)

// O mapa armazena eventos. Por exemplo:
val eventosPorData = mapOf(
    LocalDate.now().plusDays(2) to listOf(
        Events(1, "Consulta Médica", Color.Blue, "Retorno ao psicólogo")
    )
)


@Composable
fun AdicionarEventoForm(
    titulo: String,
    onTituloChange: (String) -> Unit,
    descricao: String,
    onDescricaoChange: (String) -> Unit,
    dataSelecionada: LocalDate?,
    onAdicionarClick: () -> Unit,
    onCancelarClick: () -> Unit
)
{
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Novo Lembrete para ${dataSelecionada?.toString() ?: "Data Inválida"}",
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = titulo,
            onValueChange = onTituloChange,
            label = { Text("Título do Evento") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF1892FF),
                unfocusedBorderColor = Color.Gray,
                cursorColor = Color(0xFF1892FF),
                unfocusedLabelColor = Color.Gray,
                focusedLabelColor = Color(0xFF1892FF)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = descricao,
            onValueChange = onDescricaoChange,
            label = { Text("Descrição (Opcional)") },
            modifier = Modifier.fillMaxWidth().heightIn(min = 100.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF1892FF),
                unfocusedBorderColor = Color.Gray,
                cursorColor = Color(0xFF1892FF),
                unfocusedLabelColor = Color.Gray,
                focusedLabelColor = Color(0xFF1892FF)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botões Salvar e Cancelar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(onClick = onCancelarClick) {
                Text(
                    "Cancelar",
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = onAdicionarClick,
                enabled = titulo.isNotBlank() && dataSelecionada != null,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1892FF))
            ) {
                Text(
                    text = "Salvar"
                )
            }
        }
    }
}

@Composable
fun ItemEvento(
    evento: Events,
    dataDoEvento: LocalDate,
    onRemoverClick: (LocalDate, Events) -> Unit,
    onEventClick: (Events) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onEventClick(evento) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Indicador de Cor e Título
        Box(
            modifier = Modifier
                .size(10.dp)
                .background(evento.cor, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))

        // Coluna para Título e Descrição
        Column(modifier = Modifier.weight(1f)) {
            Text(text = evento.titulo, fontWeight = FontWeight.Bold)
            if (evento.descricao.isNotEmpty()) {
                Text(text = evento.descricao, fontSize = 12.sp, color = Color.Gray)
            }
        }

        // NOVO: BOTÃO DE EXCLUSÃO
        IconButton(
            onClick = {
                onRemoverClick(dataDoEvento, evento)
            }
        ) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "Remover Evento",
                tint = Color.Red // Cor vermelha para indicar exclusão
            )
        }
    }
}

@Composable
fun DetalhesEventoDialog(
    evento: Events,
    dataSelecionada: LocalDate,
    onDismiss: () -> Unit // Função para fechar o diálogo
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(16.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                // Indicador de cor
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(evento.cor, CircleShape)
                        .align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Título
                Text(
                    text = evento.titulo,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Data
                Text(
                    text = "Data: ${dataSelecionada.toString()}",
                    color = Color.Gray,
                    fontSize = 14.sp
                )

                Divider(modifier = Modifier.padding(vertical = 12.dp))

                // Descrição
                if (evento.descricao.isNotEmpty()) {
                    Text(
                        text = "Descrição:",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = evento.descricao,
                        fontSize = 14.sp
                    )
                } else {
                    Text(
                        text = "Nenhuma descrição fornecida.",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Botão de Fechar
                Button(
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1892FF))
                ) {
                    Text("Fechar")
                }
            }
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCalendario() {
    TelaCalendario(null)
}
