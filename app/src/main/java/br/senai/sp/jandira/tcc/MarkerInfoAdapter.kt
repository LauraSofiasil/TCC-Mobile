package br.senai.sp.jandira.tcc

import android.view.LayoutInflater
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import android.content.Context
import android.widget.TextView

class MarkerInfoAdapter(private val context: Context) : GoogleMap.InfoWindowAdapter {

    override fun getInfoContents(marker: Marker): View? = null


    override fun getInfoWindow(marker: Marker): View? {

        val place = marker.tag as? Place ?: return null

        val view = LayoutInflater.from(context).inflate(R.layout.custom_marker, null)

        view.findViewById<TextView>(R.id.txt_title).text = place.name
        view.findViewById<TextView>(R.id.txt_address).text = place.address
        view.findViewById<TextView>(R.id.txt_rating).text = context.getString(R.string.rating, place.rating)

        return view
    }
}