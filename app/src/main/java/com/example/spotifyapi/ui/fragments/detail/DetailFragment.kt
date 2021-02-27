package com.example.spotifyapi.ui.fragments.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.spotifyapi.R
import com.example.spotifyapi.databinding.DetailFragmentBinding
import com.google.android.material.tabs.TabLayoutMediator

class DetailFragment : Fragment() {

    lateinit var binding: DetailFragmentBinding
    private val args: DetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DetailFragmentBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    private fun setupView() {
        binding.vpDetailFragment.adapter = DetailViewPagerAdapter(this, args.item){
            findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToTrackFragment(it))
        }

        TabLayoutMediator(binding.tabDetailFragment, binding.vpDetailFragment) { tab, position ->
            tab.text = when(position) {
                0 -> getString(R.string.track_tab)
                1 -> getString(R.string.album_tab)
                2 -> getString(R.string.artists_tab)
                3 -> getString(R.string.web_tab)
                else -> ""
            }
        }.attach()
    }
}