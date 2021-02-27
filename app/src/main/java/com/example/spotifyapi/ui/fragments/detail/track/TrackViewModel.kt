package com.example.spotifyapi.ui.fragments.detail.track

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spotifyapi.base.BaseState
import com.example.spotifyapi.data.SpotifyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TrackViewModel : ViewModel() {

    private val state = MutableLiveData<BaseState>()
    fun getState(): LiveData<BaseState> = state

    fun requestInformation(id: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                state.postValue(BaseState.Loading())
                val item = SpotifyRepository().getTrack(id)
                state.postValue(BaseState.Normal(TrackState(item)))
            } catch (e: Exception) {
                state.postValue(BaseState.Error(e))
            }
        }
    }
}