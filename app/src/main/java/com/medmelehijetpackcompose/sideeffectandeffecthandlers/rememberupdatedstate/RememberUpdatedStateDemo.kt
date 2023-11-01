package com.medmelehijetpackcompose.sideeffectandeffecthandlers.rememberupdatedstate

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
import kotlinx.coroutines.delay
import kotlin.random.Random

@Preview(showSystemUi = true)
@Composable
fun RememberUpdatedStateDemo() {

    var exampleCount: Int

    var state by remember {
        mutableStateOf(0)
    }

    exampleCount = Random.nextInt(0, 10000)
    println("shouldCancel : $exampleCount")

    state // For recomposition purpose


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        // Click on this button before 3 seconds to change onTimeout() while the LaunchedEffect is running
        Button(onClick = { state++ }) {
            Text(text = "Change onTimeout")
        }
    }

    RememberUpdatedStateDemo {
        println("shouldCancel : $exampleCount")
    }

}

/**
 *  We can use rememberUpdatedState here to capture the latest version of the callback
 *  that might change during the recomposition of the composable
 */
@Composable
fun RememberUpdatedStateDemo(
    onTimeout: () -> Unit
) {
    // val updatedOnTimeout by rememberUpdatedState(newValue = onTimeout)

    LaunchedEffect(key1 = true) {
        delay(3000)
        onTimeout() // Try updatedOnTimeout() to get the last version of onTimeout before 3 seconds
    }
}

