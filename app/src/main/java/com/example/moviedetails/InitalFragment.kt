package com.example.moviedetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.ui.unit.sp

class InitalFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                MyString()
            }
        }
    }

    @Composable
    private fun MyString(modifier: Modifier = Modifier) {
        Column(modifier = modifier) {
            Text(text = "Click the Below Buttons to See Movie Details or Abour Me fragment",
                fontSize = 25.sp
            )
        }
    }

    companion object {
    }
}
