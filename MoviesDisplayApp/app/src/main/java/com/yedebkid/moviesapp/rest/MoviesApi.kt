package com.yedebkid.moviesapp.rest

import com.yedebkid.moviesapp.model.MoviesData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET(POPULAR)
    suspend fun getPopularMovies(
        @Query("api_key") apikey: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int = PAGE

    ): Response<MoviesData?>

    @GET(UPCOMING)
    suspend fun getUpcomingMovies(
        @Query("api_key") apikey: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int = PAGE

    ): Response<MoviesData?>

    @GET(NOW_PLAYING)
    suspend fun getNowPlayingMovies(
        @Query("api_key") apikey: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int = PAGE

    ): Response<MoviesData?>

    companion object{

        const val BASE_URL = "https://api.themoviedb.org/3/movie/"
        const val POSTER_PATH = "https://image.tmdb.org/t/p/w440_and_h660_face/"

        private const val POPULAR = "popular"
        private const val UPCOMING = "upcoming"
        private const val NOW_PLAYING = "now_playing"
        private const val API_KEY = "26a7d094a77ed76d84964e9bed095b84"
        private const val LANGUAGE = "en-US"
        private const val PAGE = 1
    }





//    now_palying
//    https://api.themoviedb.org/3/movie/now_playing?api_key=26a7d094a77ed76d84964e9bed095b84&language=en-US&page=1
//
//    Popular
//    https://api.themoviedb.org/3/movie/popular?api_key=26a7d094a77ed76d84964e9bed095b84&language=en-US&page=1

//    Upcoming
//    https://api.themoviedb.org/3/movie/upcoming?api_key=26a7d094a77ed76d84964e9bed095b84&language=en-US&page=1
}