package com.example.spotifyapi.ui.fragments.infoplaylist

import com.example.spotifyapi.data.model.InfoPlaylistResponseDataModel
import java.io.Serializable

data class InfoPlaylistState(
    val info: InfoPlaylistResponseDataModel
) : Serializable
