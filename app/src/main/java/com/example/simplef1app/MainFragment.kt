package com.example.simplef1app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.navigation.fragment.findNavController


class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(
                ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
            )

            setContent {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    ButtonWithElevation("Drivers", R.id.action_MainFragment_to_DriversFragment)
                    ButtonWithElevation("Circuits", R.id.action_MainFragment_to_CircuitsFragment)
                    ButtonWithElevation("Constructors", R.id.action_MainFragment_to_ConstructorsFragment)
                }
            }
        }
    }

    @Composable
    fun ButtonWithElevation(label: String, destinationURI: Int) {
        Button(
            onClick = {
                findNavController().navigate(destinationURI)
            },
            elevation =  ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp,
                pressedElevation = 15.dp,
                disabledElevation = 0.dp
            )
        ) {
            Text(text = label)
        }
    }
}