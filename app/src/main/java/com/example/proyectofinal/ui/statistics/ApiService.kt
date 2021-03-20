package com.example.proyectofinal.ui.statistics

import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET("covid")
    suspend fun getStatistics(): StatisticResponse
}