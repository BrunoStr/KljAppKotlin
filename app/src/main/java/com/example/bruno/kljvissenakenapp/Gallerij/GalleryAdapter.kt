package com.example.bruno.kljvissenakenapp.Gallerij

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.example.bruno.kljvissenakenapp.R
import kotlinx.android.synthetic.main.gallery_item.view.*

class GalleryAdapter(var galleryList:MutableList<Photo>, var context: Context):RecyclerView.Adapter<CustomViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.gallery_item, parent, false)

        return CustomViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return galleryList.count()
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.view.title.text = galleryList.get(position).title
        holder.view.img.scaleType = ImageView.ScaleType.CENTER_CROP
        holder.view.img.setImageResource(galleryList.get(position).img)
    }

}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){

}