package com.example.androidexternshipproject.apiInteractions

data class ResponseDataClass (
    val took: Double,
    val count: Long,
    val total: Long,
    val results: List<Result>,
    val nextOffset: Long
)

data class Result (
    val id: String,
    val rss: String,
    val link: String,
    val audio: String,
    val image: String,
    val podcast: Podcast,
    val itunesID: Long,
    val thumbnail: String,
    val pubDateMS: Long,
    val guidFromRSS: String,
    val titleOriginal: String,
    val listennotesURL: String,
    val audioLengthSEC: Long,
    val explicitContent: Boolean,
    val titleHighlighted: String,
    val descriptionOriginal: String,
    val descriptionHighlighted: String,
    val transcriptsHighlighted: List<Any?>
)

data class Podcast (
    val id: String,
    val image: String,
    val genreIDS: List<Long>,
    val thumbnail: String,
    val listenScore: Long,
    val titleOriginal: String,
    val listennotesURL: String,
    val titleHighlighted: String,
    val publisherOriginal: String,
    val publisherHighlighted: String,
    val listenScoreGlobalRank: String
)