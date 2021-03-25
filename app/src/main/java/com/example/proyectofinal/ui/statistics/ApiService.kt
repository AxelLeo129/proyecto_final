package com.example.proyectofinal.ui.statistics

import retrofit2.http.GET

interface ApiService {
    @GET("covid")
    suspend fun getStatistics(): StatisticResponse
}