package com.example.api_retrofit

// Clase creada desde el JSON
data class Tarea(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)