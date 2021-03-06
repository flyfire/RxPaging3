package com.solarexsoft.rxpaging3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.solarexsoft.rxpaging3.databinding.MovieItemBinding
import java.text.SimpleDateFormat
import java.util.*

class MovieViewHolder(private val binding: MovieItemBinding, private val locale: Locale) : RecyclerView.ViewHolder(binding.root) {
    private val mDateFormatter = SimpleDateFormat("dd MMM yyyy", locale)

    fun bind(movie: Movies.Movie) {
        with(movie) {
            binding.title.text = originalTitle
            binding.date.text = releaseDate?.let { mDateFormatter.format(it) }
            binding.poster.load(poster?.small) {
                crossfade(true)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup, locale: Locale): MovieViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_item,  parent,false)

            val binding = MovieItemBinding.bind(view)

            return MovieViewHolder(
                binding,
                locale
            )
        }
    }
}
