package com.kerollosragaie.colorsapp.features.album_details.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kerollosragaie.colorsapp.R
import com.kerollosragaie.colorsapp.core.models.Photo

class PhotosAdapter(
    private val photosList: List<Photo>
) : RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val photo: ImageView = view.findViewById(R.id.item_iv_photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false))

    override fun getItemCount(): Int = photosList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val singlePhoto: Photo = photosList[position]

        Glide.with(holder.photo)
            .load(singlePhoto.url)
            .into(holder.photo)
    }

}