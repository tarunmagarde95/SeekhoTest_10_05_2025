package com.example.seekhotest_10_05_2025.data.model

data class AnimeDetailResponse(
    val data: AnimeDetail
)

data class AnimeDetail(
    val title: String,
    val synopsis: String?,
    val images: Images,
    val episodes: Int?,
    val score: Float?,
    val genres: List<Genre>,
    val trailer: Trailer?,
    val cast: List<Character>?
)

data class Genre(val name: String)

data class Trailer(val youtube_id: String?,
                   val embed_url :String)

data class Character(val name: String)

