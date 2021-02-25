package com.example.spotifyapi.ui.fragments.infoplaylist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.spotifyapi.R
import com.example.spotifyapi.base.BaseExtraData
import com.example.spotifyapi.base.BaseState
import com.example.spotifyapi.databinding.FragmentInfoPlaylistBinding


class InfoPlaylistFragment : Fragment() {

    lateinit var binding: FragmentInfoPlaylistBinding
    val viewModel: InfoPlaylistViewModel by viewModels()
    val args: InfoPlaylistFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentInfoPlaylistBinding.inflate(inflater, container, false)

        viewModel.getState().observe(viewLifecycleOwner, { state ->
            when(state){
                is BaseState.Error -> {
                    updateToErrorState(state.dataError)
                }
                is BaseState.Loading ->{
                    updateToLoadingState(state.dataLoading)
                }
                is BaseState.Normal ->{
                    updateToNormalState(state.data as InfoPlaylistState)
                }
            }
        })

        viewModel.requestInformation(args.playlistId)

        return binding.root
    }

    private fun updateToNormalState(data: InfoPlaylistState) {
        binding.tvInfoPlaylistFragmentTitle.text = data.info.name

        Glide.with(requireActivity())
            .load(data.info.images.firstOrNull()?.url ?: (R.drawable.ic_placeholder))
            .centerCrop()
            .placeholder(R.drawable.ic_placeholder)
            .into(binding.ivInfoPlaylistFragment)

        binding.tvInfoPlaylistFragmentUser.text = getString(R.string.info_user_text, data.info.owner.display_name)
        binding.tvInfoPlaylistFragmentFollowers.text = getString(R.string.info_followers_text, data.info.followers.total.toString())
        binding.tvInfoPlaylistFragmentDescription.text = getString(R.string.info_description_text,data.info.description)

    }

    private fun updateToLoadingState(dataLoading: BaseExtraData?) {

    }

    private fun updateToErrorState(dataError: Throwable) {
    }


}