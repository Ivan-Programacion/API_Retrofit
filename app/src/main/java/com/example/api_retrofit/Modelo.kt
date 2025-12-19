package com.example.api_retrofit

data class Modelo(
    val listaTareas: MutableList<Tarea> = emptyList<Tarea>().toMutableList(),
    val tareaSeleccionada: Tarea? = null
)
