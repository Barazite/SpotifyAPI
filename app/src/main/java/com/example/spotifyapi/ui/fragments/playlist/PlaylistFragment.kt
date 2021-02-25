package com.example.spotifyapi.ui.fragments.playlist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spotifyapi.R
import com.example.spotifyapi.base.BaseExtraData
import com.example.spotifyapi.base.BaseState
import com.example.spotifyapi.base.NoInternetConnectivity
import com.example.spotifyapi.databinding.FragmentPlaylistBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import retrofit2.HttpException
import java.net.UnknownHostException

class PlaylistFragment : Fragment() {

    lateinit var binding: FragmentPlaylistBinding
    val viewModel: PlaylistViewModel by viewModels()
    lateinit var mAdapter: PlaylistAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPlaylistBinding.inflate(inflater, container, false)

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
                    updateToNormalState(state.data as PlaylistState)
                }
            }
        })

        viewModel.requestInformation(getString(R.string.id_playlist))

        return binding.root
    }

    private fun setupView() {
        mAdapter = PlaylistAdapter(listOf(), requireActivity()){ item ->
            findNavController().navigate(PlaylistFragmentDirections.actionPlaylistFragmentToTrackDetailFragment(item))
        }

        binding.rvFragmentPlaylist.apply{
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun updateToNormalState(mList: PlaylistState) {
        binding.pbFragmentPlaylist.visibility = View.GONE
        mAdapter.updateList(mList.playlist)
    }

    private fun updateToErrorState(dataError: Throwable) {
        binding.pbFragmentPlaylist.visibility = View.GONE
        mAdapter.updateList(listOf())
        val msg = when(dataError) {
            is HttpException -> {
                dataError.code().toString()
            }
            is UnknownHostException, is NoInternetConnectivity -> {
                "Asegurese de tener conexion a Internet"
            }
            else -> {
                "Error"
            }
        }

            MaterialAlertDialogBuilder(requireActivity())
                .setTitle("Error")
                .setMessage(msg)
                .setPositiveButton("Retry"){dialog, which ->
                    viewModel.requestInformation(getString(R.string.id_playlist))
                }
                .show()
    }

    private fun updateToLoadingState(dataLoading: BaseExtraData?) {
        binding.pbFragmentPlaylist.visibility = View.VISIBLE
    }
}