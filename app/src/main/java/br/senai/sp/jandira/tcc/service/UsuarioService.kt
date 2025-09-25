package br.senai.sp.jandira.tcc.service

import br.senai.sp.jandira.tcc.model.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UsuarioService {

    @Headers("Content-Type: application/json")
    @POST("usuario") //diz para o retrofit que a função abaixo é um post
    fun cadastrarUsuario(@Body usuario: Usuario): Call<Usuario>

}