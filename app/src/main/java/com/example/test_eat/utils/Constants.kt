package com.example.test_eat.utils

import com.example.test_eat.R


class Constants {

    // Страницы приложения
    sealed class NavigationItem (var route: String, var icon: Int, var title: String) {
        object Home : NavigationItem ("home", R.drawable.home_icon,"Главная")
        object Search : NavigationItem ("search", R.drawable.search_icon,"Поиск")
        object Basket : NavigationItem ("basket", R.drawable.bag_icon,"Корзина")
        object Profile : NavigationItem ("profile", R.drawable.profile_icon,"Аккаунт")
    }

}