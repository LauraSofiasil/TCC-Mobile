package br.senai.sp.jandira.tcc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.tcc.screens.CodigoVerificacao
import br.senai.sp.jandira.tcc.screens.NovaSenha
import br.senai.sp.jandira.tcc.screens.TelaCadastro
import br.senai.sp.jandira.tcc.screens.TelaHome
import br.senai.sp.jandira.tcc.screens.TelaInicial
import br.senai.sp.jandira.tcc.screens.TelaInicial2
import br.senai.sp.jandira.tcc.screens.TelaLogin
import br.senai.sp.jandira.tcc.screens.TelaPerfil
import br.senai.sp.jandira.tcc.screens.RecuperacaoSenha
import br.senai.sp.jandira.tcc.screens.TelaCalendario

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent() {
            val navegacao = rememberNavController()
            NavHost(
                navController = navegacao,
                startDestination = "inicio"
            ) {
                composable(route = "inicio") { TelaInicial(navegacao) }
                composable(route = "inicio2") { TelaInicial2(navegacao) }
                composable(route = "login") { TelaLogin(navegacao) }
                composable(route = "cadastro") { TelaCadastro(navegacao) }
                composable(route = "home") { TelaHome(navegacao) }
                composable(route = "perfil") { TelaPerfil(navegacao) }
                composable(route = "recuperacao") { RecuperacaoSenha(navegacao) }
                composable(route = "codigo") { CodigoVerificacao(navegacao) }
                composable(route = "novaSenha") { NovaSenha(navegacao) }
                composable(route = "calendario") { TelaCalendario(navegacao) }
            }
        }
    }
}



