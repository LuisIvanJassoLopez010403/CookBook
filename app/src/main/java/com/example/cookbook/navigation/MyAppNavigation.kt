package com.example.cookbook.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cookbook.presentation.finder.views.InitialFinderView
import com.example.cookbook.presentation.addrecipe.views.AddRecipeView
import com.example.cookbook.presentation.finder.network.SpecifiedFinderRepository
import com.example.cookbook.presentation.finder.viewmodels.SpecifiedFinderViewModel
import com.example.cookbook.presentation.finder.viewmodels.FinderViewModelFactory
import com.example.cookbook.presentation.finder.views.SearchView
import com.example.cookbook.presentation.home.home.network.HomeRepository
import com.example.cookbook.presentation.home.home.viewmodels.HomeViewModel
import com.example.cookbook.presentation.home.home.viewmodels.HomeViewModelFactory
import com.example.cookbook.presentation.home.home.views.HomeView
import com.example.cookbook.presentation.login.views.ChangePasswordView
import com.example.cookbook.presentation.login.views.ForgotPasswordView
import com.example.cookbook.presentation.login.views.LoginView
import com.example.cookbook.presentation.login.views.VerificationCodeView
import com.example.cookbook.presentation.myRecipe.MyRecipeView
import com.example.cookbook.presentation.onboarding.OnboardingView
import com.example.cookbook.presentation.onboarding.OnboardingViewModel
import com.example.cookbook.presentation.signup.views.SignupView
import com.example.cookbook.presentation.title.TitleView
import com.example.cookbook.presentation.user.views.UserEditView
import com.example.cookbook.presentation.user.views.UserView

// Prueba
@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun MyAppNavigationView(onboardingViewModel: OnboardingViewModel = viewModel()) {
    val navController = rememberNavController()
    val isOnboardingCompleted by onboardingViewModel.isOnboardingCompleted.collectAsState(initial = null)

    // Decidir cuál será el destino inicial
    if (isOnboardingCompleted == null) {
    // Mientras se determina el estado, mostrar una pantalla de carga
        LoadingScreen()
    } else {
        val startDestination = if (isOnboardingCompleted == true) Routes.TitleView else Routes.OnboardingView

        // Configurar el NavHost con el destino inicial decidido
        NavHost(navController = navController, startDestination = startDestination) {
            composable(Routes.OnboardingView) {
                OnboardingView(navController, onboardingViewModel = onboardingViewModel)
            }
            composable(Routes.TitleView) {
                TitleView(navController)
            }
            composable(Routes.SignupView) {
                SignupView(navController)
            }
            composable(Routes.LoginView) {
                LoginView(navController)
            }
            composable(Routes.ForgotPasswordView) {
                ForgotPasswordView(navController)
            }
            composable(Routes.VerificationCodeView) {
                VerificationCodeView(navController)
            }
            composable(Routes.ChangePasswordView) {
                ChangePasswordView(navController)
            }
            composable(Routes.UserView) {
                UserView(navController)
            }
            composable(Routes.InitialFinderView) {
                val parentEntry = remember { navController.getBackStackEntry(Routes.InitialFinderView) }
                val specifiedFinderViewModel: SpecifiedFinderViewModel = viewModel(parentEntry, factory = FinderViewModelFactory(
                    SpecifiedFinderRepository
                ))
                InitialFinderView(navController, specifiedFinderViewModel)
            }
            composable(Routes.MyRecipeView) {
                MyRecipeView(navController)
            }
            composable(Routes.HomeView) {
                val viewModel: HomeViewModel = viewModel(
                    factory = HomeViewModelFactory(HomeRepository)
                )
                HomeView(
                    navController = navController,
                    viewModel = viewModel
                )
            }
            composable(Routes.AddRecipeView) {
                AddRecipeView(navController)
            }
            composable(Routes.UserEditView) {
                UserEditView(navController)
            }
            composable(Routes.SearchView) {
                val parentEntry = remember { navController.getBackStackEntry(Routes.InitialFinderView) }
                val specifiedFinderViewModel: SpecifiedFinderViewModel = viewModel(parentEntry, factory = FinderViewModelFactory(
                    SpecifiedFinderRepository)
                )
                SearchView(navController, specifiedFinderViewModel)
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}
