package com.example.yeremi_warriors.data.api

import com.example.yeremi_warriors.data.model.PhotoModel
import retrofit2.http.GET

interface PhotoApiService {
    @GET("list")
    suspend fun getPhotos(): List<PhotoModel>
}