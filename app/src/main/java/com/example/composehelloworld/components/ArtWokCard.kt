package com.example.composehelloworld.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composehelloworld.Artwork
import com.example.composehelloworld.ArtworkStyle
import com.example.composehelloworld.R

@Composable
fun ArtworkCard(artwork: Artwork) {

    // Selección del ícono según el estilo de la obra
    val iconResId = when (artwork.style) {
        ArtworkStyle.WATERCOLOUR -> R.drawable.baseline_palette_24
        ArtworkStyle.DIGITAL -> R.drawable.baseline_video_stable_24
        ArtworkStyle.INK -> R.drawable.baseline_border_color_24
    }

    Box(
        modifier = Modifier
            .padding(6.dp) // Padding alrededor de la tarjeta
            .shadow(8.dp, RoundedCornerShape(16.dp)) // Sombra con bordes redondeados
            .background(Color.White, RoundedCornerShape(16.dp)) // Fondo blanco y bordes redondeados
            .border(2.dp, Color.White, RoundedCornerShape(16.dp)) // Borde fino
            .fillMaxWidth() // Ajustar el tamaño de la tarjeta
    ) {
        Column () {
            // Contenedor para superponer el icono sobre la imagen
            Box {
                // Imagen de la obra de arte
                Image(
                    painter = painterResource(id = artwork.imageResId),
                    contentDescription = "Imagen de la obra de arte",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth() // Llenar el ancho
                        .aspectRatio(1f) // Relación de aspecto 1:1 para que sea cuadrada
                        .clip(RoundedCornerShape(16.dp)) // Redondear la imagen
                )

                // Fondo para el icono (recuadro gris oscuro)
                Box(
                    modifier = Modifier
                        .size(36.dp) // Tamaño del recuadro
                        .background(Color(0xFF2C2C2C), RoundedCornerShape(8.dp)) // Color gris oscuro con bordes redondeados
                        .align(Alignment.TopEnd) // Alinear en la esquina superior derecha
                        .padding(8.dp) // Padding para ajustar la posición
                ) {

                    // Icono superpuesto
                    Icon(
                        painter = painterResource(id = iconResId),
                        contentDescription = "Icono del estilo de la obra de arte",
                        modifier = Modifier
                            .size(24.dp) // Tamaño del icono
                            .align(Alignment.Center), // Centrar el icono dentro del recuadro
                        tint = Color.White // Color del icono
                    )
                }
            }

            // Fondo para el titulo
            Row(
                modifier = Modifier
                    .fillMaxWidth() // Llenar el ancho
                    .background(Color(0xFF2C2C2C), RoundedCornerShape(8.dp)) // Color gris oscuro con bordes redondeados
                    .padding(8.dp) // Padding para ajustar la posición
            ) {
                // Título de la obra de arte
                Text(
                    text = artwork.title,
                    style = MaterialTheme.typography.bodyLarge, // Usando la fuente EB Garamond definida en el tema
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(8.dp) // Padding alrededor del título,
                    , color = Color.White // Color del texto
                )
            }

        }
    }
}