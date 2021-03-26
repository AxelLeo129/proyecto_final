package com.example.proyectofinal.ui.news

import java.io.Serializable

class New : Serializable {

    var titulo: String
    var category: String
    var date: String
    var imagen: String
    var description: String

    constructor(titulo: String, category: String, date: String, imagen: String, description: String) {
        this.titulo = titulo
        this.category = category
        this.date = date
        this.imagen = imagen
        var new_description: String = description.replace("<p>", "")
        new_description = new_description.replace("<br>", "\n")
        new_description = new_description.replace("<br/>", "\n")
        new_description = new_description.replace("</p>", "")
        this.description = new_description
    }
}