package com.example.moviedetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MovieFragment : Fragment() {

    private lateinit var movieData: MovieData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieData = arguments?.getParcelable<MovieData>("movie_data")
            ?: throw IllegalArgumentException("MovieData argument required!")
    }

    @Composable
    fun MovieContent(movieData: MovieData) {
        val posterTable = mapOf(
            "Blue Beetle" to R.drawable.bluebettle,
            "Meg 2: The Trench" to R.drawable.meg2,
            "The Nun II" to R.drawable.nun2,
            "Barbie" to R.drawable.barbie,
            "Talk to Me" to R.drawable.talktome,
            "Retribution" to R.drawable.retribution
        )
        val imageResource = movieData.title?.let { posterTable[it] }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                if (imageResource != null) {
                    Image(
                        painter = painterResource(id = imageResource),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Column() {
                        val adjustedRating = (movieData.vote_average ?: 0.0) / 2

                        Spacer(modifier = Modifier.padding(top=10.dp))
                        RatingBar(
                            currentRating = adjustedRating,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )

                        Text(
                            text = movieData.title ?: "Unknown",
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color(0xFF006466)  // Dark teal color code
                        , modifier = Modifier.align(Alignment.CenterHorizontally)

                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Year: ${movieData.release_date?.split("-")?.get(0) ?: "Unknown"}",
                            fontWeight = FontWeight.W900,
                            color = Color.DarkGray
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Movie Description",
                            fontWeight = FontWeight.W900,
                            fontSize = 15.sp,
                            color = Color.DarkGray
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        movieData.overview?.let {
                            Text(
                                text = it,
                                fontSize = 20.sp
                            )
                        }
                    }
                }
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MovieContent(movieData)
            }
        }
    }


    companion object {
        fun newInstance(movieData: MovieData): MovieFragment {
            val args = Bundle()
            args.putParcelable("movie_data", movieData)
            val fragment = MovieFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
