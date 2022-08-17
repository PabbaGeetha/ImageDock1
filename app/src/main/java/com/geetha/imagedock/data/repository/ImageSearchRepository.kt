package com.geetha.imagedock.data.repository

import androidx.room.withTransaction
import com.geetha.imagedock.api.GetImagesApi
import com.geetha.imagedock.utils.AppConstants
import com.geetha.imagedock.utils.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class ImageSearchRepository @Inject constructor(
    private val api: GetImagesApi,
    private val db: ImagesDatabase
){

    private val imagesDao = db.imagesDao()

    /**
     * Get images data from Dao or from the api call
     */
    fun getImages(searchString: String) = networkBoundResource(
        query = {
            imagesDao.getAllImages()
        },
        fetch = {
            delay(2000)
            api.getImages(AppConstants.API_KEY, searchString)
        },
        saveFetchResult = { images ->
            db.withTransaction {
                imagesDao.deleteAllImages()
                imagesDao.insertImages(images.hits)
            }
        }
    )
}