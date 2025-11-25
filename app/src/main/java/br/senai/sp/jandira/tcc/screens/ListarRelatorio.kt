package br.senai.sp.jandira.tcc.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.tcc.model.Relatorio
import br.senai.sp.jandira.tcc.model.RelatorioResponse
import br.senai.sp.jandira.tcc.service.RelatorioService
import br.senai.sp.jandira.tcc.model.Relatorio
import kotlinx.coroutines.launch

sealed class RelatorioUiState {
    object Loading : RelatorioUiState()
    data class Success(val relatorios: List<RelatorioResponse>) : RelatorioUiState()
    data class Error(val mensagem: String) : RelatorioUiState()
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListarRelatorioScreen(
    navController: NavController = rememberNavController(),
    relatorioService: RelatorioService,
    usuarioId: Long = 1 // TODO: Get this from your authentication system
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    
    var uiState by remember { mutableStateOf<RelatorioUiState>(RelatorioUiState.Loading) }
    var searchQuery by remember { mutableStateOf("") }
    
    // Load relatorios when the screen is first displayed
    LaunchedEffect(Unit) {
        loadRelatorios()
    }
    
    // Function to load relatorios
    fun loadRelatorios() {
        scope.launch {
            uiState = RelatorioUiState.Loading
            try {
                val response = relatorioService.getListarRelatorios(usuarioId, Relatorio(usuarioId = usuarioId))
                if (response.isSuccessful) {
                    val relatorios = response.body() ?: emptyList()
                    uiState = RelatorioUiState.Success(relatorios)
                } else {
                    uiState = RelatorioUiState.Error("Erro ao carregar relatórios: ${response.message()}")
                }
            } catch (e: Exception) {
                uiState = RelatorioUiState.Error("Erro de conexão: ${e.message ?: "Erro desconhecido"}")
            }
        }
    }
    
    // Show error message if there's an error
    LaunchedEffect(uiState) {
        if (uiState is RelatorioUiState.Error) {
            val errorMessage = (uiState as RelatorioUiState.Error).mensagem
            snackbarHostState.showSnackbar(
                message = errorMessage,
                duration = SnackbarDuration.Short
            )
        }
    }
    
    // Filter relatorios based on search query
    val filteredRelatorios = when (val state = uiState) {
        is RelatorioUiState.Success -> {
            state.relatorios.filter { relatorio ->
                relatorio.titulo?.contains(searchQuery, ignoreCase = true) == true ||
                relatorio.descricao?.contains(searchQuery, ignoreCase = true) == true
            }
        }
        else -> emptyList()
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerHeader()
                DrawerBody(navController = navController)
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "Relatórios",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF6E5DE7),
                        titleContentColor = Color.White,
                        actionIconContentColor = Color.White,
                        navigationIconContentColor = Color.White
                    )
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        // TODO: Navigate to create new report
                    },
                    containerColor = Color(0xFF6E5DE7),
                    contentColor = Color.White,
                    shape = CircleShape
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Adicionar relatório"
                    )
                }
            }
        ) { padding ->
            when (val state = uiState) {
                is RelatorioUiState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is RelatorioUiState.Success -> {
                    if (filteredRelatorios.isEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(padding),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Nenhum relatório encontrado")
                                )
                                TextButton(
                                    onClick = { viewModel.carregarRelatorios() }
                                ) {
                                    Text("Tentar novamente")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Pesquisar",
                tint = Color.Gray
            )
        },
        placeholder = { Text("Pesquisar relatórios...") },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color(0xFFF5F5F5),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(12.dp),
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = { /* Handle search */ }
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
    )
}

@Composable
fun RelatorioItem(
    relatorio: RelatorioResponse,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = relatorio.data_gerado ?: "Sem data",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = relatorio.conteudo_markdown?.take(100)?.plus("...") ?: "Sem conteúdo",
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun DrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(Color(0xFF6E5DE7)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // User avatar or icon
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .background(Color.White, CircleShape)
                    .padding(8.dp)
            ) {
                // You can add an image here
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Usuário",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
            Text(
                text = "usuario@email.com",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White.copy(alpha = 0.8f)
            )
        }
    }
}

@Composable
private fun DrawerBody(
    navController: NavController
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        // Add your drawer items here
        DrawerItem(text = "Início", onClick = { /* Navigate to home */ })
        DrawerItem(text = "Perfil", onClick = { /* Navigate to profile */ })
        DrawerItem(text = "Configurações", onClick = { /* Navigate to settings */ })
        Spacer(modifier = Modifier.weight(1f))
        Divider()
        DrawerItem(text = "Sair", onClick = { /* Handle logout */ })
    }
}

@Composable
private fun DrawerItem(
    text: String,
    onClick: () -> Unit
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp)
    )
}

// Mock implementation of RelatorioService for preview
class MockRelatorioService : RelatorioService {
    override fun getGerarRelatorioIA(usuarioId: Long, request: Relatorio): Call<RelatorioResponse> {
        return object : Call<RelatorioResponse> {
            override fun enqueue(callback: Callback<RelatorioResponse>) {}
            override fun isExecuted(): Boolean = false
            override fun clone(): Call<RelatorioResponse> = this
            override fun isCanceled(): Boolean = false
            override fun cancel() {}
            override fun execute(): Response<RelatorioResponse> {
                return Response.success(RelatorioResponse("1", "Relatório de Teste", "Descrição de teste", "2023-01-01", "2023-12-31", 1))
            }
            override fun request(): Request? = null
        }
    }

    override fun getListarRelatorios(usuarioId: Long, request: Relatorio): Call<List<RelatorioResponse>> {
        return object : Call<List<RelatorioResponse>> {
            override fun enqueue(callback: Callback<List<RelatorioResponse>>) {}
            override fun isExecuted(): Boolean = false
            override fun clone(): Call<List<RelatorioResponse>> = this
            override fun isCanceled(): Boolean = false
            override fun cancel() {}
            override fun execute(): Response<List<RelatorioResponse>> {
                val relatorios = listOf(
                    RelatorioResponse("1", "Relatório 1", "Descrição 1", "2023-01-01", "2023-12-31", 1),
                    RelatorioResponse("2", "Relatório 2", "Descrição 2", "2023-02-01", "2023-12-31", 1),
                    RelatorioResponse("3", "Relatório 3", "Descrição 3", "2023-03-01", "2023-12-31", 1)
                )
                return Response.success(relatorios)
            }
            override fun request(): Request? = null
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ListarRelatorioPreview() {
    TCC_MobileTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ListarRelatorioScreen(
                navController = rememberNavController(),
                relatorioService = MockRelatorioService(),
                usuarioId = 1
            )
        }
    }
    
    ListarRelatorioScreen(relatorioService = PreviewRelatorioService())
}
