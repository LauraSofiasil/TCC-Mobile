package br.senai.sp.jandira.tcc.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Conexao {

    private val baseURL = "http://10.107.144.31:8080/v1/controle-usuario/"

    private val conexao = Retrofit
        .Builder()
        .baseUrl(baseURL) //recebe a url
        .addConverterFactory(GsonConverterFactory.create()) //convertendo o json para kotlin
        .build() //conecta

    fun getUsuarioService(): UsuarioService{
        return conexao.create(UsuarioService::class.java)
    }
}