package br.senai.sp.jandira.tcc.model

data class LoginUsuario(
    val id: Long? = 0,
    val nome: String = "",
    val email: String = "",
    val senha: String = ""
)
