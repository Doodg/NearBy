package com.enigma.nearby

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.enigma.nearby.base.loadImage
import com.enigma.nearby.data.cache.VenueItemEntity
import kotlinx.android.synthetic.main.place_item_layout.view.*

class PlacesAdapter : RecyclerView.Adapter<PlacesAdapter.ViewHolder>() {
    var places = mutableListOf<VenueItemEntity?>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacesAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.place_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return places.size
    }

    override fun onBindViewHolder(holder: PlacesAdapter.ViewHolder, position: Int) {
        if (!places.isNullOrEmpty()) {
            val place = places.get(position)
            holder.itemView.textViewPlaceName.text = place?.placeName
            holder.itemView.textViewPlaceAddress.text = place?.placeAddress
            place?.placeIcon?.let {
                holder.itemView.imageViewPlacePhoto.loadImage(
                    it

                )
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}