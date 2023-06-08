package com.example.test_eat.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.test_eat.navigation.screens.CATEGORYA_SCREEN
import com.example.test_eat.navigation.screens.HOME_SCREEN
import com.example.test_eat.utils.Constants
import com.example.test_eat.viewmodels.MainViewModel

// Маршрутизатор для приложения
@Composable
fun SetupNavController(
    navController: NavHostController,
    startDestination: String = Constants.NavigationItem.Home.route,
    viewModel: MainViewModel
){
    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        composable( route = Constants.NavigationItem.Home.route ){
            HOME_SCREEN( navcontroller = navController , viewModel = viewModel)
        }
        composable(
            route = Constants.NavigationItem.Home.route + "/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ){ navBackStackEntry ->
            val cardID = navBackStackEntry.arguments?.getString("id")?.toInt()?.plus(1)
            val card = viewModel.kitchens.value?.first{ it.id.toString() == cardID.toString() }
            if (card != null) {
                CATEGORYA_SCREEN(
                    card = card,
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
        composable( route = Constants.NavigationItem.Search.route ){ SEARCH_SCREEN() }
        composable( route = Constants.NavigationItem.Basket.route ){ BASKET_SCREEN() }
        composable( route = Constants.NavigationItem.Profile.route ){ PROFILE_SCREEN() }
    }
}

