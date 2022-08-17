package com.geetha.imagedock.di

import android.app.Application
import androidx.room.Room
import com.geetha.imagedock.api.GetImagesApi
import com.geetha.imagedock.data.repository.ImagesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Hilt Dependency Injection
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(GetImagesApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideImagesApi(retrofit: Retrofit): GetImagesApi =
        retrofit.create(GetImagesApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application) : ImagesDatabase =
        Room.databaseBuilder(app, ImagesDatabase::class.java, "images_database")
            .build()
}