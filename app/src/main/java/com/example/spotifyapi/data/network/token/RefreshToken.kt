package com.example.spotifyapi.data.network.token

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class RefreshToken: Authenticator {


        lateinit var service: TokenService

        override fun authenticate(route: Route?, response: Response): Request {
            val token = refreshAccesToken()
            return response.request.newBuilder().header("Authorization", "Bearer $token").build()
        }

        private fun refreshAccesToken(): String {
            if (::service.isInitialized){
                val myService = service.refreshToken("client_credentials")
                return myService.access_token
            }
            return ""

        }
    }
