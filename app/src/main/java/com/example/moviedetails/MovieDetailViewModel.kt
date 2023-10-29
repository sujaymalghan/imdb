package com.example.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MovieDetailViewModel : ViewModel() {
    private var _movie = MutableLiveData<ThreeMovieData>()
    val movie: LiveData<ThreeMovieData> get() = _movie

    fun setMovieData(movieData: ThreeMovieData) {
        _movie.value = movieData
    }
}
