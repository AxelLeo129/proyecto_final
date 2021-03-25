package com.example.proyectofinal.ui.map
import com.example.proyectofinal.ui.map.MapViewModel



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.proyectofinal.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback

class MapFragment : Fragment() {

    private lateinit var mapViewModel: MapViewModel
    private lateinit var map: GoogleMap
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mapViewModel =
                ViewModelProvider(this).get(MapViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_map, container, false)

        return root
    }


}
