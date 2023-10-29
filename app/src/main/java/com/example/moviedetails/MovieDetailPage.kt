package com.example.moviedetails

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2

import com.google.gson.Gson

  class MovieDetailPage : AppCompatActivity() {

    private lateinit var movieList: List<MovieData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieList = Gson().fromJson(movies, Array<MovieData>::class.java).toList().filterNotNull()

        setContent {
            MovieDetailPageUI(movieList)
        }
    }

    @OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
    @Composable
    fun MovieDetailPageUI(movieList: List<MovieData>) {
        var selectedTabIndex by remember { mutableStateOf(0) }
        val context = LocalContext.current
        val viewPager2Ref = remember { mutableStateOf<ViewPager2?>(null) }

        val density = context.resources.displayMetrics.density
        val myShadowSize = context.resources.getDimension(R.dimen.shadow_size) / density

        Column {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent),
                modifier = Modifier.shadow(myShadowSize.dp),
                title = { Text(text = "Movie Details") },

                )


            ScrollableTabRow(
                selectedTabIndex = selectedTabIndex,
                modifier = Modifier.fillMaxWidth()
            ) {
                movieList.forEachIndexed { index, movieData ->
                    Tab(
                        selected = index == selectedTabIndex,
                        onClick = {
                            selectedTabIndex = index
                            viewPager2Ref.value?.setCurrentItem(index, true)
                        },
                        text = {
                            val title = movieData.title ?: "Unknown"
                            Text(
                                text = title,
                                color = if (index == selectedTabIndex) Color(0xFFE97451) else Color(
                                    0xFFD5B895
                                ),
                                fontWeight = if (index == selectedTabIndex) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                    )
                }
            }

            AndroidView(
                factory = { ctx ->
                    ViewPager2(ctx).apply {
                        adapter = MoviesPagerAdapter(context as FragmentActivity, movieList)
                        registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                            override fun onPageSelected(position: Int) {
                                selectedTabIndex = position
                            }
                        })
                    }
                },
                modifier = Modifier.fillMaxSize()
            ) { viewPager2 ->
                viewPager2Ref.value = viewPager2
            }
        }
    }
}




@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    max: Int = 5,
    currentRating: Double,
    size: Dp = 24.dp
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        for (i in 1..max) {
            when {
                currentRating >= i -> {

                    FullStar(size)
                }
                currentRating > i - 0.5 && currentRating < i -> {

                    HalfFilledStar(size)
                }
                else -> {

                    EmptyStar(size)
                }
            }
        }
    }
}

@Composable
fun FullStar(size: Dp) {
    Image(
        painter = painterResource(id = R.drawable.fullstar),
        contentDescription = "Full Star",
        modifier = Modifier.size(size),
        contentScale = ContentScale.FillBounds
    )
}

@Composable
fun EmptyStar(size: Dp) {
    Image(
        painter = painterResource(id = R.drawable.emptystar),
        contentDescription = "Empty Star",
        modifier = Modifier.size(size),
        contentScale = ContentScale.FillBounds
    )
}

@Composable
fun HalfFilledStar(size: Dp) {
    Image(
        painter = painterResource(id = R.drawable.halfstar),
        contentDescription = "Half Star",
        modifier = Modifier.size(size),
        contentScale = ContentScale.FillBounds
    )
}

