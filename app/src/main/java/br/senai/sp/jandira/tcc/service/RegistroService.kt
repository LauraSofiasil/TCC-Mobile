package br.senai.sp.jandira.tcc.service

import br.senai.sp.jandira.tcc.model.Registro
import br.senai.sp.jandira.tcc.model.RegistroResponse
import br.senai.sp.jandira.tcc.model.RegistroResult
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface RegistroService {

    @Headers("Content-Type: application/json")
    @POST("registro")
    fun cadastrarRegistro(@Body registro: Registro): Call<RegistroResponse>

    @GET("registro")
    fun listarRegistros(): Call<RegistroResult>

}