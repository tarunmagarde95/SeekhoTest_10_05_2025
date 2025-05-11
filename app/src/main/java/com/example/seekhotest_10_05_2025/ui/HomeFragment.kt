package com.example.seekhotest_10_05_2025.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.seekhotest_10_05_2025.R
import com.example.seekhotest_10_05_2025.adapter.AnimeAdapter
import com.example.seekhotest_10_05_2025.data.RetrofitInstance
import com.example.seekhotest_10_05_2025.data.repository.AnimeRepository
import com.example.seekhotest_10_05_2025.viewModel.AnimeViewModel
import com.example.seekhotest_10_05_2025.viewModel.AnimeViewModelFactory

class HomeFragment : Fragment() {

    private lateinit var animeAdapter: AnimeAdapter
    private lateinit var viewModel: AnimeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = AnimeRepository(RetrofitInstance.api) // Make sure RetrofitInstance is available
        val factory = AnimeViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[AnimeViewModel::class.java]


        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewAnime)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        animeAdapter = AnimeAdapter { animeId ->
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(animeId.toString())
            findNavController().navigate(action)
        }

        recyclerView.adapter = animeAdapter


        animeAdapter.addLoadStateListener { loadState ->
            progressBar.visibility = if (loadState.source.refresh is LoadState.Loading) View.VISIBLE else View.GONE
        }

        viewModel.animePagingLiveData.observe(viewLifecycleOwner) { pagingData ->
            animeAdapter.submitData(lifecycle, pagingData)
        }
    }
}
