package com.example.api_retrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.api_retrofit.ui.theme.API_RetrofitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            API_RetrofitTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ListaTareas(innerPadding)
                }
            }
        }
    }
}

@Composable
// Pasamos por parámetro la ApiViewModel
fun ListaTareas(paddingValues: PaddingValues, tareasViewModel: ApiViewModel = viewModel()) {
    val estadoActual =
        tareasViewModel.getModelo.collectAsState() // Coger el estado actual (del flujo)
    Column(
        Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Al pulsar uno de los botones, hará la función correspondiente
        Button({ tareasViewModel.obtenerTodasLasTareas() }) { Text("Obtener lista") }
        Button({ tareasViewModel.obtenerTareaPorId(8) }) { Text("Obtener tarea 8") }
        Text(estadoActual.value.tareaSeleccionada.toString(), modifier = Modifier.padding(24.dp))
        Spacer(Modifier.padding(24.dp))
        Button({
            tareasViewModel.crearTarea(
                Tarea(
                    title = "Felices fiestas a todos!",
                    id = 3
                )
            )
        }) { Text("Crear tarea") }
        // Columna con scroll
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            // Mapeas las tareas desde el estado actual
            // PARA QUE FUNCIONE, HAY QUE AÑADIR EN EL MANIFEST PERMISO DE RED
            items(estadoActual.value.listaTareas) {
                Text("Título de la tarea: ${it.title}")
            }
        }
    }
}