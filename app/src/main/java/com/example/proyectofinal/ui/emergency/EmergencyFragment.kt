package com.example.proyectofinal.ui.emergency

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.proyectofinal.R
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class EmergencyFragment : Fragment() {

    private lateinit var emergencyViewModel: EmergencyViewModel
    private var lista_telefonos = ArrayList<Phone>()
    private  lateinit var grid_view: GridView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        emergencyViewModel =
                ViewModelProvider(this).get(EmergencyViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_emergency, container, false)
        grid_view = root.findViewById(R.id.gridView) as GridView
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
                val response = api.getPhones()
                for (i in response.data) {
                    lista_telefonos.add(Phone(i.title, i.phone, i.icon))
                }
                requireActivity().runOnUiThread {
                    renderGrid(lista_telefonos)
                }
            } catch (e: Exception) {
                Log.e("Main", "Error: ${e.message}")
            }
        }
    }

    private fun renderGrid(listado: ArrayList<Phone>) {
        var adaptador_personalizado = AdaptadorPersonalizado(listado, requireActivity())

        grid_view.adapter = adaptador_personalizado

        grid_view.setOnItemClickListener { arg0, arg1, position, arg3 ->
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${lista_telefonos[position].phone}")
            startActivity(intent)
            true
        }
    }

    class AdaptadorPersonalizado(var elementoModelo: ArrayList<Phone>, var contexto: Context) : BaseAdapter() {

        var diseño_plano = contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        override fun getCount(): Int {
            return elementoModelo.size
        }

        override fun getItem(position: Int): Any {
            return elementoModelo[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }


        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var vista = convertView
            if(vista == null) {
                vista = diseño_plano.inflate(R.layout.row_item, parent, false)
            }

            var tv_titulo_imagen = vista?.findViewById<TextView>(R.id.ImageTitle)
            var tv_descripcion_imagen = vista?.findViewById<TextView>(R.id.ImageInfo)
            var tv_vista_imagen = vista?.findViewById<ImageView>(R.id.ImageView)

            tv_titulo_imagen?.text = elementoModelo[position].titulo
            tv_descripcion_imagen?.text = elementoModelo[position].phone
            //tv_vista_imagen?.setImageResource(elementoModelo[position].imagen!!)
            Picasso.get().load(elementoModelo[position].imagen!!).into(tv_vista_imagen)

            return vista!!

        }

    }

}