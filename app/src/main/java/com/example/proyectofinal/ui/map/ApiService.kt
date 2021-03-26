package com.example.proyectofinal.ui.map

import retrofit2.http.GET

interface ApiService {
    @GET("centres")
    suspend fun getPoints(): MapResponse
}