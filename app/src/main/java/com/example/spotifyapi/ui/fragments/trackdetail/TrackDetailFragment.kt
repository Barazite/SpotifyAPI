package com.example.spotifyapi.ui.fragments.trackdetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.spotifyapi.R

class TrackDetailFragment : Fragment() {

    companion object {
        fun newInstance() = TrackDetailFragment()
    }

    private lateinit var viewModel: TrackDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.track_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TrackDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}