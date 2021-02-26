package com.example.spotifyapi.ui.fragments.detail.track

import com.example.spotifyapi.data.model.TrackResponseDataModel
import java.io.Serializable

data class TrackState(
    val track : TrackResponseDataModel
): Serializable
