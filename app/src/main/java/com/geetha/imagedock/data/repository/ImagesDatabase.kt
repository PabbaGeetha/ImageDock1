package com.geetha.imagedock.data.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.geetha.imagedock.data.model.Hits

/**
 * Images Database storing data in the form of Hits
 */
@Database(entities = [Hits::class], version = 1)
abstract class ImagesDatabase : RoomDatabase() {

    abstract fun imagesDao(): ImagesDao
}