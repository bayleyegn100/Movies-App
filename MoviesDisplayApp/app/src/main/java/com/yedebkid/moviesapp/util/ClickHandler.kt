package com.yedebkid.moviesapp.util

import com.yedebkid.moviesapp.model.domain.MoviesResultDomainData

sealed class DetailsClickHandler {
    data class PopularDetailsClick(val popularMovies: MoviesResultDomainData) : DetailsClickHandler()
    data class UpcomingDetailsClick(val upcomingMovies: MoviesResultDomainData) : DetailsClickHandler()
    data class NowPlayingDetailsClick(val nowPlayingMovies: MoviesResultDomainData) : DetailsClickHandler()
}
