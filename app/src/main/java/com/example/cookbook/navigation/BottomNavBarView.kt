package com.example.cookbook.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavBarView(navController: NavController) {
    BottomAppBar(
        containerColor = Color(0xFFFFA500),
        modifier = Modifier
            .height(75.dp)
            .fillMaxWidth() // Ajusta el ancho para que ocupe toda la pantalla
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        NavBarItems.forEach { navItem ->
            val isSelected = currentRoute == navItem.route

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = navItem.image,
                        contentDescription = navItem.title,
                        tint = Color.White,
                        modifier = Modifier.size(if (isSelected) 30.dp else 25.dp)
                    )
                },
                label = {
                    Text(
                        text = navItem.title,
                        color = Color.White,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                    )
                },
            )
        }
    }
}