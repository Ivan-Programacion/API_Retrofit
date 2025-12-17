package com.example.api_retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object API {
    // La url principal
    private const val BASE_URL = "https://jsonplaceholder.typicode.com"

    // by lazy --> punto acceso. Se inicializa sólo cuando se llama a la api, y solo una vez
    val apiDao: TareaDao by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Para convertir JSON en GSON
            .build()
            .create(TareaDao::class.java)
    }
}