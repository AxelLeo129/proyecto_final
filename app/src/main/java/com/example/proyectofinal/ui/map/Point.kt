package com.example.proyectofinal.ui.map

class Point {

    var nombre: String
    var lat: Double
    var lang: Double

    constructor(nombre: String, lat: String, lang: String) {
        this.nombre = nombre
        this.lat =  lat.toDouble()
        this.lang = lang.toDouble()
    }


}