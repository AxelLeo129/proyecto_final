package com.example.proyectofinal.ui.news

import retrofit2.http.GET

interface ApiService {
    @GET("news")
    suspend fun getNews(): NewResponse
}