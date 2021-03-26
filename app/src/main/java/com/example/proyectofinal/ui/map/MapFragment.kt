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
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mapViewModel: MapViewModel
    private lateinit var map: GoogleMap
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        mapViewModel =
                ViewModelProvider(this).get(MapViewModel::class.java)

        val root: View = inflater.inflate(R.layout.fragment_map, container, false)
        val supportMapFragment: SupportMapFragment = getChildFragmentManager().findFragmentById(R.id.google_map) as SupportMapFragment
        supportMapFragment.getMapAsync(this)
        return root
    }



    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        Antut(googleMap)
    }
    fun Antut(googleMap: GoogleMap){
        map = googleMap
        var punto1: LatLng = LatLng(14.6090025,-90.525939)
        map.addMarker(MarkerOptions().position(punto1).title("Parque de la industria"))
    }


}
