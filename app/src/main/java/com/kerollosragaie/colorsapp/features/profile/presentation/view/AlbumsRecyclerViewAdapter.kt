package com.kerollosragaie.colorsapp.features.profile.presentation.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.kerollosragaie.colorsapp.R
import com.kerollosragaie.colorsapp.core.Constants.ALBUM_ID
import com.kerollosragaie.colorsapp.core.models.Album
import com.kerollosragaie.colorsapp.features.album_details.presentation.view.AlbumDetailsActivity

class AlbumsRecyclerViewAdapter(
    private val albumsList: List<Album>
) :
    RecyclerView.Adapter<AlbumsRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val albumItem: LinearLayout = view.findViewById(R.id.item_album)
        val albumTitle: TextView = view.findViewById(R.id.item_tv_album_name)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlbumsRecyclerViewAdapter.ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val singleAlbum: Album = albumsList[position]
        holder.albumTitle.text = singleAlbum.title
        holder.albumItem.setOnClickListener {
            val intent = Intent(holder.itemView.context, AlbumDetailsActivity::class.java)
            intent.putExtra(ALBUM_ID, singleAlbum.id)
            startActivity(holder.itemView.context, intent, null)
        }
    }

    override fun getItemCount(): Int {
        return albumsList.size
    }
}