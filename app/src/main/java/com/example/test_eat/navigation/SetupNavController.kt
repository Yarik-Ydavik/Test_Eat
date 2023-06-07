package com.example.test_eat.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.test_eat.navigation.screens.HOME_SCREEN
import com.example.test_eat.utils.Constants

// Маршрутизатор для приложения
@Composable
fun SetupNavController(
    navController: NavHostController,
    startDestination: String = Constants.NavigationItem.Home.route
){
    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        composable( route = Constants.NavigationItem.Home.route ){
            HOME_SCREEN()
        }
        composable( route = Constants.NavigationItem.Search.route ){
            SEARCH_SCREEN()
        }
        composable( route = Constants.NavigationItem.Basket.route ){
            BASKET_SCREEN()
        }
        composable( route = Constants.NavigationItem.Profile.route ){
            PROFILE_SCREEN()
        }
    }
}

