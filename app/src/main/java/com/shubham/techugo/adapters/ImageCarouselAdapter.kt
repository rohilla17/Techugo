package com.shubham.techugo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shubham.techugo.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image_item.view.*

class ImageCarouselAdapter(val imageList: ArrayList<String>) :
    RecyclerView.Adapter<ImageCarouselAdapter.CarouselViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        return CarouselViewHolder(parent)
    }


    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        holder.bind(imageList[position])
    }

    override fun getItemCount(): Int = imageList.size

    class CarouselViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        constructor(parent: ViewGroup) : this(
            LayoutInflater.from(parent.context).inflate(
                R.layout.image_item,
                parent, false
            )
        )

        fun bind(image: String) {
            Picasso.get().load(image).into(itemView.carousel_image)
        }
    }

}