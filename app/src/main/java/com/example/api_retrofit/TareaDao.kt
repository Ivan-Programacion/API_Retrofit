package com.example.api_retrofit

import org.w3c.dom.Comment
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface TareaDao {
    @GET("/todos")
    // Cuando hay servidor de por medio, SIEMPRE debe ser suspend (asíncrono)
    suspend fun getAllTasks(): Response<List<Tarea>> // Response en vez de Flow

    @GET("/todos/{id}")
    suspend fun getTaskById(@Path("id") id: Int): Response<Tarea>

    @POST("/todos") // Crear una tarea
    suspend fun createTask(@Body tarea: Tarea): Response<Tarea>

    @PUT("/posts/{id}/comments")
    suspend fun updateCommentBypostId(@Path("id") id: Int, @Body newComment: Comment)
}