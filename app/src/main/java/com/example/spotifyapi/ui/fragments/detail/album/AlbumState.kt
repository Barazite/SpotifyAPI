package com.example.spotifyapi.ui.fragments.detail.album

import com.example.spotifyapi.data.model.AlbumResponseDataModel
import java.io.Serializable

data class AlbumState(
    val album : AlbumResponseDataModel
): Serializable
