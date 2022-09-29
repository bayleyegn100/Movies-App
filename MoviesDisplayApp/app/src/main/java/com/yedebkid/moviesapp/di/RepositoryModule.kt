package com.yedebkid.moviesapp.di

import com.yedebkid.moviesapp.rest.MoviesRepo
import com.yedebkid.moviesapp.rest.MoviesRepoImplementation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesRepository(
        moviesRepoImplementation: MoviesRepoImplementation
    ): MoviesRepo
}