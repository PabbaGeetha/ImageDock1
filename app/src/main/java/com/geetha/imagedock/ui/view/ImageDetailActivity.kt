package com.geetha.imagedock.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.geetha.imagedock.R
import com.geetha.imagedock.data.model.Hits
import com.geetha.imagedock.databinding.ActivityImageDetailBinding
import com.geetha.imagedock.utils.AppConstants

class ImageDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_detail)

        setupUI()
    }

    private fun setupUI() {
        val imageItem = intent.extras?.get(AppConstants.IMAGE_ID_EXTRA) as Hits

        /**
         * Using Glide library to load image
         */
        Glide.with(this@ImageDetailActivity)
            .load(imageItem.largeImageURL)
            .centerCrop()
            .thumbnail(0.5f)
            .placeholder(R.drawable.ic_splash_image_24)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop().into(binding.imgViewDetail);

        binding.tvUserNameDetail.text = getString(R.string.user, imageItem.user)
        binding.tvTagsDetail.text = getString(R.string.tags, imageItem.tags)
        binding.tvLikesDetail.text = getString(R.string.likes, imageItem.likes)
        binding.tvDownloadsDetail.text = getString(R.string.downloads, imageItem.downloads)
        binding.tvCommentsDetail.text = getString(R.string.comments, imageItem.comments)
    }

}