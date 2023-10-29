@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.moviedetails

import androidx.compose.runtime.*
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment


class MainActivity : AppCompatActivity() {
    val items = listOf(
        NavigationItem.Aboutme to AboutMeFragment(),
        NavigationItem.Moviebuttons to AboutMeFragment(),
        NavigationItem.Moviemaster to AboutMeFragment()
    )
    private var selectedItem = -1

    private val viewModel: AboutMeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            selectedItem = savedInstanceState.getInt("selectedFragmentIndex", -1)
        }
        setContent {
            CustomContent()
        }
    }

    @Composable
    fun CustomContent() {
        val selectedItemState = rememberSaveable { mutableStateOf(selectedItem) }
        val currentFragment: Fragment? by viewModel.currentFragment.observeAsState()


        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
        ) {
            TaskAppBar("Assignment 3")

            Box(
                modifier = Modifier
                    .fillMaxWidth().weight(1f),
                contentAlignment = Alignment.Center
            ) {
                AndroidView(
                    factory = { context ->
                        val fragmentView = FrameLayout(context).apply {
                            id = View.generateViewId()
                        }
                        fragmentView
                    },
                    update = { fragmentView ->
                        currentFragment?.let { fragment ->
                            if (supportFragmentManager.findFragmentById(fragmentView.id) != fragment) {
                                loadFragment(fragment, fragmentView)
                            }
                        }
                    }
                )


            }

            CustomButtonNavigation(selectedItemState) { selectedFragment ->
                selectedItem = items.indexOfFirst { it.second::class == selectedFragment::class }
                viewModel.selectFragment(selectedFragment)
            }
        }
    }

    private fun loadFragment(fragment: Fragment, id: View) {
        supportFragmentManager.beginTransaction()
            .replace(id.id, fragment).addToBackStack(null)
            .commit()
    }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("selectedFragmentIndex", selectedItem)
    }


    @Composable
    fun CustomButtonNavigation(
        selectedItemState: MutableState<Int>,
        onFragmentSelected: (Fragment) -> Unit
    ) {
        val items = listOf(
            NavigationItem.Aboutme to AboutMeFragment(),
            NavigationItem.Moviebuttons to AboutMeFragment(),
            NavigationItem.Moviemaster to AboutMeFragment()
        )

        val screenHeight = LocalConfiguration.current.screenHeightDp.dp
        val orientation = LocalConfiguration.current.orientation
        val buttonHeight = if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            LocalConfiguration.current.screenHeightDp.dp * 0.25f
        } else {
            LocalConfiguration.current.screenHeightDp.dp * 0.15f
        }
        val context = LocalContext.current
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            items.forEachIndexed { index, pair ->
                val (item, fragment) = pair
                Button(
                    onClick = {
                        if (item == NavigationItem.Moviebuttons) {

                            context.startActivity(Intent(context, MovieListActivity::class.java))
                        }
                       else if (
                           item==NavigationItem.Moviemaster
                       )
                        {
                            context.startActivity(Intent(context, MovieDetailPage::class.java))
                        }

                       else if (selectedItemState.value != index) {
                            viewModel.selectFragment(pair.second)
                            selectedItemState.value = index
                        } else {
                            viewModel.selectFragment(InitalFragment())
                            selectedItemState.value = -1
                        }
                    }
                    ,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedItemState.value == index) Color.Cyan else Color.Transparent,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .padding(1.dp)
                        .height(buttonHeight)
                        .clip(RoundedCornerShape(10.dp))
                        .shadow(elevation = 2.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        androidx.compose.material.Icon(item.icon, contentDescription = item.label)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(item.label)
                    }
                }
            }
        }
    }

    sealed class NavigationItem(val label: String, val icon: ImageVector) {

        object Aboutme : NavigationItem("About Me", Icons.Filled.Person)
        object Moviebuttons : NavigationItem("Movie Buttons", Icons.Filled.List)
        object Moviemaster:NavigationItem("Movie Master Details",Icons.Filled.Info)

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskAppBar(name:String ) {

    val context = LocalContext.current
    val density = context.resources.displayMetrics.density
    val myShadowSize = context.resources.getDimension(R.dimen.shadow_size) / density

    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent)
        ,
        modifier = Modifier.shadow(myShadowSize.dp),
        title = { Text(text = name) },

        )
}


