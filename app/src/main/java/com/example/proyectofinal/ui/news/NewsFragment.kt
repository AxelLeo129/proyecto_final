package com.example.proyectofinal.ui.news;

import android.content.Context
import android.content.Intent
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
import com.example.proyectofinal.ViewActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_item.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsFragment : Fragment() {

        private lateinit var newsViewModel: NewsViewModel
        private var lista_noticias = ArrayList<New>()
        private  lateinit var grid_view: GridView

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
                newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
                val root = inflater.inflate(R.layout.fragment_news, container, false)
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
                                val response = api.getNews()
                                for (i in response) {
                                        lista_noticias.add(New(i.title, i.name, i.start.split("T")[0], i.image, i.detail))
                                }
                                requireActivity().runOnUiThread {
                                        renderGrid(lista_noticias)
                                }
                        } catch (e: Exception) {
                                Log.e("Main", "Error: ${e.message}")
                        }
                }
        }

        private fun renderGrid(listado: ArrayList<New>) {
                var adaptador_personalizado = AdaptadorPersonalizado(listado, requireActivity())

                grid_view.adapter = adaptador_personalizado

                grid_view.setOnItemClickListener { arg0, arg1, position, arg3 ->
                        var intent = Intent(requireActivity(), ViewActivity::class.java)
                        intent.putExtra("data", lista_noticias[position])
                        startActivity(intent)
                }
        }

        class AdaptadorPersonalizado(var elementoModelo: ArrayList<New>, var contexto: Context) : BaseAdapter() {

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
                                vista = diseño_plano.inflate(R.layout.row_item1, parent, false)
                        }

                        var tv_titulo_imagen = vista?.findViewById<TextView>(R.id.ImageTitle)
                        var tv_fecha_imagen = vista?.findViewById<TextView>(R.id.ImageInfo)
                        var tv_vista_imagen = vista?.findViewById<ImageView>(R.id.ImageView)
                        var tv_categoria_imagen = vista?.findViewById<TextView>(R.id.ImageCategory)

                        tv_titulo_imagen?.text = elementoModelo[position].titulo
                        tv_fecha_imagen?.text = elementoModelo[position].date
                        tv_categoria_imagen?.text = elementoModelo[position].category
                        Picasso.get().load(elementoModelo[position].imagen!!).into(tv_vista_imagen)

                        return vista!!

                }

        }

}
