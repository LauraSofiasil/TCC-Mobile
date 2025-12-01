package br.senai.sp.jandira.tcc.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.tcc.R


@Composable
fun TelaHome(navegacao: NavHostController?) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
            Image(
                painter = painterResource(R.drawable.testehomeimg),
                contentDescription = "",
                modifier = Modifier
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //Barra Superior
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),
                    shape = RoundedCornerShape(
                        bottomStart = 10.dp,
                        bottomEnd = 10.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0XFFffffff)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(R.drawable.sininho),
                            contentDescription = "",
                            modifier = Modifier
                                .size(29.dp)
                        )

                        Spacer(modifier = Modifier.width(80.dp))

                        Image(
                            painter = painterResource(R.drawable.perfilicon),
                            contentDescription = "",
                            modifier = Modifier
                                .size(34.dp)
                                .padding(end = 10.dp)
                                .clickable {navegacao!!.navigate("perfil")}
                        )
                    }
                }

                //1° Collumn
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                        .height(175.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    Text(
                        text = "Bem-Vindo ao TEAjuda",
                        color = Color(0xffFFFFFF),
                        fontFamily = FontFamily(Font(R.font.inter_bold)),
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(top = 28.dp)
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    )
                    {
                        Text(
                            text = "Uma plataforma dedicada a apoiar pessoas com Trantorno do Espectro Autista e suas",
                            color = Color(0xffFFFFFF),
                            fontFamily = FontFamily(Font(R.font.inter_normal)),
                            fontSize = 6.8.sp,
                            modifier = Modifier
                        )
                        Text(
                            text = "famílias",
                            color = Color(0xffFFFFFF),
                            fontFamily = FontFamily(Font(R.font.inter_normal)),
                            fontSize = 6.8.sp,
                        )
                    }
                }

                //2° Collumn
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFD9D9D9))
                        .height(160.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    Text(
                        text = "Nossos Recursos",
                        color = Color(0xffFFFFFF),
                        fontFamily = FontFamily(Font(R.font.inter_bold)),
                        fontSize = 10.sp,
                        modifier = Modifier
                            .padding(top = 3.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 23.dp),
                        horizontalArrangement = Arrangement.Center
                    )
                    {
                        Card(
                            modifier = Modifier
                                .width(93.dp)
                                .height(68.dp),
                            shape = RoundedCornerShape(
                                bottomStart = 10.dp,
                                bottomEnd = 10.dp,
                                topEnd = 10.dp,
                                topStart = 10.dp
                            ),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0XFFffffff)
                            )
                        )
                        {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                Image(
                                    painter = painterResource(R.drawable.alvocortado),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(15.dp)
                                        .padding(top = 5.dp)
                                )
                                Text(
                                    text = "Objetivos Personalizados",
                                    color = Color(0xff000000),
                                    fontFamily = FontFamily(Font(R.font.inter_normal)),
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 6.sp,
                                    modifier = Modifier
                                        .padding(top = 8.dp)
                                )
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 4.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                )
                                {
                                    Text(
                                        text = "Estabeleça e acompanhe objetivos de",
                                        color = Color(0xff000000),
                                        fontFamily = FontFamily(Font(R.font.inter_normal)),
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 3.sp,
                                        modifier = Modifier
                                    )
                                    Text(
                                        text = "desenvolvimento individualizados",
                                        color = Color(0xff000000),
                                        fontFamily = FontFamily(Font(R.font.inter_normal)),
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 3.sp,
                                        modifier = Modifier
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Card(
                            modifier = Modifier
                                .width(93.dp)
                                .height(68.dp),
                            shape = RoundedCornerShape(
                                bottomStart = 10.dp,
                                bottomEnd = 10.dp,
                                topEnd = 10.dp,
                                topStart = 10.dp
                            ),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0XFFffffff)
                            )
                        )
                        {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                Image(
                                    painter = painterResource(R.drawable.graficocortado),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(15.dp)
                                        .padding(top = 7.dp)
                                )
                                Text(
                                    text = "Acompanhamento",
                                    color = Color(0xff000000),
                                    fontFamily = FontFamily(Font(R.font.inter_normal)),
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 6.sp,
                                    modifier = Modifier
                                        .padding(top = 8.dp)
                                )
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 4.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                )
                                {
                                    Text(
                                        text = "Monitore o progresso com relatórios",
                                        color = Color(0xff000000),
                                        fontFamily = FontFamily(Font(R.font.inter_normal)),
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 3.sp,
                                        modifier = Modifier
                                    )
                                    Text(
                                        text = "detalhados facéis de entender",
                                        color = Color(0xff000000),
                                        fontFamily = FontFamily(Font(R.font.inter_normal)),
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 3.sp,
                                        modifier = Modifier
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Card(
                            modifier = Modifier
                                .width(93.dp)
                                .height(68.dp),
                            shape = RoundedCornerShape(
                                bottomStart = 10.dp,
                                bottomEnd = 10.dp,
                                topEnd = 10.dp,
                                topStart = 10.dp
                            ),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0XFFffffff)
                            )
                        )
                        {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                Image(
                                    painter = painterResource(R.drawable.familia),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(15.dp)
                                        .padding(top = 8.dp)
                                )
                                Text(
                                    text = "Suporte Familiar",
                                    color = Color(0xff000000),
                                    fontFamily = FontFamily(Font(R.font.inter_normal)),
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 6.sp,
                                    modifier = Modifier
                                        .padding(top = 8.dp)
                                )
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 4.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                )
                                {
                                    Text(
                                        text = "Recursos e ferramentas para toda a família",
                                        color = Color(0xff000000),
                                        fontFamily = FontFamily(Font(R.font.inter_normal)),
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 3.sp,
                                        modifier = Modifier
                                    )
                                }
                            }
                        }

                    }
                }

                //3° Collumn
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xffffffff))
                        .height(170.dp)
                        .padding(start = 11.dp),
                )
                {
                    Text(
                        text = "Sobre Nós",
                        color = Color(0xff000000),
                        fontFamily = FontFamily(Font(R.font.inter_bold)),
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(top = 55.dp)
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                    )
                    {
                        Text(
                            text = "O TeAjuda nasceu da necessidade de criar uma ponte entre",
                            color = Color(0xA3020202),
                            fontFamily = FontFamily(Font(R.font.inter_normal)),
                            fontSize = 8.sp,
                            modifier = Modifier
                        )
                        Text(
                            text = "profissionais, famílias e pessoas no espectro autista,",
                            color = Color(0xA3020202),
                            fontFamily = FontFamily(Font(R.font.inter_normal)),
                            fontSize = 8.sp,
                            modifier = Modifier
                        )
                        Text(
                            text = "oferecendo ferramentas que facilitam o acompanhamento e",
                            color = Color(0xA3020202),
                            fontFamily = FontFamily(Font(R.font.inter_normal)),
                            fontSize = 8.sp,
                            modifier = Modifier
                        )
                        Text(
                            text = "desenvolvimento contínuo.",
                            color = Color(0xA3020202),
                            fontFamily = FontFamily(Font(R.font.inter_normal)),
                            fontSize = 8.sp,
                            modifier = Modifier
                        )

                    }

                }

                //4° Collumn
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(198.dp)
                ){
                    Image(
                        painter = painterResource(R.drawable.maistestehome),
                        contentDescription = "",
                        modifier = Modifier
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Transparent)
                            .padding(start = 11.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    )
                    {
                        Text(
                            text = "Pronto para começar?",
                            color = Color(0xffFFFFFF),
                            fontFamily = FontFamily(Font(R.font.inter_bold)),
                            fontSize = 18.sp,
                            modifier = Modifier
                                .padding(top = 18.dp)
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 18.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        )
                        {
                            Text(
                                text = "Junte-se a nós e descubra como podemos ajudar no desenvolvimento e",
                                color = Color(0xffFFFFFF),
                                fontFamily = FontFamily(Font(R.font.inter_normal)),
                                fontSize = 6.8.sp,
                                modifier = Modifier
                            )
                            Text(
                                text = "acompanhamento",
                                color = Color(0xffFFFFFF),
                                fontFamily = FontFamily(Font(R.font.inter_normal)),
                                fontSize = 6.8.sp,
                            )

                            Spacer(modifier = Modifier.height(33.dp))

                            Button(
                                onClick = {},
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xffffffff)
                                ),
                                shape = RoundedCornerShape(
                                    60.dp
                                ),
                                modifier = Modifier
                                    .width(118.dp)
                                    .height(40.dp)
                            ) {
                                Text(
                                    text = "Acessar calendário",
                                    style = MaterialTheme.typography.titleLarge,
                                    color = Color(0xff4DA9FF),
                                    fontSize = 8.sp
                                )
                            }
                        }
                    }
                }

                //5° Collumn
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xB8000000))
                        .height(120.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 23.dp),
                        horizontalArrangement = Arrangement.Center
                    )
                    {
                        Column(
                                modifier = Modifier
                                    .fillMaxHeight(),
                        )
                        {
                            Text(
                                text = "Contato",
                                color = Color(0xffffffff),
                                fontFamily = FontFamily(Font(R.font.inter_bold)),
                                fontSize = 7.sp,
                                modifier = Modifier
                                    .padding(top = 15.dp)
                            )
                            Text(
                                text = "Email:  equipe@teajuda.com",
                                color = Color(0xff9c9c9c),
                                fontFamily = FontFamily(Font(R.font.inter_normal)),
                                fontSize = 5.sp,
                                modifier = Modifier
                                    .padding(top = 10.dp)
                            )
                            Text(
                                text = "Telefone: (11) 99999-9999",
                                color = Color(0xff9c9c9c),
                                fontFamily = FontFamily(Font(R.font.inter_normal)),
                                fontSize = 5.sp,
                                modifier = Modifier
                                    .padding(top = 7.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(60.dp))

                        Column(
                            modifier = Modifier
                                .fillMaxHeight(),
                        )
                        {
                            Text(
                                text = "Equipe",
                                color = Color(0xffffffff),
                                fontFamily = FontFamily(Font(R.font.inter_bold)),
                                fontSize = 7.sp,
                                modifier = Modifier
                                    .padding(top = 15.dp)
                            )
                            Row{
                                Column()
                                {
                                    Text(
                                        text = "Bryan Barbosa",
                                        color = Color(0xff9c9c9c),
                                        fontFamily = FontFamily(Font(R.font.inter_normal)),
                                        fontSize = 5.sp,
                                        modifier = Modifier
                                            .padding(top = 10.dp)
                                    )
                                    Text(
                                        text = "Gustavo Deodato",
                                        color = Color(0xff9c9c9c),
                                        fontFamily = FontFamily(Font(R.font.inter_normal)),
                                        fontSize = 5.sp,
                                        modifier = Modifier
                                            .padding(top = 7.dp)
                                    )
                                }
                                Column()
                                {
                                    Text(
                                        text = "Pedro Victor",
                                        color = Color(0xff9c9c9c),
                                        fontFamily = FontFamily(Font(R.font.inter_normal)),
                                        fontSize = 5.sp,
                                        modifier = Modifier
                                            .padding(top = 10.dp)
                                    )
                                    Text(
                                        text = "Ana Clara",
                                        color = Color(0xff9c9c9c),
                                        fontFamily = FontFamily(Font(R.font.inter_normal)),
                                        fontSize = 5.sp,
                                        modifier = Modifier
                                            .padding(top = 7.dp)
                                    )
                                }
                                Column()
                                {
                                    Text(
                                        text = "Laura Sofia",
                                        color = Color(0xff9c9c9c),
                                        fontFamily = FontFamily(Font(R.font.inter_normal)),
                                        fontSize = 5.sp,
                                        modifier = Modifier
                                            .padding(top = 10.dp)
                                    )
                                    Text(
                                        text = "Beatriz",
                                        color = Color(0xff9c9c9c),
                                        fontFamily = FontFamily(Font(R.font.inter_normal)),
                                        fontSize = 5.sp,
                                        modifier = Modifier
                                            .padding(top = 7.dp)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.width(60.dp))

                        Column(
                            modifier = Modifier
                                .fillMaxHeight(),
                        )
                        {
                            Text(
                                text = "Acesse",
                                color = Color(0xffffffff),
                                fontFamily = FontFamily(Font(R.font.inter_bold)),
                                fontSize = 7.sp,
                                modifier = Modifier
                                    .padding(top = 15.dp)
                            )
                            Text(
                                text = "Perfil",
                                color = Color(0xff9c9c9c),
                                fontFamily = FontFamily(Font(R.font.inter_normal)),
                                fontSize = 5.sp,
                                modifier = Modifier
                                    .padding(top = 10.dp)
                                    .clickable { navegacao!!.navigate("perfil")}
                            )
                            Text(
                                text = "Calendário",
                                color = Color(0xff9c9c9c),
                                fontFamily = FontFamily(Font(R.font.inter_normal)),
                                fontSize = 5.sp,
                                modifier = Modifier
                                    .padding(top = 7.dp)
                            )
                            Text(
                                text = "Espaços TEA",
                                color = Color(0xff9c9c9c),
                                fontFamily = FontFamily(Font(R.font.inter_normal)),
                                fontSize = 5.sp,
                                modifier = Modifier
                                    .padding(top = 7.dp)
                            )
                        }
                        
                    }
                }


                //Barra inferior
               Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(
                        topEnd = 0.dp,
                        topStart = 0.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xD6000000)
                    )
                )
                {
                    Row(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        Text(
                            text = "©",
                            color = Color(0xff8E8E8E),
                            fontFamily = FontFamily(Font(R.font.inter_normal)),
                            fontSize = 6.sp,
                            modifier = Modifier

                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            text = "2025 TeAjuda. Todos os direitos reservados.",
                            color = Color(0xff8E8E8E),
                            fontFamily = FontFamily(Font(R.font.inter_normal)),
                            fontSize = 5.sp,
                            modifier = Modifier

                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(top = 14.dp)
                            .fillMaxSize(),
                        horizontalArrangement = Arrangement.Center
                    )
                    {
                        Image(
                            painter = painterResource(R.drawable.calendario),
                            contentDescription = "",
                            modifier = Modifier
                                .size(24.dp)
                        )

                        Spacer(modifier = Modifier.width(80.dp))

                        Image(
                            painter = painterResource(R.drawable.casinha),
                            contentDescription = "",
                            modifier = Modifier
                                .size(24.dp)
                                .clickable { navegacao!!.navigate("home") }
                        )

                        Spacer(modifier = Modifier.width(80.dp))

                        Image(
                            painter = painterResource(R.drawable.medicina),
                            contentDescription = "",
                            modifier = Modifier
                                .size(24.dp)
                                .clickable { navegacao!!.navigate("") }
                        )
                    }
               }
            }

    }
}

@Preview
@Composable
private fun TelaHomePreview() {
    TelaHome(null)
}