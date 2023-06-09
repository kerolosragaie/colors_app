package com.kerollosragaie.colorsapp.features.album_details.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kerollosragaie.colorsapp.R
import dagger.hilt.android.AndroidEntryPoint

class AlbumDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_details)
    }
}