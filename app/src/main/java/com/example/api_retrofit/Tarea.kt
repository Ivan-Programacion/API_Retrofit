package com.example.api_retrofit

// Clase creada desde el JSON
data class Tarea(
    val completed: Boolean = false,
    val id: Int = 0,
    val title: String = "",
    val userId: Int = 0
)