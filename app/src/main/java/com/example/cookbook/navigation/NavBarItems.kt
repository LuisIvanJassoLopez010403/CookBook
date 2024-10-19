package com.example.cookbook.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search

val NavBarItems = listOf(
    BarItem(
        title = "Home",
        image = Icons.Filled.Home,
        route = Routes.HomeView
    ),
    BarItem(
        title = "Search",
        image = Icons.Filled.Search,
        route = Routes.InitialFinderView
    ),
    BarItem(
        title = "Add",
        image = Icons.Filled.Add,
        route = Routes.LoginView
    ),
    BarItem(
        title = "User",
        image = Icons.Filled.Person,
        route = Routes.UserView
    ),
)