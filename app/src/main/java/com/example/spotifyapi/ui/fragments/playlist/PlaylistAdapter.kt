package com.example.spotifyapi.ui.fragments.playlist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.spotifyapi.R
import com.example.spotifyapi.data.model.Item
import com.example.spotifyapi.databinding.ItemPlaylistBinding

class PlaylistAdapter (private var myList: List<Item>, private val context: Context, private val listener: (item:Item) -> Unit) : RecyclerView.Adapter<PlaylistAdapter.ViewHolder>(){

    class ViewHolder(val binding: ItemPlaylistBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(ItemPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val spotifyTrack = myList[position]
        holder.binding.apply{
            tvItemPlaylistFragment.text = spotifyTrack.track.name

            Glide.with(context)
                .load(spotifyTrack.track.album.images.firstOrNull()?.url ?: "https://image.freepik.com/free-vector/page-found-error-404_23-2147508324.jpg")
                .centerCrop()
                .placeholder(R.drawable.ic_placeholder)
                .into(ivItemPlaylistFragment)
        }

        holder.itemView.setOnClickListener{
            listener.invoke(spotifyTrack)
        }
    }

    override fun getItemCount() = myList.size

    fun updateList(newList: List<Item>){
        myList = newList
        notifyDataSetChanged()
    }
}