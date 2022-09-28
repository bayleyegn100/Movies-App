package com.yedebkid.moviesapp.rest

import com.yedebkid.moviesapp.model.domain.mapToMoviesResultDomainData
import com.yedebkid.moviesapp.util.FailureResponseException
import com.yedebkid.moviesapp.util.NullResponseException
import com.yedebkid.moviesapp.util.UIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

interface MoviesRepo {
    fun getAllPopularMovies(): Flow<UIState>
    fun getAllNowPlayingMovies(): Flow<UIState>
    fun getAllUpcomingMovies(): Flow<UIState>
}

class MoviesRepoImplementation @Inject constructor(
    private val moviesApi: MoviesApi
): MoviesRepo {
    override fun getAllPopularMovies(): Flow<UIState> = flow {
        emit(UIState.LOADING)
        delay(2000)

        try {
            val response = moviesApi.getPopularMovies()
            if(response.isSuccessful){
                response.body()?.let{
                    emit(UIState.SUCCESS(it.mapToMoviesResultDomainData()))
                } ?: throw NullResponseException("Response is null.")
            } else {
                throw FailureResponseException(response.errorBody().toString())
            }
        } catch (e: Exception){
            emit(UIState.ERROR(e))
        }
    }

    override fun getAllNowPlayingMovies(): Flow<UIState> = flow {
        emit(UIState.LOADING)
        delay(2000)

        try {
            val response = moviesApi.getNowPlayingMovies()
            if(response.isSuccessful){
                response.body()?.let{
                    emit(UIState.SUCCESS(it.mapToMoviesResultDomainData()))
                } ?: throw NullResponseException("Response is null.")
            } else {
                throw FailureResponseException(response.errorBody().toString())
            }
        } catch (e: Exception){
            emit(UIState.ERROR(e))
        }
    }

    override fun getAllUpcomingMovies(): Flow<UIState> = flow {
        emit(UIState.LOADING)
        delay(2000)

        try {
            val response = moviesApi.getUpcomingMovies()
            if(response.isSuccessful){
                response.body()?.let{
                    emit(UIState.SUCCESS(it.mapToMoviesResultDomainData()))
                } ?: throw NullResponseException("Response is null.")
            } else {
                throw FailureResponseException(response.errorBody().toString())
            }
        } catch (e: Exception){
            emit(UIState.ERROR(e))
        }
    }

}
