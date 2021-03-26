package com.example.proyectofinal.ui.emergency

import retrofit2.http.GET

interface ApiService {
    @GET("information")
    suspend fun getPhones(): EmergencyResponse
}