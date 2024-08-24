package com.cheemala.bookreader.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cheemala.bookreader.screens.HomeScreen
import com.cheemala.bookreader.screens.SplashScreen

@Composable
fun BookReaderNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = BookReaderScreens.SplashScreen.name) {

        composable(route = BookReaderScreens.SplashScreen.name){
            SplashScreen(navController = navController)
        }

        composable(route = BookReaderScreens.HomeScreen.name){
            HomeScreen(navController = navController)
        }

    }
}