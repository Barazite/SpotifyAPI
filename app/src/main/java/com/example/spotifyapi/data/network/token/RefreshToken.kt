package com.example.spotifyapi.data.network.token

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class RefreshToken: Authenticator {

        lateinit var service: TokenService

        override fun authenticate(route: Route?, response: Response): Request {

            return response.request.newBuilder().header("Authorization", "Bearer").build()
        }


}