package com.yedebkid.moviesapp.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yedebkid.moviesapp.viewModel.MoviesViewModel

open class BaseFragment : Fragment() {
//This open class base fragment is created to access the view modal and to bundle the object to the details fragment/ open means for extention
    protected val moviesViewModel by lazy {
        ViewModelProvider(requireActivity())[MoviesViewModel::class.java]
    }
}