package com.example.seekhotest_10_05_2025.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seekhotest_10_05_2025.data.model.AnimeDetail
import com.example.seekhotest_10_05_2025.data.repository.AnimeRepository
import kotlinx.coroutines.launch

class AnimeDetailViewModel(private val repository: AnimeRepository) : ViewModel() {

    private val _animeDetail = MutableLiveData<AnimeDetail>()
    val animeDetail: LiveData<AnimeDetail> = _animeDetail

    fun fetchAnimeDetail(id: String) {
        viewModelScope.launch {
            val response = repository.getAnimeDetail(id)
            response?.let {
                _animeDetail.value = it.data
            }
        }
    }
}
