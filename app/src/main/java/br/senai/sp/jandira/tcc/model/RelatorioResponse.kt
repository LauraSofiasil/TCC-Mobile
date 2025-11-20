package br.senai.sp.jandira.tcc.model


//arquivo responsavel por mandar por receber dados direto da API
data class RelatorioResponse(
    val id: Long? = null,
    val usuarioId: Long,
    val conteudo_markdown: String? = null,
    val data_geracao: String? = null

)
