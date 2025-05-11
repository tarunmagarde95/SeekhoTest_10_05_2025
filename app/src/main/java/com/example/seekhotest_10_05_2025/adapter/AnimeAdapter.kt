package com.example.seekhotest_10_05_2025.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.seekhotest_10_05_2025.R
import com.example.seekhotest_10_05_2025.data.model.Anime



class AnimeAdapter(
    private val onItemClick: (Int) -> Unit
) : PagingDataAdapter<Anime, AnimeAdapter.AnimeViewHolder>(DiffCallback) {

    inner class AnimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.animeTitle)
        val image: ImageView = itemView.findViewById(R.id.animeImage)
        val animeEpisodes: TextView = itemView.findViewById(R.id.animeEpisodes)
        val ratings: TextView = itemView.findViewById(R.id.ratings)

        fun bind(anime: Anime) {
            title.text = anime.title
            animeEpisodes.text = "Total Episodes : ${anime.episodes}"
            ratings.text = "Rating : ${anime.rating}"
            Glide.with(image.context).load(anime.images.jpg.image_url).into(image)

            itemView.setOnClickListener {
                onItemClick(anime.mal_id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_anime, parent, false)
        return AnimeViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val anime = getItem(position) ?: return
        holder.bind(anime)
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Anime>() {
            override fun areItemsTheSame(oldItem: Anime, newItem: Anime) =
                oldItem.mal_id == newItem.mal_id

            override fun areContentsTheSame(oldItem: Anime, newItem: Anime) =
                oldItem == newItem
        }
    }
}
