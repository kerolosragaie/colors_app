package com.kerollosragaie.colorsapp.features.album_details.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kerollosragaie.colorsapp.core.models.Photo
import com.kerollosragaie.colorsapp.databinding.ItemPhotoBinding

class PhotoRVAdapter :
    ListAdapter<Photo, PhotoRVAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val photo: ImageView = binding.itemIvPhoto
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoRVAdapter.ViewHolder =
        ViewHolder(ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: PhotoRVAdapter.ViewHolder, position: Int) {
        val item = getItem(position)

        Glide.with(holder.photo)
            .load(item.url)
            .into(holder.photo)
    }

    class DiffCallback : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean =
            oldItem == newItem

    }
}