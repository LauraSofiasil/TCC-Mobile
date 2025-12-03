package br.senai.sp.jandira.tcc.screens.components

import android.graphics.Color
import java.time.LocalDate

data class Events(
    val id: Int, val titulo: String, val cor: Color)

// O mapa armazena eventos. Por exemplo:
val eventosPorData = mapOf(
    LocalDate.now().plusDays(2) to listOf(
        Events(1, "Consulta Médica", cor = Color.RED)
    ),
    LocalDate.now().plusDays(5) to listOf(
        Events(2, "Aniversário", Color.BLUE),
        Events(3, "Reunião TCC", Color.BLUE)
    )
    // Adicione mais eventos aqui...
)
