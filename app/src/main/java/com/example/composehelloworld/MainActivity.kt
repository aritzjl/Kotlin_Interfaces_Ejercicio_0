package com.example.composehelloworld

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composehelloworld.ui.theme.ComposeHelloWorldTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

var total_likes by mutableIntStateOf(0)
var toolbarTitle by mutableStateOf("Inicio")

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolbarTitle = "Hola"
        enableEdgeToEdge()
        setContent {
            HomeView()
        }
    }
}

@Composable
fun FAB(){
    val context = LocalContext.current
    FloatingActionButton(onClick = {
        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf("aritzzjl@gmail.com"))
            putExtra(Intent.EXTRA_SUBJECT, "He visto tu portfolio de Kotlin")
        }
        if (emailIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(emailIntent)
        }
    }) {
        Image(painter= painterResource(R.drawable.baseline_email_24), contentDescription = "Icono", modifier= Modifier
            .width(48.dp)
            .clickable {
                val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:")
                    putExtra(Intent.EXTRA_EMAIL, arrayOf("aritzzjl@gmail.com"))
                    putExtra(Intent.EXTRA_SUBJECT, "He visto tu portfolio de Kotlin")
                }
                if (emailIntent.resolveActivity(context.packageManager) != null) {
                    context.startActivity(emailIntent)
                }
            })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBar() {
    val context = LocalContext.current
    TopAppBar(
        title = { Text(text = toolbarTitle, color = Color.Black) },
        actions = {
            IconButton(onClick = {
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "Echa un vistazo a mi web https://github.com/aritzjl")
                    type = "text/plain"
                }
                context.startActivity(Intent.createChooser(shareIntent, "Compartir vía"))
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.share_icon),
                    contentDescription = "Compartir",
                    tint = Color.Black
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
    )
}

@Composable
fun BottomNavBar(navController: NavHostController) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            selected = navController.currentBackStackEntry?.destination?.route == "home",
            onClick = {
                toolbarTitle = "Inicio"
                navController.navigate("home") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Info, contentDescription = "Info") },
            selected = navController.currentBackStackEntry?.destination?.route == "about",
            onClick = {
                toolbarTitle = "Sobre mí"
                navController.navigate("about") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Create, contentDescription = "Gallery") },
            selected = navController.currentBackStackEntry?.destination?.route == "gallery",
            onClick = {
                toolbarTitle = "Galería"
                navController.navigate("gallery") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
            selected = navController.currentBackStackEntry?.destination?.route == "settings",
            onClick = {
                toolbarTitle = "Ajustes"
                navController.navigate("settings") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}

//  SETTINGS VIEW
@Preview
@Composable
fun SettingsView() {
    val navController = rememberNavController()
    Scaffold(
        topBar = { ToolBar() },
        bottomBar = { BottomNavBar(navController) },
        content = { paddingValues ->
            NavHost(navController = navController, startDestination = "home") {
                composable("home") { HomeContent(paddingValues) }
                composable("about") { AboutView(paddingValues) }
                composable("gallery") { GalleryContent(paddingValues) }
                composable("settings") { SettingsContent(paddingValues) }
            }
        }
    )
}

@Composable
fun SettingsContent(paddingValues: PaddingValues) {
    LazyColumn(modifier= Modifier
        .fillMaxSize()
        .background(Color.DarkGray)
        .padding(paddingValues)) {
        item {
            Text(text = "Ajustes", fontWeight = FontWeight.ExtraBold ,fontSize = 32.sp, color = Color.White, modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp), textAlign = TextAlign.Center)
        }
    }
}
//  HOME VIEW
@Preview
@Composable
fun HomeView() {
    val navController = rememberNavController()
    Scaffold(
        topBar = { ToolBar() },
        bottomBar = { BottomNavBar(navController) },
        content = { paddingValues ->
            NavHost(navController = navController, startDestination = "home") {
                composable("home") { HomeContent(paddingValues) }
                composable("about") { AboutView(paddingValues) }
                composable("gallery") { GalleryContent(paddingValues) }
                composable("settings") { SettingsContent(paddingValues) }
            }
        }
    )
}

@Composable
fun HomeContent(paddingValues: PaddingValues) {
    LazyColumn(modifier= Modifier
        .fillMaxSize()
        .background(Color.DarkGray)
        .padding(paddingValues)) {
        item {
            Text(text = "Inicio", fontWeight = FontWeight.ExtraBold ,fontSize = 32.sp, color = Color.White, modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp), textAlign = TextAlign.Center)
        }
    }
}

//  GALLERY VIEW
@Preview
@Composable
fun GalleryView() {
    val navController = rememberNavController()
    Scaffold(
        topBar = { ToolBar() },
        bottomBar = { BottomNavBar(navController) },
        content = { paddingValues ->
            NavHost(navController = navController, startDestination = "home") {
                composable("home") { HomeContent(paddingValues) }
                composable("about") { AboutView(paddingValues) }
                composable("gallery") { GalleryContent(paddingValues) }
                composable("settings") { SettingsContent(paddingValues) }
            }
        }
    )
}

@Composable
fun GalleryContent(paddingValues: PaddingValues) {
    LazyColumn(modifier= Modifier
        .fillMaxSize()
        .background(Color.DarkGray)
        .padding(paddingValues)) {
        item {
            Text(text = "Galería Proyectos", fontWeight = FontWeight.ExtraBold ,fontSize = 32.sp, color = Color.White, modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp), textAlign = TextAlign.Center)
        }
    }
}



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AboutView(paddingValues2: PaddingValues) {
    val navController = rememberNavController()
    Scaffold(
        topBar = { ToolBar() },
        bottomBar = { BottomNavBar(navController) },
        floatingActionButton = { FAB() },
        content = { paddingValues ->
            NavHost(navController = navController, startDestination = "about") {
                composable("about") { AboutContent(paddingValues2) }
            }
        }
    )
}



@Composable
fun AboutContent(paddingValues: PaddingValues) {
    val counter by remember { derivedStateOf { total_likes } }

    LazyColumn(modifier= Modifier
        .fillMaxSize()
        .background(Color.DarkGray)
        .padding(paddingValues))
    {
        item{
            Image(painter= painterResource(R.drawable.img_1), contentDescription = "Icono", modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .clip(
                    CircleShape
                ))
            Text(text = "Aritz Jaber", fontWeight = FontWeight.ExtraBold ,fontSize = 32.sp, color = Color.White, modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp), textAlign = TextAlign.Center)
            Text(text = "** Full Stack Developer **", fontWeight = FontWeight.SemiBold ,fontSize = 28.sp, color = Color.LightGray, modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp), textAlign = TextAlign.Center)
            LazyRow(horizontalArrangement = Arrangement.SpaceBetween, modifier= Modifier
                .fillMaxWidth()
                .padding(16.dp))
            {
                item{
                    Image(painter= painterResource(R.drawable.img_3), contentDescription = "Icono", modifier= Modifier
                        .width(48.dp)
                        .clickable {
                            total_likes++
                        })
                    Text(text = counter.toString(), fontWeight = FontWeight.Normal ,fontSize = 20.sp, color = Color.White, modifier = Modifier
                        .padding(10.dp)
                        .padding(top = 12.dp))
                }
            }
            Row (modifier= Modifier
                .fillMaxWidth()
                .height(5.dp)
                .background(Color.LightGray)){

            }
            Text(
                fontSize = 18.sp, color = Color.White, modifier = Modifier.padding(10.dp),
                text="¡Hola! Soy Aritz, un apasionado desarrollador de software con una sólida experiencia en la creación de soluciones tecnológicas innovadoras. Mi enfoque se centra en el desarrollo personalizado de software y la creación de experiencias digitales únicas."
            )
            Text(
                fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White, modifier = Modifier.padding(10.dp),
                text="Acerca de mí \uD83D\uDCBB"
            )

            Text(
                fontSize = 18.sp, color = Color.White, modifier = Modifier.padding(7.dp),
                text = "Soy un estudiante de DAW de primer año, que lleva más de un año trabajando como freelance en la plataforma Fiverr, gracias a ello, he podido participar en decenas de proyectos, con gente de todas partes del mundo, lo cual me ha dado la oportunidad de aprender a desenvolverme en diferentes ámbitos de la programación, además de realizar encargos con ideas innovadoras."
            )

            Text(
                fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White, modifier = Modifier.padding(10.dp),
                text="Conocimientos Técnicos \uD83D\uDEE0\uFE0F"
            )

            LazyRow(horizontalArrangement = Arrangement.SpaceEvenly, modifier= Modifier
                .fillMaxWidth()
                .padding(16.dp))
            {
                item {
                    Image(painter= painterResource(R.drawable.img_4), contentDescription = "Icono", modifier=Modifier.width(48.dp))
                    Image(painter= painterResource(R.drawable.img_5), contentDescription = "Icono", modifier=Modifier.width(48.dp))
                    Image(painter= painterResource(R.drawable.img_6), contentDescription = "Icono", modifier=Modifier.width(48.dp))
                    Image(painter= painterResource(R.drawable.img_7), contentDescription = "Icono", modifier=Modifier.width(48.dp))
                    Image(painter= painterResource(R.drawable.img_9), contentDescription = "Icono", modifier=Modifier.width(48.dp))
                    Image(painter= painterResource(R.drawable.img_10), contentDescription = "Icono", modifier=Modifier.width(48.dp))
                }
            }

            Text(
                fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White, modifier = Modifier.padding(10.dp),
                text="Habilidades y Especialidades \uD83C\uDFAF"
            )

            Text(
                fontSize = 18.sp, color = Color.White, modifier = Modifier.padding(7.dp),
                text = "* Desarrollo de software personalizado\n" +
                        "* Diseño y creación de sitios web a medida\n" +
                        "* Integración de tecnologías emergentes, incluida la inteligencia artificial\n" +
                        "* Servicios de almacenamiento en la nube y administración de servidores"
            )

            Text(
                fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White, modifier = Modifier.padding(10.dp),
                text="Contacto \uD83D\uDCE7 \uD83D\uDCDE"
            )

            LazyRow(horizontalArrangement = Arrangement.SpaceEvenly, modifier= Modifier
                .fillMaxWidth()
                .padding(16.dp))
            {
                item {
                    Image(painter= painterResource(R.drawable.img_4), contentDescription = "Icono", modifier=Modifier.width(48.dp))
                    Image(painter= painterResource(R.drawable.img_5), contentDescription = "Icono", modifier=Modifier.width(48.dp))
                }
            }
        }
    }

}