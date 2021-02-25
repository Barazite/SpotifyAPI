package com.example.spotifyapi.data.network.token

import com.example.spotifyapi.data.model.TokenResponseDataModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface TokenService {

    /*@FormUrlEncoded
    @POST("api/token")
    suspend fun getAuthToken(@Field("grant_type") grantType: String): TokenResponseDataModel*/

    @FormUrlEncoded
    @POST("/api/token")
    fun refreshToken( @Field("grant_type") granType: String): TokenResponseDataModel

}