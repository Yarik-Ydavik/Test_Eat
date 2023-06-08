package com.example.test_eat.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.test_eat.utils.Constants

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        Constants.NavigationItem.Home,
        Constants.NavigationItem.Search,
        Constants.NavigationItem.Basket,
        Constants.NavigationItem.Profile,
    )
    BottomNavigation(
        backgroundColor = Color.White,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach{ item ->
            BottomNavigationItem(
                selected = currentRoute == item.route || currentRoute?.startsWith(item.route) == true,
                onClick = {
                    navController.navigate(item.route){
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(painter = painterResource(id = item.icon), contentDescription = item.route) },
                label = { Text(text = item.title) },
                alwaysShowLabel = true,
                selectedContentColor = Color(red = 51, green = 100, blue = 224),
                unselectedContentColor = Color(red = 165, green = 169, blue = 178)
            )
        }
    }
}