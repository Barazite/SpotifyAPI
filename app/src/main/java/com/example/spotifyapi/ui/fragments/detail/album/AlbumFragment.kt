package com.example.spotifyapi.ui.fragments.detail.album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.spotifyapi.R
import com.example.spotifyapi.base.BaseExtraData
import com.example.spotifyapi.base.BaseState
import com.example.spotifyapi.databinding.AlbumFragmentBinding


class AlbumFragment(private val id: String, private val myListener: (track: String) -> Unit) : Fragment() {

    lateinit var binding: AlbumFragmentBinding
    private val viewModel: AlbumViewModel by viewModels()
    lateinit var mAdapter: AlbumAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = AlbumFragmentBinding.inflate(inflater, container, false)

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
                    updateToNormalState(state.data as AlbumState)
                }
            }
        })

        viewModel.requestInformation(id)

        return binding.root
    }

    private fun setupView() {
        mAdapter = AlbumAdapter(listOf(), myListener)
        binding.rvAlbumFragment.apply{
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun updateToNormalState(album: AlbumState) {
        binding.pbAlbumFragment.visibility = View.GONE
        binding.tvAlbumFragmentTitle.text = album.album.name
        Glide.with(requireActivity())
            .load(album.album.images.firstOrNull()?.url ?: "https://image.freepik.com/free-vector/page-found-error-404_23-2147508324.jpg")
            .centerCrop()
            .placeholder(R.drawable.ic_placeholder)
            .into(binding.ivAlbumFragment)
        binding.tvAlbumFragmentDate.text = getString(R.string.album_date, album.album.release_date)
        binding.tvAlbumFragmentProduction.text = getString(R.string.album_studio, album.album.label)
        mAdapter.updateList((album).album.tracks.items)
    }

    private fun updateToLoadingState(dataLoading: BaseExtraData?) {
        binding.pbAlbumFragment.visibility = View.VISIBLE
    }

    private fun updateToErrorState(dataError: Throwable) {
        binding.pbAlbumFragment.visibility = View.GONE
    }


}