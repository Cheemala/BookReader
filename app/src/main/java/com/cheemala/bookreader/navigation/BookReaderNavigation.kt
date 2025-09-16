package com.cheemala.bookreader.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cheemala.bookreader.screens.bookdetails.BookDetailScreen
import com.cheemala.bookreader.screens.bookdetails.BookDetailScreenViewModel
import com.cheemala.bookreader.screens.home.HomeScreen
import com.cheemala.bookreader.screens.login.SignUpOrLoginScreen
import com.cheemala.bookreader.screens.login.SignUpOrLoginScreenViewModel
import com.cheemala.bookreader.screens.search.SearchScreen
import com.cheemala.bookreader.screens.search.SearchScreenViewModel
import com.cheemala.bookreader.screens.splash.SplashScreen

@Composable
fun BookReaderNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = BookReaderScreens.SplashScreen.name) {

        composable(route = BookReaderScreens.SplashScreen.name){
            SplashScreen(navController = navController)
        }

        composable(route = BookReaderScreens.SignUpOrLoginScreen.name){
            val signUpOrLoginScreenViewModel = hiltViewModel<SignUpOrLoginScreenViewModel>()
            SignUpOrLoginScreen(navController = navController, signUpOrLoginScreenViewModel = signUpOrLoginScreenViewModel)
        }

        composable(route = BookReaderScreens.HomeScreen.name){
            HomeScreen(navController = navController)
        }

        composable(route = BookReaderScreens.SearchScreen.name){
            val searchScreenVM = hiltViewModel<SearchScreenViewModel>()
            SearchScreen(navController = navController,searchScreenVM)
        }

        val destinationPath = BookReaderScreens.BookDetailScreen.name
        composable(route = "$destinationPath/{bookId}",
            arguments = listOf(navArgument(name = "bookId"){type = NavType.StringType})){backStackEntry ->
            backStackEntry.arguments?.getString("bookId").let { navArgBookId ->
                val bookdetailScreenVM = hiltViewModel<BookDetailScreenViewModel>()
                BookDetailScreen(navController = navController, bookdetailScreenVM,bookId = navArgBookId.toString())
            }
        }

    }
}