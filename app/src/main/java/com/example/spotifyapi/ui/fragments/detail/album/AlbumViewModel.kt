package com.example.spotifyapi.ui.fragments.detail.album

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spotifyapi.base.BaseState
import com.example.spotifyapi.data.SpotifyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AlbumViewModel : ViewModel() {

    private val state = MutableLiveData<BaseState>()
    fun getState(): LiveData<BaseState> = state

    fun requestInformation(id: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                state.postValue(BaseState.Loading())
                val item = SpotifyRepository().getAlbum(id)
                state.postValue(BaseState.Normal(AlbumState(item)))
            } catch (e: Exception) {
                state.postValue(BaseState.Error(e))
            }
        }
    }
}