package com.example.spotifyapi.ui.fragments.detail.album

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spotifyapi.data.model.ItemX
import com.example.spotifyapi.databinding.ItemAlbumBinding

class AlbumAdapter (private var myList: List<ItemX>, private val myListener: (track: String) -> Unit) : RecyclerView.Adapter<AlbumAdapter.ViewHolder>(){

    class ViewHolder(val binding: ItemAlbumBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemAlbumBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: AlbumAdapter.ViewHolder, position: Int) {
        val track = myList[position]
        holder.binding.apply{
            tvItemAlbumNumber.text = track.track_number.toString()
            tvItemAlbumTitle.text = track.name
        }

         holder.itemView.setOnClickListener{
                myListener.invoke(track.id)
         }
    }

    override fun getItemCount(): Int = myList.size

    fun updateList(newList: List<ItemX>){
        myList = newList
        notifyDataSetChanged()
    }
}