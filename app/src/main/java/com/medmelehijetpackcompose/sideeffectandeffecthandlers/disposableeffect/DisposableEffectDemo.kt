package com.medmelehijetpackcompose.sideeffectandeffecthandlers.disposableeffect

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver

@Preview(showSystemUi = true)
@Composable
fun DisposableEffectApp() {

    var showComposable by remember {
        mutableStateOf(true)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {

        Button(onClick = { showComposable = !showComposable }) {
            Text(text = "Navigate")
        }

        /**
         *  Try this to let the DisposableEffectDemo composable leaves the composition
         *  So onDispose will get executed
         *  if (showComposable)
         */
        DisposableEffectDemo(showComposable)

    }
}

@Composable
private fun DisposableEffectDemo(showComposable: Boolean) {

    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(key1 = true) {
        println("DisposableEffect execution")
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_PAUSE) {
                println("On pause called")
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            println("onDispose execution")
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Text(
        modifier = Modifier
            .background(Color(0xFF9F81CE))
            .padding(vertical = 200.dp, horizontal = 10.dp),
        textAlign = TextAlign.Center,
        text = "DisposableEffect Composable \nshowComposable : $showComposable"
    )

    println("End of composition") // Indicates a successful composition
}