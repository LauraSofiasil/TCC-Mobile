package br.senai.sp.jandira.tcc.model

import com.google.gson.annotations.SerializedName

data class Usuario(
    val id: Long? = 0,
    val nome: String = "",
    val email: String = "",
    val senha: String = ""
)
