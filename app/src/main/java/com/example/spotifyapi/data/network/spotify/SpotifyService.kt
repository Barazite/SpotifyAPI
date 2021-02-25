package com.example.spotifyapi.data.network.spotify

import com.example.spotifyapi.data.model.PlaylistResponseDataModel
import com.example.spotifyapi.data.model.TokenResponseDataModel
import retrofit2.http.*


interface SpotifyService {

    @GET("playlists/{playlist_id}/tracks")
    suspend fun getPlaylist(@Path("playlist_id") id: String): PlaylistResponseDataModel

}