package com.example.spotifyapi.ui.fragments.detail

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.spotifyapi.data.model.Item
import com.example.spotifyapi.ui.fragments.detail.album.AlbumFragment
import com.example.spotifyapi.ui.fragments.detail.artists.ArtistsFragment
import com.example.spotifyapi.ui.fragments.detail.track.TrackFragment
import com.example.spotifyapi.ui.fragments.detail.web.WebFragment

class DetailViewPagerAdapter (fragment: Fragment, val item: Item): FragmentStateAdapter(fragment){
    override fun getItemCount(): Int = 4


    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> TrackFragment(item.track.id)
            1 -> AlbumFragment(item.track.album.id)
            2 -> ArtistsFragment(item.track.artists.first().id)
            3 -> WebFragment(item.track.external_urls.spotify)
            else -> Fragment()
        }
    }
}