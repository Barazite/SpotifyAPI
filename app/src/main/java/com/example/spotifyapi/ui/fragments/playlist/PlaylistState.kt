package com.example.spotifyapi.ui.fragments.playlist

import com.example.spotifyapi.data.model.Item
import java.io.Serializable

data class PlaylistState (
    val playlist: List<Item> = listOf()
): Serializable