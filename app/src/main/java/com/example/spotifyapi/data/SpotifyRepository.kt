package com.example.spotifyapi.data

import com.example.spotifyapi.data.model.*
import com.example.spotifyapi.data.network.spotify.SpotifyNetwork

class SpotifyRepository {

    suspend fun getPlaylist(id: String): List<Item> {
        return SpotifyNetwork().getPlayList(id).items
    }

    suspend fun getInfoPlaylist(id: String): InfoPlaylistResponseDataModel {
        return SpotifyNetwork().getInfoPlayList(id)
    }

    suspend fun getTrack(id: String): TrackResponseDataModel {
        return SpotifyNetwork().getTrack(id)
    }

    suspend fun getAlbum(id: String): AlbumResponseDataModel {
        return SpotifyNetwork().getAlbum(id)
    }

    suspend fun getArtist(id: String): ArtistsResponseDataModel {
        return SpotifyNetwork().getArtist(id)
    }
}