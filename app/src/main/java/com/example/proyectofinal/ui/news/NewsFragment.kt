package com.example.proyectofinal.ui.news;

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.proyectofinal.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class NewsFragment : Fragment() {

        private lateinit var newsViewModel: NewsViewModel

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
                newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
                val root = inflater.inflate(R.layout.fragment_news, container, false)
                val textView: TextView = root.findViewById(R.id.text_news)
                newsViewModel.text.observe(viewLifecycleOwner, Observer {
                        textView.text = it
                })
                return root
        }

        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                val api = Retrofit.Builder()
                        .baseUrl("http://gtpreviene.researchmobile.co:3000/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(ApiService::class.java)

                CoroutineScope(Dispatchers.IO).launch {
                        try {
                                val response = api.getNews()
                                Log.e("Main", "${response.whatsapp_phone}")
                        } catch (e: Exception) {
                                Log.e("Main", "Error: ${e.message}")
                        }
                }
        }

}
