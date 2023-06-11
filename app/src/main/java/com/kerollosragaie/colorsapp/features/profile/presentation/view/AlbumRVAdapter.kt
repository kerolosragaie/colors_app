package com.kerollosragaie.colorsapp.features.profile.presentation.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kerollosragaie.colorsapp.core.Constants
import com.kerollosragaie.colorsapp.core.models.Album
import com.kerollosragaie.colorsapp.databinding.ItemAlbumBinding
import com.kerollosragaie.colorsapp.features.album_details.presentation.view.AlbumDetailsActivity

class AlbumRVAdapter : ListAdapter<Album, AlbumRVAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(private val binding: ItemAlbumBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val albumItem: LinearLayout = binding.itemAlbum
        fun bind(album: Album) {
            binding.apply {
                itemTvAlbumName.text = album.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.albumItem.setOnClickListener {
            navigateToAlbumDetailsActivity(holder.itemView.context, item)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Album>() {
        override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean =
            oldItem == newItem

    }

    private fun navigateToAlbumDetailsActivity(
        context: Context,
        singleAlbum: Album
    ) {
        val intent = Intent(context, AlbumDetailsActivity::class.java)
        intent.putExtra(Constants.ALBUM_DATA, singleAlbum)
        ContextCompat.startActivity(context, intent, null)
    }

}