package com.example.proyectofinal.ui.news

import retrofit2.http.GET

interface ApiService {
    @GET("information")
    suspend fun getNews(): NewResponse
}