package com.example.yeremi_warriors.data.api

import com.example.yeremi_warriors.data.model.CatFactModel
import retrofit2.http.GET

interface CatFactApiService {
    @GET("fact")
    suspend fun getCatFact(): CatFactModel
}