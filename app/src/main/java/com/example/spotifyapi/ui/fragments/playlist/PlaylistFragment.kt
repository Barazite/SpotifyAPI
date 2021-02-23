package com.example.spotifyapi.ui.fragments.playlist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.spotifyapi.R
import com.example.spotifyapi.databinding.FragmentPlaylistBinding

class PlaylistFragment : Fragment() {

    lateinit var binding: FragmentPlaylistBinding
    val viewModel: PlaylistViewModel by viewModels()
    lateinit var mAdapter: PlaylistAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPlaylistBinding.inflate(inflater, container, false)


        return binding.root
    }

}