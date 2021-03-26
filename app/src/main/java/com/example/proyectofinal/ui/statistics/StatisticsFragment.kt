package com.example.proyectofinal.ui.statistics

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.proyectofinal.R
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class StatisticsFragment : Fragment() {

    private lateinit var statisticsViewModel: StatisticsViewModel
    lateinit var lastUpdate: TextView
    lateinit var recovered: TextView
    lateinit var confirmed: TextView
    lateinit var deaths: TextView
    lateinit var image: ImageView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_statistics, container, false)

        statisticsViewModel =
                ViewModelProvider(this).get(StatisticsViewModel::class.java)

        lastUpdate = root.findViewById(R.id.lastUpdate)
        recovered = root.findViewById(R.id.recovered)
        confirmed = root.findViewById(R.id.confirmed)
        deaths = root.findViewById(R.id.deaths)
        image = root.findViewById(R.id.ImageView)
        val uiHandler = Handler(Looper.getMainLooper())
        uiHandler.post(Runnable {
            Picasso.get().load("https://i.pinimg.com/originals/47/b4/70/47b47090853fdfb505d07c60e1db586f.png").into(image)
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
                val response = api.getStatistics()
                lastUpdate.text = "- Última actualización: ${response.lastUpdate.split("T")[0]}"
                recovered.text = "- Recuperados: ${response.recovered}"
                confirmed.text = "- Confirmados: ${response.confirmed}"
                deaths.text = "- Fallecidos: ${response.deaths}"
            } catch (e: Exception) {
                Log.e("Estadisticas", "Error: ${e.message}")

            }
        }

    }


}