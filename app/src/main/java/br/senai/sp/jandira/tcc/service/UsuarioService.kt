package br.senai.sp.jandira.tcc.service

import br.senai.sp.jandira.tcc.model.LoginUsuario
import br.senai.sp.jandira.tcc.model.Recuperacao
import br.senai.sp.jandira.tcc.model.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UsuarioService {

    //Iserir
    @Headers("Content-Type: application/json")
    @POST("usuario") //diz para o retrofit que a função abaixo é um post
    fun cadastrarUsuario(@Body usuario: Usuario): Call<Usuario>

    //Login
    @Headers("Content-Type: application/json")
    @POST("usuario/login") //diz para o retrofit que a função abaixo é um post
    fun loginUsuario(@Body loginUsuario: LoginUsuario ): Call<Usuario>

    //Recuperação
    @Headers("Content-Type: application/json")
    @POST("solicitacao-de-senha") //diz para o retrofit que a função abaixo é um post
    fun recuperacaoSenha(@Body recuperacao: LoginUsuario): Call<Recuperacao>



//    //Listar dados do login
//    @GET("usuario/login")
//    fun listarDados(): Call<LoginUsuario>
//
//    //Listar dados de um usuário específico do login
//    @GET("usuario/login/{id}")
//    fun buscarUsuarioID(@Path("id") id: Long): Call<LoginUsuario>

    //Listar todos os usuários
//    @GET("usuario")
//    fun listarUsuarios(): Call<Usuario>
//
//    //Buscar por ID
//    @GET("usuario/{id}")
//    fun buscarporID(@Path("id") id: Long): Call<Usuario>
}