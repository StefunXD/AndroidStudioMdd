package com.example.mdd.navigations

sealed class Screen(val route: String){
    object HomePage : Screen("homepage")
    //Blind Test
    object Home : Screen("blindtesthome")
    object Question : Screen("blindtestquestion")
    object StartScreenBlindTest : Screen("startscreenblindtest")
    //Users
    object LoginScreen : Screen("homepagelogin")
    object RegisterScreen : Screen("homepageregister")


}