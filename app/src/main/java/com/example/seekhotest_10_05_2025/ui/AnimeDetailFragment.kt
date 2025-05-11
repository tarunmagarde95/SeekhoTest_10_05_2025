package com.example.seekhotest_10_05_2025.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.seekhotest_10_05_2025.R
import com.example.seekhotest_10_05_2025.data.RetrofitInstance
import com.example.seekhotest_10_05_2025.data.repository.AnimeRepository
import com.example.seekhotest_10_05_2025.viewModel.AnimeDetailViewModel
import com.example.seekhotest_10_05_2025.viewModel.AnimeDetailViewModelFactory

class AnimeDetailFragment : Fragment() {

    private lateinit var viewModel: AnimeDetailViewModel


    private lateinit var webView: WebView
    private lateinit var imagePoster: ImageView
    private lateinit var tvTitle: TextView
    private lateinit var tvSynopsis: TextView
    private lateinit var tvGenres: TextView
    private lateinit var tvCast: TextView
    private lateinit var tvEpisodes: TextView
    private lateinit var tvRating: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_anime_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        webView = view.findViewById(R.id.webViewTrailer)
        imagePoster = view.findViewById(R.id.imagePoster)
        tvTitle = view.findViewById(R.id.tvTitle)
        tvSynopsis = view.findViewById(R.id.tvSynopsis)
        tvGenres = view.findViewById(R.id.tvGenres)
        tvCast = view.findViewById(R.id.tvCast)
        tvEpisodes = view.findViewById(R.id.tvEpisodes)
        tvRating = view.findViewById(R.id.tvRating)


        val repository = AnimeRepository(RetrofitInstance.api)
        viewModel = ViewModelProvider(this, AnimeDetailViewModelFactory(repository))[AnimeDetailViewModel::class.java]

        val animeId = arguments?.let { AnimeDetailFragmentArgs.fromBundle(it).animeId }

        viewModel.fetchAnimeDetail(animeId.toString())


        viewModel.animeDetail.observe(this) { anime ->
            tvTitle.text = anime.title
            tvSynopsis.text = anime.synopsis
            tvGenres.text = "Genres: " + anime.genres.joinToString(", ") { it.name }
          //  tvCast.text = "Studio: " + anime.studios.joinToString(", ") { it.name }
            tvEpisodes.text = "Episodes: ${anime.episodes}"
            tvRating.text = "Rating: ${anime.score}"

            anime.trailer?.embed_url?.let {
                webView.visibility = View.VISIBLE
                imagePoster.visibility = View.GONE
                webView.settings.javaScriptEnabled = true
                webView.loadUrl(it)
            } ?: run {
                webView.visibility = View.GONE
                imagePoster.visibility = View.VISIBLE
                Glide.with(this).load(anime.images.jpg.image_url).into(imagePoster)
            }
        }


    }

}