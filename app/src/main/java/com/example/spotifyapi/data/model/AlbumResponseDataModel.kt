package com.example.spotifyapi.data.model


data class AlbumResponseDataModel(
    val album_type: String,
    val artists: List<Artist>,
    val available_markets: List<String>,
    val copyrights: List<Copyright>,
    val external_ids: ExternalIds,
    val external_urls: ExternalUrlsX,
    val genres: List<Any>,
    val href: String,
    val id: String,
    val images: List<Image>,
    val label: String,
    val name: String,
    val popularity: Int,
    val release_date: String,
    val release_date_precision: String,
    val total_tracks: Int,
    val tracks: TracksX,
    val type: String,
    val uri: String
)

data class Copyright(
    val text: String,
    val type: String
)

data class TracksX(
    val href: String,
    val items: List<ItemX>,
    val limit: Int,
    val next: Any,
    val offset: Int,
    val previous: Any,
    val total: Int
)

data class ItemX(
    val artists: List<ArtistX>,
    val available_markets: List<String>,
    val disc_number: Int,
    val duration_ms: Int,
    val explicit: Boolean,
    val external_urls: ExternalUrlsXXX,
    val href: String,
    val id: String,
    val is_local: Boolean,
    val name: String,
    val preview_url: String,
    val track_number: Int,
    val type: String,
    val uri: String
)


