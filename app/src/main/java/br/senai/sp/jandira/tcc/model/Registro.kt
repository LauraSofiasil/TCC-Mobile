package br.senai.sp.jandira.tcc.model

data class Registro(
    val id: Int? = null,
    val texto: String,
    val data: String
)

data class RegistroResponse(
    val status_code: Int
)

