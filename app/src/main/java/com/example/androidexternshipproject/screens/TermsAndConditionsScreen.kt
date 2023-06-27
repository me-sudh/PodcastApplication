package com.example.androidexternshipproject.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.androidexternshipproject.Components.HeadingTextComponent
import com.example.androidexternshipproject.R
import com.example.androidexternshipproject.navigation.PodcastAppRouter
import com.example.androidexternshipproject.navigation.Screen
import com.example.androidexternshipproject.navigation.SystemBackButtonHandler

@Composable
fun TermsAndConditionsScreen(){
    Surface(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Black)
        .padding(16.dp),
        color = Color.Black

    ) {
        HeadingTextComponent(value = stringResource(id = R.string.terms_and_conditions_page))

    }

    SystemBackButtonHandler {
        PodcastAppRouter.navigateTo(Screen.SignUpScreen)
    }
}