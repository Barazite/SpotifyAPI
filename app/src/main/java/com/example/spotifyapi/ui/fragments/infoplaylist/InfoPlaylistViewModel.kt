package com.example.spotifyapi.ui.fragments.infoplaylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spotifyapi.base.BaseState
import com.example.spotifyapi.data.SpotifyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InfoPlaylistViewModel: ViewModel(){

    private val state = MutableLiveData<BaseState>()
    fun getState(): LiveData<BaseState> = state

    fun requestInformation(idPlaylist: String){
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    state.postValue(BaseState.Loading())
                    val items = SpotifyRepository().getInfoPlaylist(idPlaylist)
                    state.postValue(BaseState.Normal(InfoPlaylistState(items)))
                } catch (e: Exception) {
                    state.postValue(BaseState.Error(e))
                }
            }
    }
}