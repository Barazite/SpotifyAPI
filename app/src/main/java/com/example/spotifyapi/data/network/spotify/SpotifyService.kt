package com.example.spotifyapi.data.network.spotify

import com.example.spotifyapi.data.model.*
import retrofit2.http.*


interface SpotifyService {

    @GET("playlists/{playlist_id}/tracks")
    suspend fun getPlaylist(@Path("playlist_id") id: String): PlaylistResponseDataModel

    @GET("playlists/{playlist_id}")
    suspend fun getInfoPlaylist(@Path("playlist_id") id: String): InfoPlaylistResponseDataModel

    @GET("tracks/{track_id}")
    suspend fun getTrack(@Path("track_id") id: String): TrackResponseDataModel

    @GET("albums/{album_id}")
    suspend fun getAlbum(@Path("album_id")id: String): AlbumResponseDataModel



}