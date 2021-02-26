package com.example.spotifyapi.ui.fragments.detail.artists

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.spotifyapi.R
import com.example.spotifyapi.base.BaseExtraData
import com.example.spotifyapi.base.BaseState
import com.example.spotifyapi.databinding.ArtistsFragmentBinding

class ArtistsFragment(private val id: String) : Fragment() {

    lateinit var binding: ArtistsFragmentBinding
    val viewModel: ArtistsViewModel by viewModels()
    lateinit var mAdapter: ArtistsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ArtistsFragmentBinding.inflate(inflater, container, false)

        setupView()

        viewModel.getState().observe(viewLifecycleOwner, { state ->
            when(state){
                is BaseState.Error -> {
                    updateToErrorState(state.dataError)
                }
                is BaseState.Loading ->{
                    updateToLoadingState(state.dataLoading)
                }
                is BaseState.Normal ->{
                    updateToNormalState(state.data as ArtistsState)
                }
            }
        })

        viewModel.requestInformation(id)

        return binding.root
    }


    private fun setupView() {
        mAdapter = ArtistsAdapter(listOf())

        binding.rvArtistsFragment.apply{
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun updateToNormalState(artist: ArtistsState) {
        binding.pbArtistsFragment.visibility = View.GONE
        binding.tvArtistsFragmentArtist.text = artist.artist.name
        Glide.with(requireActivity())
            .load(artist.artist.images.first().url)
            .centerCrop()
            .placeholder(R.drawable.ic_placeholder)
            .into(binding.ivArtistsFragmentArtist)
        binding.tvArtistsFragmentPopularity.text = artist.artist.popularity.toString()
        mAdapter.updateList(artist.artist.genres)
    }

    private fun updateToLoadingState(dataLoading: BaseExtraData?) {
        binding.pbArtistsFragment.visibility = View.VISIBLE
    }

    private fun updateToErrorState(dataError: Throwable) {
        binding.pbArtistsFragment.visibility = View.GONE
    }
}