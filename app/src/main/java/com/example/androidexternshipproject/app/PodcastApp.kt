package com.example.androidexternshipproject.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.androidexternshipproject.apiInteractions.Result
import com.example.androidexternshipproject.dbInteractions.RoomDbDao
import com.example.androidexternshipproject.navigation.PodcastAppRouter
import com.example.androidexternshipproject.navigation.Screen
import com.example.androidexternshipproject.screens.LoginScreen
import com.example.androidexternshipproject.screens.PodcastHolderScreen
import com.example.androidexternshipproject.screens.SignUpScreen
import com.example.androidexternshipproject.screens.TermsAndConditionsScreen

@Composable
fun PodcastApp(dao: RoomDbDao, results: List<Result>) {

    Surface(
        modifier = Modifier.fillMaxSize(),
//        color = Color.White
    ) {
            Crossfade(targetState = PodcastAppRouter.currentScreen) {currentState->
                when(currentState.value){
                    is Screen.SignUpScreen->{
                        SignUpScreen(dao)
                    }
                    is Screen.TermsAndConditionsScreen->{
                        TermsAndConditionsScreen()
                    }
                    is Screen.LoginScreen->{
                        LoginScreen(dao)
                    }
                    is Screen.PodcastHolderScreen->{
                        PodcastHolderScreen(results)
                    }
                }
                
            }
    }
}