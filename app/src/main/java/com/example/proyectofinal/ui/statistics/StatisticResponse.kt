package com.example.proyectofinal.ui.statistics

data class StatisticResponse(
    val confirmed: Int,
    val country: String,
    val deaths: Int,
    val enable: Int,
    val lastUpdate: String,
    val recovered: Int
)