package com.example.spotifyapi.ui.fragments.playlist

import android.app.Application
import androidx.lifecycle.*
import com.example.spotifyapi.base.BaseState
import com.example.spotifyapi.base.NetworkManager
import com.example.spotifyapi.base.NoInternetConnectivity
import com.example.spotifyapi.data.SpotifyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlaylistViewModel (app: Application): AndroidViewModel(app) {

    private val state = MutableLiveData<BaseState>()
    fun getState(): LiveData<BaseState> = state

    fun requestInformation(idPlaylist: String){
        val hasInternetConnection: Boolean = NetworkManager().isNetworkAvailable(getApplication())
        if(hasInternetConnection) {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    state.postValue(BaseState.Loading())
                    val items = SpotifyRepository().getPlaylist(idPlaylist)
                    state.postValue(BaseState.Normal(PlaylistState(items)))
                } catch (e: Exception) {
                    state.postValue(BaseState.Error(e))
                }
            }
        }else{
            state.postValue(BaseState.Error(NoInternetConnectivity()))
        }
    }

}