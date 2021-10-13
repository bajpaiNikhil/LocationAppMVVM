package com.example.locationappmvvm.others

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.locationappmvvm.LocationViewMode.LocationViewModelIs
import com.example.locationappmvvm.R
import com.example.locationappmvvm.db.entities.Location_item

class LocationItemAdapter(var item : List<Location_item>  , private val viewModelIs: LocationViewModelIs) : RecyclerView.Adapter<LocationItemAdapter.LocationViewHolder>() {

    inner class LocationViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val longitudeIs = view.findViewById<TextView>(R.id.longitudeTV)
        val latitudeIs  = view.findViewById<TextView>(R.id.latitudeTV)
        val addressIs  = view.findViewById<TextView>(R.id.addressTV)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.location_item ,parent ,false)
        return LocationViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val currentItem = item[position]
        holder.longitudeIs.text=  currentItem.longitudeIs
        holder.latitudeIs.text = currentItem.latitudeIs
        holder.addressIs.text  = currentItem.addressIs
    }

    override fun getItemCount(): Int {
        return item.size
    }
}