package com.yedebkid.moviesapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.yedebkid.moviesapp.R
import com.yedebkid.moviesapp.adapter.MoviesAdapter
import com.yedebkid.moviesapp.databinding.FragmentPopularBinding
import com.yedebkid.moviesapp.model.domain.MoviesResultDomainData
import com.yedebkid.moviesapp.util.BaseFragment
import com.yedebkid.moviesapp.util.DetailsClickHandler
import com.yedebkid.moviesapp.util.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularFragment : BaseFragment() {
    private val binding by lazy {
        FragmentPopularBinding.inflate(layoutInflater)
    }

//    private val moviesViewModel by lazy {    // Use BaseFragement not to make code DRY
//        ViewModelProvider(requireActivity())[MoviesViewModel::class.java]
//    }

//    Click listener
    private val moviesAdaptor by lazy {
        MoviesAdapter { handler ->
            when(handler){
                is DetailsClickHandler.PopularDetailsClick -> {
                    moviesViewModel.moviesResultDomainData = handler.popularMovies
                    findNavController().navigate(R.id.action_popularFragment_to_detailsFragment)
                }
                else -> {

                }
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding.popularFragment.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = moviesAdaptor
        }

        moviesViewModel.popularMovies.observe(viewLifecycleOwner) { state ->        //popularMovies is the live data from movies viewmodel
            when (state) {
                is UIState.LOADING -> {
                    binding.popularFragment.visibility = View.GONE
                    binding.loadingSpinner.visibility = View.VISIBLE
                }
                is UIState.SUCCESS<*> -> {
                    binding.popularFragment.visibility = View.VISIBLE
                    binding.loadingSpinner.visibility = View.GONE

                    val newMovies = state.data as MoviesResultDomainData
                    moviesAdaptor.updateMovies(newMovies)
                }
                is UIState.ERROR -> {
                    binding.popularFragment.visibility = View.GONE
                    binding.loadingSpinner.visibility = View.GONE
                }
            }
        }
        return binding.root
    }
}