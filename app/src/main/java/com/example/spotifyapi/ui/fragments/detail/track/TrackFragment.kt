package com.example.spotifyapi.ui.fragments.detail.track

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.spotifyapi.R

class TrackFragment(id: String) : Fragment() {

    private lateinit var viewModel: TrackViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.track_fragment, container, false)
    }


}