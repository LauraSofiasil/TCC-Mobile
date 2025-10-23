package br.senai.sp.jandira.tcc.service

import br.senai.sp.jandira.tcc.model.Codigo
import br.senai.sp.jandira.tcc.model.LoginUsuario
import br.senai.sp.jandira.tcc.model.NovaSenha
import br.senai.sp.jandira.tcc.model.Recuperacao
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RecuperacaoService {

    //Recuperação
    @Headers("Content-Type: application/json")
    @POST("solicitacao-de-senha") //diz para o retrofit que a função abaixo é um post
    fun recuperacaoSenha(@Body recuperacao: LoginUsuario): Call<Recuperacao>

    //Código de Verificação
    @Headers("Content-Type: application/json")
    @POST("solicitacao-de-senha")
    fun codigoVerificacao(@Body codigo: Codigo): Call<Codigo>

    //Nova Senha
    @Headers("Content-Type: application/json")
    @POST("solicitacao-de-senha")
    fun novaSenha(@Body novaSenha: NovaSenha): Call<NovaSenha>
}