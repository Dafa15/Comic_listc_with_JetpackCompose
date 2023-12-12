package com.example.comislist.navigation

sealed class Screen (val route: String){
    object Home : Screen("Home")
    object Favorite : Screen("Favorite")
    object Profile : Screen("Profile")
    object DetailComic : Screen("Home/{id}") {
        fun createRoute(id: Long) = "Home/$id"
    }
}