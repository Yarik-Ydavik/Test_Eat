package com.example.test_eat.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.test_eat.navigation.screens.CATEGORYA_SCREEN
import com.example.test_eat.navigation.screens.HOME_SCREEN
import com.example.test_eat.utils.Constants
import com.example.test_eat.viewmodels.BagViewModel
import com.example.test_eat.viewmodels.MainViewModel

// Маршрутизатор для приложения
@Composable
fun SetupNavController(
    navController: NavHostController,
    startDestination: String = Constants.NavigationItem.Home.route,
    viewModel: MainViewModel,
    bagViewModel: BagViewModel,
){

    var rout by remember { mutableStateOf("") }
    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        composable( route = Constants.NavigationItem.Home.route ){
            HOME_SCREEN( navcontroller = navController)
        }
        composable(
            route = Constants.NavigationItem.Home.route + "/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ){ navBackStackEntry ->
            val cardID = navBackStackEntry.arguments?.getString("id")?.toInt()?.plus(1)
            val card = viewModel.kitchens.value?.first{ it.id.toString() == cardID.toString() }
            if (card != null) {
                rout = route.toString()
                CATEGORYA_SCREEN(
                    card = card,
                    navController = navController,
                    viewModel = viewModel,
                    viewModel2 = bagViewModel
                )
            }
        }
        composable( route = Constants.NavigationItem.Search.route ){
            BackHandler(true) { navController.popBackStack(route = rout, inclusive = false) }
            SEARCH_SCREEN()
        }
        composable( route = Constants.NavigationItem.Basket.route ){
            BackHandler(true) { navController.popBackStack(route = rout, inclusive = false) }
            BASKET_SCREEN( viewModel2 = bagViewModel )
        }
        composable( route = Constants.NavigationItem.Profile.route ){
            BackHandler(true) { navController.popBackStack(route = rout, inclusive = false) }
            PROFILE_SCREEN()
        }
    }
}

