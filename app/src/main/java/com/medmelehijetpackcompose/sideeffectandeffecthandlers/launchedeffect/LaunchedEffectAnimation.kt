package com.medmelehijetpackcompose.sideeffectandeffecthandlers.launchedeffect

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.abs

@Preview(showSystemUi = true)
@Composable
fun AnimationApp() {

    var counter by remember {
        mutableStateOf(10)
    }

    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        // Both of the following buttons will trigger a recomposition since counter will get changed

        // this one will cause the re-execution of the LaunchedEffect scope
        Button(onClick = { counter += 40 }) {
            Text(text = "Increase Counter")
        }

        // But this one will not since we passe abs(counter) to the LaunchedEffect as a key
        Button(onClick = { counter = -counter }) {
            Text(text = "Fix Counter")
        }

        LaunchedEffectAnimation(counter)

        println("End of composition") // Indicates a successful composition
    }
}


@Composable
fun LaunchedEffectAnimation(counter: Int) {

    val animatable = remember { Animatable(0F) }

    LaunchedEffect(key1 = abs(counter)) {
        println("LaunchedEffect Execution $counter")
        animatable.animateTo(counter.toFloat())
    }

    Row(
        Modifier
            .background(Color(0xFF9F81CE))
            .height(20.dp)
            .width(animatable.value.dp)
    ) {}

}