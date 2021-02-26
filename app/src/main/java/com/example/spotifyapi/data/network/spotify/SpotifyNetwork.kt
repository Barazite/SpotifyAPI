package com.example.spotifyapi.data.network.spotify

import androidx.viewbinding.BuildConfig
import com.example.spotifyapi.data.model.*
import com.example.spotifyapi.data.network.token.RefreshToken
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class SpotifyNetwork {

    lateinit var service: SpotifyService

    private fun loadRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spotify.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(createHttpClient())
            .build()

        service = retrofit.create(SpotifyService::class.java)
    }

    private fun createHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(90L, TimeUnit.SECONDS)
            .readTimeout(90L, TimeUnit.SECONDS)
            .writeTimeout(90L, TimeUnit.SECONDS)

        val loggerInterceptor = HttpLoggingInterceptor()
        loggerInterceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        builder.addInterceptor(loggerInterceptor)

        val accessToken = "BQAH86Xa3Zy0WJoO403FktiDC9v2-KU7idyvU-tuROglg6Fdz1HNxtju0G-TUc6bQZrbYhCeD-zEdoaaE9c"
        builder.addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $accessToken")
                .build()
            chain.proceed(request)
        }
        
        return builder.build()
    }


    suspend fun getPlayList(id: String): PlaylistResponseDataModel {
        loadRetrofit()
        return service.getPlaylist(id)
    }

    suspend fun getInfoPlayList(id: String): InfoPlaylistResponseDataModel {
        loadRetrofit()
        return service.getInfoPlaylist(id)
    }

    suspend fun getTrack(id: String): TrackResponseDataModel {
        loadRetrofit()
        return service.getTrack(id)
    }

    suspend fun getAlbum(id: String): AlbumResponseDataModel {
        loadRetrofit()
        return service.getAlbum(id)
    }

    suspend fun getArtist(id: String): ArtistsResponseDataModel {
        loadRetrofit()
        return service.getArtist(id)
    }

}