package com.medmelehijetpackcompose.sideeffectandeffecthandlers.launchedeffect

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.flow.collect

@Preview(showSystemUi = true)
@Composable
fun LaunchedEffectApp(viewModel: LaunchedEffectViewModel = LaunchedEffectViewModel()) {

    var state by remember {
        mutableStateOf(true)
    }

    LaunchedEffectDemo(viewModel, state) {
        state = !state
    }
}

@Composable
private fun LaunchedEffectDemo(
    viewModel: LaunchedEffectViewModel,
    state: Boolean,
    action: () -> Unit
) {
    // LaunchedEffect will get executed only once because we passe a constant value to is as key (true)
    LaunchedEffect(key1 = true) {
        println("LaunchedEffect Execution")
        viewModel.sharedFlow.collect { event ->
            when (event) {
                is LaunchedEffectViewModel.ScreenEvents.ShowSnackbar -> {
                    println("ShowSnackbar")
                }

                is LaunchedEffectViewModel.ScreenEvents.Navigate -> {
                    println("Navigate")
                }
            }
        }
    }

    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        Button(onClick = { viewModel.navigate() }) {
            Text(text = "Navigate")
        }

        Button(onClick = { viewModel.showSnackBar() }) {
            Text(text = "Show SnackBar")
        }


        Button(onClick = action) { // This button is only for recomposition purpose
            Text(text = "Recomopose : $state")
        }

        println("End of composition") // Indicates a successful composition
    }
}