package com.example.androidexternshipproject.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.androidexternshipproject.navigation.PodcastAppRouter
import com.example.androidexternshipproject.navigation.Screen
import com.example.androidexternshipproject.screens.LoginScreen
import com.example.androidexternshipproject.screens.SignUpScreen
import com.example.androidexternshipproject.screens.TermsAndConditionsScreen

@Composable
fun PodcastApp() {

    Surface(
        modifier = Modifier.fillMaxSize(),
//        color = Color.White
    ) {
            Crossfade(targetState = PodcastAppRouter.currentScreen) {currentState->
                when(currentState.value){
                    is Screen.SignUpScreen->{
                        SignUpScreen()
                    }
                    is Screen.TermsAndConditionsScreen->{
                        TermsAndConditionsScreen()
                    }
                    is Screen.LoginScreen->{
                        LoginScreen()
                    }
                }
                
            }
    }
}