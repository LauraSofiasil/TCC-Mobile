package br.senai.sp.jandira.tcc.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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

@Composable
fun TelaCalendario(navegacao: NavHostController?) {
    // 1. Definir o intervalo de meses
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

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Exibir o nome do mês visível (o primeiro na tela)
        val visibleMonth = state.firstVisibleMonth.yearMonth
        Text(
            text = "${visibleMonth.month.name} ${visibleMonth.year}",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Exibir os dias da semana (Seg, Ter, Qua, etc.)
        SemanaHeader(daysOfWeek = daysOfWeek())

        // 3. O Composable do Calendário em si
        HorizontalCalendar(
            modifier = Modifier.fillMaxWidth(),
            state = state,
            dayContent = { day ->
                // Mude a chamada para passar o estado e a função de clique
                Day(
                    calendarDay = day,
                    isSelected = selectedDate == day.date, // Verifica se este dia é o selecionado
                    onDayClick = { date ->
                        selectedDate = if (selectedDate == date) null else date
                    }
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
    }
}

@Composable
fun Day(
    calendarDay: CalendarDay,
    isSelected: Boolean, //Recebe o estado de seleção
    onDayClick: (LocalDate) -> Unit
) {
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
    }
}

@Composable
fun SemanaHeader(daysOfWeek: List<DayOfWeek>) {
    Row(modifier = Modifier.fillMaxWidth()) {
        for (dayOfWeek in daysOfWeek) {
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                text = dayOfWeek.name.take(3) // Ex: MON, TUE
            )
        }
    }
}

// Helper para obter a lista de dias da semana corretamente ordenada
fun daysOfWeek(firstDayOfWeek: DayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek): List<DayOfWeek> {
    val days = DayOfWeek.values().toList()
    return days.drop(days.indexOf(firstDayOfWeek)) + days.take(days.indexOf(firstDayOfWeek))
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCalendario() {
    TelaCalendario(null)
}
