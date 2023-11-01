package com.medmelehijetpackcompose.sideeffectandeffecthandlers.sideeffect

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

@Preview(showSystemUi = true)
@Composable
fun SideEffectDemo() {

    var state by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    /**
     * Wrapping the side effect with SideEffect scope won't change the bivavior but will
     * only execute the side effect after every successful composition.
     */
    //SideEffect {
    Toast.makeText(context, "Welcome to my app", Toast.LENGTH_SHORT).show()
    println("side effect")
    //}


    AppBody(state) {
        state = !state
    }

}

@Composable
private fun AppBody(state: Boolean, action: () -> Unit) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        /**
         * After every time we click on this button, recomposition will happen and
         * the Toast will appear again an again which is considered a side effect
         */
        Button(onClick = action) {
            Text(text = "State : $state")
        }

        // This will make the composition unsuccessful and the SideEffect scope will not get executed
        // val number = 10 / 0

    }

    println("end of composition") // Indicates a successful composition
}