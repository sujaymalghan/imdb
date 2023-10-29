package com.example.moviedetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.gson.Gson

class MovieDetailsThreebuttons : Fragment() {

    private lateinit var moviethreeList: List<ThreeMovieData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        moviethreeList = Gson().fromJson(movies, Array<ThreeMovieData>::class.java).toList().filterNotNull()

        return ComposeView(requireContext()).apply {
            setContent {
                val screenWidthDp = LocalConfiguration.current.screenWidthDp

                Box(
                    modifier = Modifier.fillMaxSize().background(lerp(Color.White, Color(0xFF7FFF00), 0.3f))
                ) {
                    if (screenWidthDp > 850) {  // Large Screen Layout (Tablet)
                        Row(
                            modifier = Modifier.fillMaxSize()
                        ) {

                            Column(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                moviethreeList.take(3).forEach { movie ->
                                    Button(
                                        onClick = {

                                            val transaction = requireActivity().supportFragmentManager.beginTransaction()
                                            transaction.replace(MovieDetailsThreebuttons.FRAGMENT_DETAIL_CONTAINER_ID, MovieDetailFragment.newInstance(movie))
                                            transaction.addToBackStack(null)
                                            transaction.commit()
                                        },
                                        modifier = Modifier
                                            .fillMaxWidth(0.8f)
                                            .padding(5.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color.Black,
                                            contentColor = Color(0xFF7FFF00)
                                        ),
                                        shape = RectangleShape
                                    ) {
                                        Text(text = movie.title ?: "")
                                    }
                                }
                            }


                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .weight(1.5f)
                            ) {
                                AndroidView(
                                    modifier = Modifier.fillMaxSize(),
                                    factory = { context ->
                                        val frameLayout = FrameLayout(context).apply {
                                            id =MovieDetailsThreebuttons.FRAGMENT_DETAIL_CONTAINER_ID  }
                                        frameLayout
                                    }
                                )
                            }
                        }
                    } else {
                        // Small Screen Layout (Phone)
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.align(Alignment.Center)
                        ) {
                            moviethreeList.take(3).forEach { movie ->
                                Button(
                                    onClick = {
                                        val transaction = requireActivity().supportFragmentManager.beginTransaction()
                                        transaction.replace(MovieListActivity.FRAGMENT_CONTAINER_ID, MovieDetailFragment.newInstance(movie))
                                        transaction.addToBackStack(null)
                                        transaction.commit()
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth(0.5f)
                                        .padding(5.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Black,
                                        contentColor = Color(0xFF7FFF00)
                                    ),
                                    shape = RectangleShape
                                ) {
                                    Text(text = movie.title ?: "")
                                }

                            }
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val FRAGMENT_DETAIL_CONTAINER_ID = 12345678
    }

}
