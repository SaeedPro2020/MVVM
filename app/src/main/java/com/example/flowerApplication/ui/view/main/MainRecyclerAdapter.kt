package com.example.flowerApplication.ui.view.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flowerApplication.R
import com.example.flowerApplication.model.data.data.DataClass

class MainRecyclerAdapter(
    private val context: Context,
    private val flowers: List<DataClass>,
    private val itemListener: FlowerItemListener
) :

    RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>() {
    override fun getItemCount() = flowers.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // TODO: we crate a layout view here
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.flower_grid_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val flower = flowers[position]
        with(holder) {
            nameText.let {
                it.text = flower.flowerName
                it.contentDescription = flower.description
            }
            ratingBar.rating = flower.popularity
                   Glide.with(context)
                       .load(flower.imageUrl)
                       .into(flowerImage)
            holder.itemView.setOnClickListener{
                itemListener.onFlowerItemClick(flower)
            }
        }
    }


    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val nameText = itemView.findViewById<TextView>(R.id.nameText)!!
        val flowerImage = itemView.findViewById<ImageView>(R.id.flowerImage)!!
        val ratingBar = itemView.findViewById<RatingBar>(R.id.ratingBar)!!
    }

    interface FlowerItemListener {
        fun onFlowerItemClick(flower: DataClass)
    }

}