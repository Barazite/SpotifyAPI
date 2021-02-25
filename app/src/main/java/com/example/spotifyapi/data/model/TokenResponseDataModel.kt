package com.example.spotifyapi.data.model

data class TokenResponseDataModel(
    val access_token: String,
    val expires_in: Int,
    val scope: String,
    val token_type: String
)