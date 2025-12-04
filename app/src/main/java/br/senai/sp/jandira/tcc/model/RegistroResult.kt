package br.senai.sp.jandira.tcc.model

data class RegistroResult(
    val status: Boolean,
    val status_code: Int,
    val itens: Int,
    val registros: List<Registro>
)

