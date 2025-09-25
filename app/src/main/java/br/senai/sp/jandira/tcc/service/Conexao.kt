package br.senai.sp.jandira.tcc.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Conexao {

    private val base_URL = "http://10.107.140.17:8080/v1/controle-usuario/"

    private val conexao = Retrofit
        .Builder()
        .baseUrl(base_URL) //recebe a url
        .addConverterFactory(GsonConverterFactory.create()) //convertendo o json para kotlin
        .build() //conecta

    fun getUsuarioService(): UsuarioService{
        return conexao.create(UsuarioService::class.java)
    }
}