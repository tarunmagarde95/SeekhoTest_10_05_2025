package com.example.seekhotest_10_05_2025.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.seekhotest_10_05_2025.data.model.Anime
import com.example.seekhotest_10_05_2025.data.repository.AnimeRepository

class AnimeViewModel(private val repository: AnimeRepository) : ViewModel() {
    val animePagingLiveData: LiveData<PagingData<Anime>> =
        repository.getAnimePagingData(viewModelScope)
}
