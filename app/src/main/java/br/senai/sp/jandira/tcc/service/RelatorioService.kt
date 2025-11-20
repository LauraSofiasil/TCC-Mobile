package br.senai.sp.jandira.tcc.service

import br.senai.sp.jandira.tcc.model.Relatorio
import br.senai.sp.jandira.tcc.model.RelatorioResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface RelatorioService {
        @Headers("Content-Type: application/json") //aq defini ao app que ele recebera um JSON
        @GET("relatorio/ia/{usuarioId}")
        fun getGerarRelatorioIA(
            @Path("usuarioId") usuarioId: Long,
            @Body request: Relatorio
        ): Call<RelatorioResponse>

}