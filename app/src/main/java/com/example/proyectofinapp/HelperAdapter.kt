package com.example.proyectofinapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.proyectofinapp.databinding.HelperAdapterBinding

class HelperAdapter (context: Context, helpers: List<Helpers>) : ArrayAdapter<Helpers>(context, 0, helpers){
    private val helpersArray: MutableList<Helpers> = ArrayList(helpers)

    override fun getCount() = helpersArray.size

    override fun getItem(pos: Int) = helpersArray[pos]

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: HelperAdapterBinding = if(convertView == null)
            HelperAdapterBinding.inflate(LayoutInflater.from(context), parent, false)
        else
            HelperAdapterBinding.bind(convertView)

        //Load country information
        binding.name.text = helpersArray[position].name
        binding.description.text = helpersArray[position].description
        binding.numberRating.text = helpersArray[position].numberRating

        return binding.root
    }
}