package br.senai.sp.jandira.tcc.model

data class LoginUsuario(
    val email: String = "",
    val senha: String = ""
)

data class LoginResponse(
    val status_code: Int,
    val token: String,
    val data: DadosUsuario
)

data class DadosUsuario(
    val id: Long,
    val nome: String,
    val email: String
)