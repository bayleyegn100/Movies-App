package com.yedebkid.moviesapp.model.domain

import android.os.Parcelable
import com.yedebkid.moviesapp.model.Result
import kotlinx.parcelize.Parcelize
import java.util.*


data class MoviesResultDomainData(
    val title: String,
    val posterImg: String,
    val date: String,
    val popularity: Double,
    val overview: String,

)

fun List<Result?>?.mapToMoviesResultDomainData(): List<MoviesResultDomainData>? =
    this?.map{
        MoviesResultDomainData(
            title = it?.originalTitle ?: "",
            posterImg = it?.posterPath ?: "",
            date = it?.releaseDate ?: "",
            popularity = it?.popularity ?: 0.0,
            overview = it?.overview ?: ""


        )
    }
