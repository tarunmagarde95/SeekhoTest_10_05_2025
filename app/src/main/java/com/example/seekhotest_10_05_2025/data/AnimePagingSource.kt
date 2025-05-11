package com.example.seekhotest_10_05_2025.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.seekhotest_10_05_2025.data.api.ApiService
import com.example.seekhotest_10_05_2025.data.model.Anime

class AnimePagingSource(
    private val apiService: ApiService
) : PagingSource<Int, Anime>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Anime> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getAnimeList(currentPage)
            val data = response.data
            val nextKey = if (response.pagination.has_next_page) currentPage + 1 else null

            LoadResult.Page(
                data = data,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Anime>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }
}
