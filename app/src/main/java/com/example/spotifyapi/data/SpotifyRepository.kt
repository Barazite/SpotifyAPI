package com.example.spotifyapi.data

import com.example.spotifyapi.data.model.Item
import com.example.spotifyapi.data.network.spotify.SpotifyNetwork

class SpotifyRepository {

    suspend fun getPlaylist(id: String): List<Item> {
        return SpotifyNetwork().getPlayList(id).items
    }
}