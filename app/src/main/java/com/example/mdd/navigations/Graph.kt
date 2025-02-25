package com.example.mdd.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.mdd.MainPage
import com.example.mdd.SampleSimilarMainPage
import com.example.mdd.ui.management.StartScreenBlindTest
import com.example.mdd.uimanager.LoginScreen
import com.example.mdd.viewmodel.BlindTestViewModel


@Composable
fun Graph(navController: NavHostController,
               viewModel: BlindTestViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.HomePage.route){ MainPage(SampleSimilarMainPage.SimilarMainPageSample) }
      composable(Screen.StartScreenBlindTest.route) {StartScreenBlindTest(navController, viewModel) }
        composable(Screen.LoginScreen.route) { LoginScreen(navController) }
    }


}


