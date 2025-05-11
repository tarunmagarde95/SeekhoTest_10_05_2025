package com.example.seekhotest_10_05_2025.data.api

import com.example.seekhotest_10_05_2025.data.model.AnimeDetailResponse
import com.example.seekhotest_10_05_2025.data.model.TopAnimeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("anime")
    suspend fun getAnimeList(@Query("page") page: Int): TopAnimeResponse

    @GET("anime/{id}")
    suspend fun getAnimeDetails(@Path("id") animeId: String): Response<AnimeDetailResponse>

}