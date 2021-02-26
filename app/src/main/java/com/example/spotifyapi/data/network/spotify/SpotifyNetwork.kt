package com.example.spotifyapi.data.network.spotify

import androidx.viewbinding.BuildConfig
import com.example.spotifyapi.data.model.InfoPlaylistResponseDataModel
import com.example.spotifyapi.data.model.PlaylistResponseDataModel
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
        // Create OkHttpClient
        val builder = OkHttpClient.Builder()
            .connectTimeout(90L, TimeUnit.SECONDS)
            .readTimeout(90L, TimeUnit.SECONDS)
            .writeTimeout(90L, TimeUnit.SECONDS)

        // Logger interceptor
        val loggerInterceptor = HttpLoggingInterceptor()
        loggerInterceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        builder.addInterceptor(loggerInterceptor)

       // App token
        val accessToken = "BQBNHPYMOfRq9DVAwLWCwQpWP3xEENIpdtUXaFqYx-mvgYQPQi6HvtBKT3l31I4U7vYURsOCIUPS68M4Fy0"
        builder.addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $accessToken")
                .build()
            chain.proceed(request)
        }

        /*builder.authenticator(RefreshToken())
            .connectTimeout(90L, TimeUnit.SECONDS)
            .followRedirects(false)
            .followSslRedirects(false)*/

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

}