package com.abhiram.thorak.ui.screens

sealed class Route(val route : String){
    object StartUpScreen : Route("startup_screen")
    object HomeScreen : Route("home_screen")
    object Customize : Route("customization")
}
