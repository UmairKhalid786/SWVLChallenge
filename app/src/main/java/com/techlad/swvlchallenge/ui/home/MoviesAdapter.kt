package com.techlad.swvlchallenge.ui.home

import android.graphics.Color
import androidx.databinding.ViewDataBinding
import com.techlad.swvlchallenge.R
import com.techlad.swvlchallenge.data.responses.MoviesResponse.Data.Movie
import com.techlad.swvlchallenge.databinding.ItemMovieBinding
import com.techlad.swvlchallenge.ui.base.BaseAdapter
import java.util.*

class MoviesAdapter : BaseAdapter<Movie>() {
    val rnd = Random()
    override fun getLayoutIdForPosition(position: Int) = R.layout.item_movie

    override fun areTheseItemsSame(oldItem: Movie, newItem: Movie) = oldItem.id == newItem.id

    override fun onBind(binding: ViewDataBinding, item: Movie, adapterPosition: Int) {
        if (binding is ItemMovieBinding) {
            binding.propsLl.movie = item
            val color: Int = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
            binding.thumbIv.thumbIv.setBackgroundColor(color)
        }
    }
}