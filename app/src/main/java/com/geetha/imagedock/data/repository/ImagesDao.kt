package com.geetha.imagedock.data.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.geetha.imagedock.data.model.Hits
import kotlinx.coroutines.flow.Flow

@Dao
interface ImagesDao {

    @Query("SELECT * FROM images")
    fun getAllImages(): Flow<List<Hits>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImages(images: List<Hits>)

    @Query("DELETE FROM images")
    suspend fun deleteAllImages()
}