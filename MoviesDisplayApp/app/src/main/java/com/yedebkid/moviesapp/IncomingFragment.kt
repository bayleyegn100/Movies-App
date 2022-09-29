package com.yedebkid.moviesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.yedebkid.moviesapp.adapter.MoviesAdapter
import com.yedebkid.moviesapp.databinding.FragmentIncomingBinding
import com.yedebkid.moviesapp.model.domain.MoviesResultDomainData
import com.yedebkid.moviesapp.util.BaseFragment
import com.yedebkid.moviesapp.util.DetailsClickHandler
import com.yedebkid.moviesapp.util.UIState

class IncomingFragment : BaseFragment() {

    private val binding by lazy {
        FragmentIncomingBinding.inflate(layoutInflater)
    }

    private val moviesAdaptor by lazy {
        MoviesAdapter {
            when(it){
                is DetailsClickHandler.UpcomingDetailsClick -> {
                    moviesViewModel.moviesResultDomainData = it.upcomingMovies
                    findNavController().navigate(R.id.action_incomingFragment_to_detailsFragment)
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

        binding.incomingFragmentRv.apply {
            layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL, false
            )
            adapter = moviesAdaptor
        }

        moviesViewModel.upcomingMovies.observe(viewLifecycleOwner) {
            when (it) {
                is UIState.LOADING -> {
                    binding.incomingFragmentRv.visibility = View.GONE
                    binding.loadingSpinner.visibility = View.VISIBLE
                }
                is UIState.SUCCESS<*> -> {
                    binding.incomingFragmentRv.visibility = View.VISIBLE
                    binding.loadingSpinner.visibility = View.GONE

                    val newMovies = it.data as MoviesResultDomainData
                    moviesAdaptor.updateMovies(newMovies)
                }
                is UIState.ERROR -> {
                    binding.incomingFragmentRv.visibility = View.GONE
                    binding.loadingSpinner.visibility = View.GONE
                }
            }
        }
        return binding.root
    }
}