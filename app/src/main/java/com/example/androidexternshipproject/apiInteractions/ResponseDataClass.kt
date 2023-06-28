package com.example.androidexternshipproject.apiInteractions

import com.google.gson.annotations.SerializedName

data class ResponseDataClass (
    val took: Double,
    val count: Long,
    val total: Long,
    val results: List<Result>,
    @SerializedName("next_offset")
    val nextOffset: Long
)

data class Result (
    val id: String,
    val rss: String,
    val link: String,
    val audio: String,
    val image: String,
    val podcast: Podcast,
    @SerializedName("itunes_id")
    val itunesID: Long,
    val thumbnail: String,
    @SerializedName("pub_date_ms")
    val pubDateMS: Long,
    @SerializedName("guid_from_rss")
    val guidFromRSS: String,
    @SerializedName("title_original")
    val titleOriginal: String,
    @SerializedName("listennotes_url")
    val listennotesURL: String,
    @SerializedName("audio_length_sec")
    val audioLengthSEC: Long,
    @SerializedName("explicit_content")
    val explicitContent: Boolean,
    @SerializedName("title_highlighted")
    val titleHighlighted: String,
    @SerializedName("description_original")
    val descriptionOriginal: String,
    @SerializedName("description_highlighted")
    val descriptionHighlighted: String,
    @SerializedName("transcripts_highlighted")
    val transcriptsHighlighted: List<Any?>
)

data class Podcast (
    val id: String,
    val image: String,
    @SerializedName("genre_ids")
    val genreIDS: List<Long>,
    val thumbnail: String,
    @SerializedName("listen_score")
    val listenScore: Long,
    @SerializedName("title_original")
    val titleOriginal: String,
    @SerializedName("listennotes_url")
    val listennotesURL: String,
    @SerializedName("title_highlighted")
    val titleHighlighted: String,
    @SerializedName("publisher_original")
    val publisherOriginal: String,
    @SerializedName("publisher_highlighted")
    val publisherHighlighted: String,
    @SerializedName("listen_score_global_rank")
    val listenScoreGlobalRank: String
)