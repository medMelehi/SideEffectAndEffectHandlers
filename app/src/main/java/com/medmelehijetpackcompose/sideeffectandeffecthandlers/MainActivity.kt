package com.medmelehijetpackcompose.sideeffectandeffecthandlers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.medmelehijetpackcompose.sideeffectandeffecthandlers.disposableeffect.DisposableEffectApp
import com.medmelehijetpackcompose.sideeffectandeffecthandlers.launchedeffect.AnimationApp
import com.medmelehijetpackcompose.sideeffectandeffecthandlers.launchedeffect.LaunchedEffectApp
import com.medmelehijetpackcompose.sideeffectandeffecthandlers.remembercoroutinescope.DrawerScreenWithScaffoldState
import com.medmelehijetpackcompose.sideeffectandeffecthandlers.rememberupdatedstate.RememberUpdatedStateDemo
import com.medmelehijetpackcompose.sideeffectandeffecthandlers.sideeffect.SideEffectDemo
import com.medmelehijetpackcompose.sideeffectandeffecthandlers.ui.theme.SideEffectAndEffectHandlers


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SideEffectAndEffectHandlers {
                SideEffectDemo()
                // LaunchedEffectApp()
                // AnimationApp()
                // DisposableEffectApp()
                // RememberUpdatedStateDemo()
                // DrawerScreenWithScaffoldState()
            }
        }
    }
}
