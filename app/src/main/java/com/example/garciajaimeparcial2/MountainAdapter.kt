package com.example.garciajaimeparcial2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class MountainAdapter(private val context: Context, private val mountains: List<Mountain>) : BaseAdapter() {
    override fun getCount(): Int = mountains.size

    override fun getItem(position: Int): Any = mountains[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item_mountain, parent, false)
        val mountain = mountains[position]
        val ivIcon = view.findViewById<ImageView>(R.id.iv_mountain_icon)
        val tvInfo = view.findViewById<TextView>(R.id.tv_mountain_info)
        val tvConcejo = view.findViewById<TextView>(R.id.tv_concejo)

        // Cargar la imagen a partir del nombre de la montaña; si no existe, usar una imagen por defecto
        val resourceId = context.resources.getIdentifier(mountain.nombre.toLowerCase(), "drawable", context.packageName)
        if (resourceId != 0) {
            ivIcon.setImageResource(resourceId)
        } else {
            ivIcon.setImageResource(R.drawable.default_mountain_icon)
        }

        tvInfo.text = "${mountain.nombre} - ${mountain.altura}"
        tvConcejo.text = mountain.concejo

        return view
    }
}
