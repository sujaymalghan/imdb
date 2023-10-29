package com.example.moviedetails

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


class MoviesPagerAdapter(
    private val context: FragmentActivity,
    private val movieList: List<MovieData>
) : FragmentStateAdapter(context) {

    override fun getItemCount(): Int = movieList.size

    override fun createFragment(position: Int): Fragment =
        MovieFragment.newInstance(movieList[position])
}
