package com.example.spotifyapi.ui.fragments.detail.track

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.spotifyapi.R
import com.example.spotifyapi.base.BaseExtraData
import com.example.spotifyapi.base.BaseState
import com.example.spotifyapi.databinding.TrackFragmentBinding

class TrackFragment(private val id: String) : Fragment() {

    lateinit var binding: TrackFragmentBinding
    val viewModel: TrackViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = TrackFragmentBinding.inflate(inflater, container, false)

        viewModel.getState().observe(viewLifecycleOwner, { state ->
            when(state){
                is BaseState.Error -> {
                    updateToErrorState(state.dataError)
                }
                is BaseState.Loading ->{
                    updateToLoadingState(state.dataLoading)
                }
                is BaseState.Normal ->{
                    updateToNormalState(state.data as TrackState)
                }
            }
        })

        viewModel.requestInformation(id)

        return binding.root
    }

    private fun updateToNormalState(data: TrackState) {
        binding.pbTrackFragment.visibility = View.GONE
        binding.tvTrackFragmentTitle.text = data.track.name
        binding.tvTrackFragmentPopularity.text = data.track.popularity.toString()
        val minutes = ((data.track.duration_ms/1000)/60).toString()
        val seconds = ((data.track.duration_ms/1000)%60).toString()
        binding.tvTrackFragmentDuration.text = getString(R.string.track_duration, minutes, seconds)

    }

    private fun updateToLoadingState(dataLoading: BaseExtraData?) {
        binding.pbTrackFragment.visibility = View.VISIBLE
    }

    private fun updateToErrorState(dataError: Throwable) {
        binding.pbTrackFragment.visibility = View.GONE
    }
}