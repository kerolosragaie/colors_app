package com.kerollosragaie.colorsapp.features.image_viewer.presentation.view


import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.kerollosragaie.colorsapp.core.Constants
import com.kerollosragaie.colorsapp.databinding.ActivityImageViewerBinding


class ImageViewerActivity : AppCompatActivity(){
    private val binding by lazy { ActivityImageViewerBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI(){
        val imageUrl = loadImageUrl()
        Glide.with(binding.iv)
            .load(imageUrl)
            .into(binding.iv)

        binding.btnShareImage.setOnClickListener {
            shareImage()
        }
    }

    private fun loadImageUrl(): String =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getStringExtra(Constants.IMAGE_URL)!!
        } else {
            intent.getStringExtra(Constants.IMAGE_URL)!!
        }


    private fun shareImage(){
        val mBitmap = (binding.iv.drawable as BitmapDrawable).bitmap
        val path = MediaStore.Images.Media.insertImage(contentResolver,mBitmap,"Image",null)
        val uri = Uri.parse(path)

        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_STREAM,uri)
            type = "image/png"
        }
        startActivity(Intent.createChooser(shareIntent,"Share"))
    }

}