package com.example.mdd.navigations

sealed class Screen(val route: String){
    object Home : Screen("blindtesthome")
    object Question : Screen("blindtestquestion")
    object Result : Screen("blindtestresult")

}