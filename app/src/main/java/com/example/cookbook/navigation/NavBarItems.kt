package com.example.cookbook.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Casino
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Image

val NavBarItems = listOf(
    BarItem(
        title = "First Partial",
        image = Icons.Filled.Casino,
        route = Routes.LoginView
    ),
    BarItem(
        title = "Second Partial",
        image = Icons.Filled.CheckCircle,
        route = Routes.LoginView
    ),
    BarItem(
        title = "Third Partial",
        image = Icons.Filled.Image,
        route = Routes.LoginView
    ),
)