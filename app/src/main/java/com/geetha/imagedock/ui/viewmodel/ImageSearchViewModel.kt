package com.geetha.imagedock.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geetha.imagedock.data.model.Hits
import com.geetha.imagedock.data.repository.ImageSearchRepository
import com.geetha.imagedock.utils.AppConstants
import com.geetha.imagedock.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageSearchViewModel @Inject constructor(
    private val repository: ImageSearchRepository
) :
    ViewModel() {

    var images = MutableLiveData<ArrayList<Hits>>()

    /**
     * Initial api call with word fruits
     */
    init {
        imagelist(AppConstants.INITIAL_SEARCH)
    }

    /**
     * Getting search view text
     */
    fun getImageList(searchString: String) = imagelist(searchString)

    /**
     * Coroutine with viewModelScope to get images data
     */
    fun imagelist(searchString: String) = viewModelScope.launch {
        val returnedList = repository.getImages(searchString)
        images.postValue(returnedList.first { it is Resource.Success }.data as ArrayList<Hits>?)
    }
}