package br.senai.sp.jandira.tcc.model

import com.google.gson.annotations.SerializedName

data class Registro(
    val id: Int? = null,
    val texto: String,
    @SerializedName("usuario_id") var usuario_id: Int
)

data class RegistroResponse(
    val status_code: Int
)

