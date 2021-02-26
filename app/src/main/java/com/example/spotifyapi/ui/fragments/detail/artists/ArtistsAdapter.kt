package com.example.spotifyapi.ui.fragments.detail.artists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spotifyapi.databinding.ItemArtistsGenreBinding

class ArtistsAdapter (private var myList: List<String>) : RecyclerView.Adapter<ArtistsAdapter.ViewHolder>(){

    class ViewHolder(val binding: ItemArtistsGenreBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemArtistsGenreBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: ArtistsAdapter.ViewHolder, position: Int) {
        val genre = myList[position]

        holder.binding.tvItemArtistsGenre.text = genre
    }

    override fun getItemCount(): Int = myList.size

    fun updateList(newList: List<String>){
        myList = newList
        notifyDataSetChanged()
    }
}