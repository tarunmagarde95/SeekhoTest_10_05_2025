package com.example.seekhotest_10_05_2025.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.seekhotest_10_05_2025.data.repository.AnimeRepository

class AnimeDetailViewModelFactory(private val repository: AnimeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AnimeDetailViewModel(repository) as T
    }
}
