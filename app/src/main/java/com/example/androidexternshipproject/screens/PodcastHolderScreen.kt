package com.example.androidexternshipproject.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.androidexternshipproject.apiInteractions.Result
import com.example.androidexternshipproject.view.PodcastGrid
import com.example.androidexternshipproject.view.PodcastPlayer

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PodcastHolderScreen(results: List<Result>){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ){
        val navHostController = rememberNavController()
        NavHost(navController =navHostController,
            startDestination = "podcast_data"){
            composable("podcast_data"){
                PodcastGrid(navController = navHostController, results)
            }
            composable("grid_details/{item}",
                arguments = listOf(
                    navArgument("item"){
//                        type = NavType.StringType
                        type = NavType.IntType
                    }
                )
            ){
                    navBackStackEntry ->
                navBackStackEntry.arguments?.getInt("item")?.let{
                      PodcastPlayer(podcast = results[it])
                }
            }
        }
    }
}

