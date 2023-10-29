package com.example.moviedetails

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

class MovieListActivity : AppCompatActivity() {

    companion object {
        const val FRAGMENT_CONTAINER_ID = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val frameLayout = FrameLayout(this).apply {
            id = FRAGMENT_CONTAINER_ID
        }

        setContentView(frameLayout)

        if (savedInstanceState == null) {
            addMovieDetailsFragment(FRAGMENT_CONTAINER_ID)
        }
    }

    private fun addMovieDetailsFragment(containerId: Int) {
        supportFragmentManager.beginTransaction()
            .replace(containerId, MovieDetailsThreebuttons())
            .commit()
    }
}
