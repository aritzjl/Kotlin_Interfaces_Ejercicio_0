package com.example.composehelloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composehelloworld.ui.theme.ComposeHelloWorldTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ColumnsTest()
        }
    }
}



@Preview(showBackground = true)
@Composable
fun ColumnsTest() {
    var counter by remember { mutableStateOf(value=0) }
    LazyColumn(modifier=Modifier.fillMaxSize().background(Color.DarkGray))
    {
        item{
            Image(painter= painterResource(R.drawable.img_1), contentDescription = "Icono", modifier = Modifier.fillMaxWidth().height(400.dp).clip(
                CircleShape))
            Text(text = "Aritz Jaber", fontWeight = FontWeight.ExtraBold ,fontSize = 32.sp, color = Color.White, modifier = Modifier.fillMaxWidth().padding(10.dp), textAlign = TextAlign.Center)
            Text(text = "** Full Stack Developer **", fontWeight = FontWeight.SemiBold ,fontSize = 28.sp, color = Color.LightGray, modifier = Modifier.fillMaxWidth().padding(10.dp), textAlign = TextAlign.Center)
            LazyRow(horizontalArrangement = Arrangement.SpaceBetween, modifier=Modifier.fillMaxWidth().padding(16.dp))
            {
                item{
                    Image(painter= painterResource(R.drawable.img_3), contentDescription = "Icono", modifier=Modifier.width(48.dp).clickable {
                        counter++
                    })
                    Text(text = counter.toString(), fontWeight = FontWeight.Normal ,fontSize = 20.sp, color = Color.White, modifier = Modifier.padding(10.dp).padding(top = 12.dp))
                }
            }
            Row (modifier=Modifier.fillMaxWidth().height(5.dp).background(Color.LightGray)){

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

            LazyRow(horizontalArrangement = Arrangement.SpaceEvenly, modifier=Modifier.fillMaxWidth().padding(16.dp))
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

            LazyRow(horizontalArrangement = Arrangement.SpaceEvenly, modifier=Modifier.fillMaxWidth().padding(16.dp))
            {
                item {
                    Image(painter= painterResource(R.drawable.img_4), contentDescription = "Icono", modifier=Modifier.width(48.dp))
                    Image(painter= painterResource(R.drawable.img_5), contentDescription = "Icono", modifier=Modifier.width(48.dp))
                }
            }
        }
    }
}