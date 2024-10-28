package com.example.catho

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.catho.ui.theme.CathoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Aplicación de encuesta simple
            CathoTheme {
                MiEncuestaApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiEncuestaApp() {
    // Variables de estado para las respuestas de la encuesta
    var nombre by remember { mutableStateOf("") }
    var aceptaTerminos by remember { mutableStateOf(false) }
    var sliderValue by remember { mutableStateOf(50f) }
    var resumenVisible by remember { mutableStateOf(false) }  // Mostrar resumen después de enviar

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!resumenVisible) {
            // TextField para ingresar nombre
            TextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("¿Cuál es tu nombre?") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Checkbox para aceptar términos
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("¿Aceptas los términos?")
                Checkbox(
                    checked = aceptaTerminos,
                    onCheckedChange = { aceptaTerminos = it }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Slider para calificar algo (ejemplo: satisfacción)
            Text("¿Cómo calificarías tu experiencia? ${sliderValue.toInt()}")
            Slider(
                value = sliderValue,
                onValueChange = { sliderValue = it },
                valueRange = 0f..100f,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para enviar respuestas
            Button(onClick = { resumenVisible = true }) {
                Text("Enviar")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Imagen decorativa (asegúrate de tener una imagen en drawable)
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Imagen decorativa",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(100.dp)
            )
        } else {
            // Mostrar el resumen después de enviar
            text()
            Spacer(modifier = Modifier.height(16.dp))
            Text("Nombre: $nombre")
            Text("Acepta términos: ${if (aceptaTerminos) "Sí" else "No"}")
            Text("Calificación de la experiencia: ${sliderValue.toInt()}")
        }
    }
}

fun text() {

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CathoTheme {
        MiEncuestaApp()
    }
}


