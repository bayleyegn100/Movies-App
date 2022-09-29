package com.yedebkid.moviesapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.yedebkid.moviesapp.databinding.FragmentDetailsBinding
import com.yedebkid.moviesapp.model.domain.MoviesResultDomainData
import com.yedebkid.moviesapp.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment() {

    private val binding by lazy {
        FragmentDetailsBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        populateMovieDetails(moviesViewModel.moviesResultDomainData)
        return binding.root
    }

    private fun populateMovieDetails(movies: MoviesResultDomainData? = null){
        movies?.let {
            binding.title.text = it.title
            binding.releasedDate.text = it.date
            binding.popularity.text = it.popularity.toString()
            binding.MoviePoster.text= it.posterImg
        } ?: Toast.makeText(requireContext(), "Movie Details not Found.", Toast.LENGTH_LONG).show()
    }
}