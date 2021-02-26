package com.example.spotifyapi.ui.fragments.detail.artists


import com.example.spotifyapi.data.model.ArtistsResponseDataModel
import java.io.Serializable

data class ArtistsState(
    val artist : ArtistsResponseDataModel
): Serializable
