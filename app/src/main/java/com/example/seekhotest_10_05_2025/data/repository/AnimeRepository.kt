package com.example.seekhotest_10_05_2025.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.seekhotest_10_05_2025.data.AnimePagingSource
import com.example.seekhotest_10_05_2025.data.api.ApiService
import com.example.seekhotest_10_05_2025.data.model.Anime
import com.example.seekhotest_10_05_2025.data.model.AnimeDetailResponse
import kotlinx.coroutines.CoroutineScope

class AnimeRepository(private val apiService: ApiService) {

    fun getAnimePagingData(scope: CoroutineScope): LiveData<PagingData<Anime>> {
        return Pager(
            config = PagingConfig(pageSize = 25),
            pagingSourceFactory = { AnimePagingSource(apiService) }
        ).flow
            .cachedIn(scope)
            .asLiveData()
    }

    suspend fun getAnimeDetail(id: String): AnimeDetailResponse? {
        val response = apiService.getAnimeDetails(id)
        return if (response.isSuccessful) response.body() else null
    }
}

