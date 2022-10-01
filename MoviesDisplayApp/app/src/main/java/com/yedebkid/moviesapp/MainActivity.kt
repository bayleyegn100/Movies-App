package com.yedebkid.moviesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.yedebkid.moviesapp.databinding.ActivityMainBinding
import com.yedebkid.moviesapp.view.IncomingFragment
import com.yedebkid.moviesapp.view.NowPlayingFragment
import com.yedebkid.moviesapp.view.PopularFragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        replaceFragment(PopularFragment())

        binding.bottomNavigationView.setOnItemReselectedListener {
            when(it.itemId){
                R.id.popularFragment -> replaceFragment(PopularFragment())
                R.id.incomingFragment -> replaceFragment(IncomingFragment())
                R.id.nowPlayingFragment -> replaceFragment(NowPlayingFragment())

                else -> {

                }
            }
        true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.movies_frag_container, fragment)
        fragmentManager.commit()
    }
}