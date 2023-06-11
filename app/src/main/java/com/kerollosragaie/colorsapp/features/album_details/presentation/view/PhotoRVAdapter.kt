package com.kerollosragaie.colorsapp.features.album_details.presentation.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kerollosragaie.colorsapp.core.Constants
import com.kerollosragaie.colorsapp.core.models.Photo
import com.kerollosragaie.colorsapp.databinding.ItemPhotoBinding
import com.kerollosragaie.colorsapp.features.image_viewer.presentation.view.ImageViewerActivity

class PhotoRVAdapter :
    ListAdapter<Photo, PhotoRVAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imageView: ImageView = binding.itemIvPhoto
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoRVAdapter.ViewHolder =
        ViewHolder(ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: PhotoRVAdapter.ViewHolder, position: Int) {
        val item = getItem(position)

        holder.imageView.setOnClickListener {
            navigateToImageViewer(holder.itemView.context,item.url)
        }

        Glide.with(holder.imageView)
            .load(item.url)
            .into(holder.imageView)
    }

    class DiffCallback : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean =
            oldItem == newItem
    }

    private fun navigateToImageViewer(context: Context, itemUrl:String){
        val intent = Intent(context, ImageViewerActivity::class.java)
        intent.putExtra(Constants.IMAGE_URL, itemUrl)
        ContextCompat.startActivity(context, intent, null)
    }
}