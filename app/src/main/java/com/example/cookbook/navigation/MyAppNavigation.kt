package com.example.cookbook.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cookbook.presentation.Finder.InitialFinderView
import com.example.cookbook.presentation.home.view.HomeView
import com.example.cookbook.presentation.login.views.LoginView
import com.example.cookbook.presentation.myRecipe.MyRecipeView
import com.example.cookbook.presentation.onboarding.OnboardingView
import com.example.cookbook.presentation.signup.SignupView
import com.example.cookbook.presentation.title.TitleView
import com.example.cookbook.presentation.user.UserView

// Prueba
@Composable
fun MyAppNavigationView() {
    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = Routes.OnboardingView, builder = {
            composable(Routes.OnboardingView) {         //
                OnboardingView(navController)
            }
            composable(Routes.OnboardingView) {         //
                OnboardingView(navController)
            }
            composable(Routes.TitleView) {
                TitleView(navController)
            }
            composable(Routes.SignupView){
                SignupView(navController)
            }
            composable(Routes.LoginView) {
                LoginView(navController)
            }
            composable(Routes.UserView) {
                UserView(navController)
            }
            composable(Routes.InitialFinderView) {
                InitialFinderView(navController)
            }
            composable(Routes.MyRecipeView){
                MyRecipeView(navController)
            }
            composable(Routes.HomeView){
                HomeView(navController)
            }
        })
}