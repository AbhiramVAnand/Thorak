
package com.abhiram.thorak


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abhiram.thorak.helpers.PrefernceRepository
import com.abhiram.thorak.ui.screens.Home
import com.abhiram.thorak.ui.screens.Route
import com.abhiram.thorak.ui.screens.tour.AddFavs
import com.abhiram.thorak.ui.screens.tour.Customize
import com.abhiram.thorak.ui.screens.tour.StartUp
import com.abhiram.thorak.ui.theme.ThorakTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val prefernceRepository = PrefernceRepository(this)
        val isFirst = prefernceRepository.isFirstRun()
        setContent {
            ThorakTheme {
                Navigation(isFirst = isFirst)
            }
        }
    }
}

@Composable
fun Navigation(isFirst : Boolean) {
    val navController = rememberNavController()
    if(isFirst){
        NavHost(navController = navController, startDestination = Route.StartUpScreen.route){
            composable(route = Route.StartUpScreen.route){
                StartUp(navController = navController)
            }
            composable(route=Route.Customize.route){
                Customize(navController =  navController)
            }
            composable(route = Route.AddFav.route){
                AddFavs(navController = navController)
            }
        }
    }else{
        NavHost(navController = navController, startDestination = Route.HomeScreen.route){
            composable(route = Route.HomeScreen.route){
                Home(navController = navController)
            }
        }
    }

}