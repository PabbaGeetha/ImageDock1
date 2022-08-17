package com.geetha.imagedock.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.geetha.imagedock.R
import com.geetha.imagedock.data.model.Hits
import com.geetha.imagedock.databinding.ActivityImageSearchBinding
import com.geetha.imagedock.ui.ImageClickListener
import com.geetha.imagedock.ui.adapter.ImageListAdapter
import com.geetha.imagedock.ui.viewmodel.ImageSearchViewModel
import com.geetha.imagedock.utils.AppConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageSearchActivity : AppCompatActivity(), ImageClickListener {

    private lateinit var binding: ActivityImageSearchBinding
    private val adapter = ImageListAdapter(this@ImageSearchActivity, this@ImageSearchActivity)
    private val imageSearchViewModel: ImageSearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //initializes splash screen
        installSplashScreen()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_search)

        setupUI()
        initializeObserver()
        imageSearchView()
    }

    private fun setupUI() {
        binding.rvImagesList.layoutManager = GridLayoutManager(this@ImageSearchActivity, 2)
        binding.rvImagesList.adapter = adapter
    }

    /**
     * Observing view model data
     */
    private fun initializeObserver() {

        imageSearchViewModel.images.observe(this@ImageSearchActivity, Observer {
             adapter.updateUserList(it)
        })
    }

    /**
     * Search view query listener
     */
    private fun imageSearchView() {

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(searchString: String): Boolean {
                imageSearchViewModel.getImageList(searchString)
                binding.searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newString: String?): Boolean {
                return false
            }
        })
    }
    /**
     * Recycler view item click
     */
    override fun onClick(hit: Hits) {
        val intent = Intent(this@ImageSearchActivity, ImageDetailActivity::class.java)
        intent.putExtra(AppConstants.IMAGE_ID_EXTRA, hit)
        startActivity(intent)
    }
}
