package com.example.api_retrofit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// Puente entre view y modelo
class ApiViewModel : ViewModel() {
    private val _modeloTareas = MutableStateFlow(Modelo())
    val getModelo = _modeloTareas.asStateFlow()

    fun obtenerTodasLasTareas() {
        // Al ser asíncrono (suspend) hay que hacer launch
        viewModelScope.launch {
            // Ejecutamos la Query
            val response = API.apiDao.getAllTasks()
            if (response.isSuccessful) {
                // Actualizamos el modelo con la copia y añadiendo el contenido que cambiamos
                // Se hace elvis porque trata e lvalor nulo. Y si nos da nulo, la ponemos vacía
                _modeloTareas.update { it.copy(listaTareas = (response.body() ?: emptyList()) as MutableList<Tarea>) }
            } else {
                println("Error: ${response.code()}")
            }
        }
    }

    fun obtenerTareaPorId(id: Int) {
        viewModelScope.launch {
            // Ejecutamos la Query
            val response = API.apiDao.getTaskById(id)
            if (response.isSuccessful) {
                _modeloTareas.update { it.copy(tareaSeleccionada = response.body()) }
            } else {
                println("Error: ${response.code()}")
            }
        }
    }
    fun crearTarea(tarea: Tarea) {
        viewModelScope.launch {
            // Ejecutamos la Query
            val response = API.apiDao.createTask(tarea)
            if (response.isSuccessful) {
                _modeloTareas.update {
                    it.listaTareas.add(tarea)
                    it.copy(listaTareas = it.listaTareas)
                }
            } else {
                println("Error: ${response.code()}")
            }
        }
    }

}
