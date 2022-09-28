package com.yedebkid.moviesapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yedebkid.moviesapp.model.domain.MoviesResultDomainData
import com.yedebkid.moviesapp.rest.MoviesRepo
import com.yedebkid.moviesapp.util.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesRepo: MoviesRepo,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _popularMovies: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val popularMovies: LiveData<UIState> get() = _popularMovies

    private val _upcomingMovies: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val upcomingMovies: LiveData<UIState> get() = _upcomingMovies

    private val _nowPlayingMovies: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val nowPlayingMovies: LiveData<UIState> get() = _nowPlayingMovies

    var moviesResultDomainData: MoviesResultDomainData? = null

    init {
        getPopularMoviesOnly()
        getNowPlayingMoviesOnly()
        getUpcomingMoviesOnly()
    }


    private fun getPopularMoviesOnly() {
        viewModelScope.launch(ioDispatcher) {
            //Making a network call
            moviesRepo.getAllPopularMovies().collect() {
                //post the live data
                _popularMovies.postValue(it)
            }
        }

    }

    private fun getNowPlayingMoviesOnly() {
        viewModelScope.launch(ioDispatcher) {
            //Making a network call
            moviesRepo.getAllNowPlayingMovies().collect {
                //post the live data
                _nowPlayingMovies.postValue(it)
            }
        }

    }

    private fun getUpcomingMoviesOnly() {
        viewModelScope.launch(ioDispatcher) {
            //Making a network call
            moviesRepo.getAllUpcomingMovies().collect() {
                //post the live data
                _upcomingMovies.postValue(it)
            }
        }

    }
}