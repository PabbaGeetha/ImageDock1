package com.geetha.imagedock.api

import com.geetha.imagedock.data.model.Images
import retrofit2.http.GET
import retrofit2.http.Query

/**
* Get call for Images Api
*/
interface GetImagesApi {

    companion object {
        const val BASE_URL = "https://pixabay.com/"
    }

    @GET("/api/")
    suspend fun getImages(
        @Query("key") key: String?,
        @Query("q") searchText: String?
    ): Images
}

