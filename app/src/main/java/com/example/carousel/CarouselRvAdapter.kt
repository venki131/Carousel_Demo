package com.example.carousel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class CarouselRvAdapter(private val list: MutableList<Int>) :
    RecyclerView.Adapter<CarouselViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return CarouselViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(list[position])
            .placeholder(R.drawable.building1)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .fitCenter()
            .dontAnimate()
            .into(holder.imageBuilding)
    }

}

class CarouselViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var imageBuilding: ImageView

    init {
        imageBuilding = view.findViewById(R.id.buildingImage)
    }
}
