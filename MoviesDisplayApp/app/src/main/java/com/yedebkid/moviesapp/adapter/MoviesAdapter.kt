package com.yedebkid.moviesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yedebkid.moviesapp.R
import com.yedebkid.moviesapp.databinding.MoviesItemBinding
import com.yedebkid.moviesapp.model.domain.MoviesResultDomainData
import com.yedebkid.moviesapp.util.DetailsClickHandler

class MoviesAdapter(
    private val dataSet: MutableList<MoviesResultDomainData> = mutableListOf(),
    private val detailsClickHandler: (DetailsClickHandler) -> Unit
   ) : RecyclerView.Adapter<MoviesViewHolder>() {

    fun updateMovies(newMovies: MoviesResultDomainData){
        dataSet.clear()
        dataSet.add(newMovies)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder =
        MoviesViewHolder(
            MoviesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) =
        holder.bind(dataSet[position], detailsClickHandler)

    override fun getItemCount(): Int = dataSet.size
}


class MoviesViewHolder(
    private val binding: MoviesItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(movies: MoviesResultDomainData, onClickHandler: (DetailsClickHandler) -> Unit) {
       binding.title.text = movies.title
       binding.date.text = movies.date
       binding.popularity.text = movies.popularity.toString()

        binding.detailsbtn.setOnClickListener{
            onClickHandler(DetailsClickHandler.PopularDetailsClick(movies))
        }

        Picasso.get()
            .load(movies.posterImg)
            .placeholder(R.drawable.ic_baseline_image_search_24)
            .error(R.drawable.ic_baseline_broken_image_24)
            .into(binding.posterImg)

    }

}
