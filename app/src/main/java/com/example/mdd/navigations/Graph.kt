package com.example.mdd.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.mdd.ui.management.StartScreen
import com.example.mdd.viewmodel.BlindTestViewModel


@Composable
fun Graph(navController: NavHostController,
               viewModel: BlindTestViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) { StartScreen(navController, viewModel) }
    }

}


