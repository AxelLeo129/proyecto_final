package com.example.proyectofinal

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.proyectofinal.ui.news.New
import com.squareup.picasso.Picasso

class ViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        var modalNews: New = intent.getSerializableExtra("data") as New

        var title: TextView = findViewById(R.id.ImageTitle)
        title.text = "${modalNews.titulo}"
        var date: TextView = findViewById(R.id.ImageInfo)
        date.text = "${modalNews.date}"
        var category: TextView = findViewById(R.id.ImageCategory)
        category.text = "${modalNews.category}"
        var detalle: TextView = findViewById(R.id.ImageDescription)
        detalle.text = "${modalNews.description}"
        var image: ImageView = findViewById(R.id.ImageView)
        Picasso.get().load(modalNews.imagen).into(image)
    }
}