package com.example.seekhotest_10_05_2025.data.model

data class TopAnimeResponse(
    val pagination: Pagination,
    val data: List<Anime>
)

data class Pagination(
    val last_visible_page: Int,
    val has_next_page: Boolean,
    val current_page: Int
)

data class Anime(
    val mal_id: Int,
    val title: String,
    val images: Images,
    val episodes: Int,
    val rating: String
)

data class Images(
    val jpg: Jpg
)

data class Jpg(
    val image_url: String
)


