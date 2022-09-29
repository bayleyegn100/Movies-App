package com.yedebkid.moviesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yedebkid.moviesapp.adapter.MoviesAdapter
import com.yedebkid.moviesapp.databinding.FragmentNowPlayingBinding
import com.yedebkid.moviesapp.util.BaseFragment
import com.yedebkid.moviesapp.util.DetailsClickHandler
import com.yedebkid.moviesapp.util.UIState

class NowPlayingFragment : BaseFragment() {

    private val binding by lazy {
        FragmentNowPlayingBinding.inflate(layoutInflater)
    }

    private val moviesAdaptor by lazy {
        MoviesAdapter { handler ->
            when(handler){
                is DetailsClickHandler.NowPlayingDetailsClick -> {
                    moviesViewModel.moviesResultDomainData = handler.nowPlayingMovies
                    findNavController().navigate(R.id.action_nowPlayingFragment_to_detailsFragment)
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
        binding.nowPlayingFragmentRv.apply {
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.VERTICAL, false
            )
            adapter = moviesAdaptor
        }

        moviesViewModel.nowPlayingMovies.observe(viewLifecycleOwner) {
            when(it) {
                is UIState.LOADING -> {
                    binding.nowPlayingFragmentRv.visibility = View.GONE
                    binding.loadingSpinner.visibility = View.VISIBLE
                }
                is UIState.SUCCESS<*> -> {
                    binding.nowPlayingFragmentRv.visibility = View.VISIBLE
                    binding.loadingSpinner.visibility = View.GONE
                }
                is UIState.ERROR -> {
                    binding.nowPlayingFragmentRv.visibility = View.GONE
                    binding.loadingSpinner.visibility = View.GONE
                }
            }
        }

        return binding.root
    }
}