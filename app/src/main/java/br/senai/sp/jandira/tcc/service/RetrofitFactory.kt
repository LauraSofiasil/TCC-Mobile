package br.senai.sp.jandira.tcc.service

import br.senai.sp.jandira.tcc.screens.RecuperacaoSenha
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    private val baseURL = "http://10.107.134.25:8080/v1/teajuda/"

    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(baseURL) //recebe a url
        .addConverterFactory(GsonConverterFactory.create()) //convertendo o json para kotlin
        .build() //conecta

    fun getUsuarioService(): UsuarioService{
        return retrofitFactory.create(UsuarioService::class.java)
    }

    fun getRecuperacaoService(): RecuperacaoService{
        return retrofitFactory.create(RecuperacaoService::class.java)
    }

    //funcao de gerar o relatorio
    fun getGerarRelatorioService(): RelatorioService {
        return retrofitFactory.create(RelatorioService::class.java)
    }
}