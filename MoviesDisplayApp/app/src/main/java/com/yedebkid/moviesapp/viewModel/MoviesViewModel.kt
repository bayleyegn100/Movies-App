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

    var moviesResultDomainData: MoviesResultDomainData? = null

    private val _popularMovies: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val popularMovies: LiveData<UIState> get() = _popularMovies

    private val _upcomingMovies: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val upcomingMovies: LiveData<UIState> get() = _upcomingMovies

    private val _nowPlayingMovies: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val nowPlayingMovies: LiveData<UIState> get() = _nowPlayingMovies

    init {
        getPopularMoviesOnly()
        getNowPlayingMoviesOnly()
        getUpcomingMoviesOnly()
    }

    private fun getPopularMoviesOnly() {
        viewModelScope.launch(ioDispatcher) {
            moviesRepo.getAllPopularMovies().collect() {
                _popularMovies.postValue(it)
            }
        }

    }
    private fun getNowPlayingMoviesOnly() {
        viewModelScope.launch(ioDispatcher) {
            moviesRepo.getAllNowPlayingMovies().collect {
                _nowPlayingMovies.postValue(it)
            }
        }

    }
    private fun getUpcomingMoviesOnly() {
        viewModelScope.launch(ioDispatcher) {
            moviesRepo.getAllUpcomingMovies().collect() {
                _upcomingMovies.postValue(it)
            }
        }

    }
}