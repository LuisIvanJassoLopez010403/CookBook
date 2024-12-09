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
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cookbook.presentation.finder.views.InitialFinderView
import com.example.cookbook.presentation.addrecipe.views.AddRecipeView
import com.example.cookbook.presentation.finder.network.IngredientByCategory
import com.example.cookbook.presentation.finder.network.SpecifiedFinderRepository
import com.example.cookbook.presentation.finder.viewmodels.SpecifiedFinderViewModel
import com.example.cookbook.presentation.finder.viewmodels.FinderViewModelFactory
import com.example.cookbook.presentation.finder.views.SearchView
import com.example.cookbook.presentation.home.home.network.HomeRepository
import com.example.cookbook.presentation.home.home.viewmodels.HomeViewModel
import com.example.cookbook.presentation.home.home.viewmodels.HomeViewModelFactory
import com.example.cookbook.presentation.home.home.views.HomeView
import com.example.cookbook.presentation.lists.views.CreateListView
import com.example.cookbook.presentation.lists.views.ListRecipesView
import com.example.cookbook.presentation.login.views.ChangePasswordView
import com.example.cookbook.presentation.login.views.ForgotPasswordView
import com.example.cookbook.presentation.login.views.LoginView
import com.example.cookbook.presentation.login.views.VerificationCodeView
import com.example.cookbook.presentation.myRecipe.MyRecipeView
import com.example.cookbook.presentation.onboarding.OnboardingView
import com.example.cookbook.presentation.onboarding.OnboardingViewModel
import com.example.cookbook.presentation.recipe.views.RecipeDetailView
import com.example.cookbook.presentation.signup.views.SignupView
import com.example.cookbook.presentation.title.views.TitleView
import com.example.cookbook.presentation.title.views.AboutView
import com.example.cookbook.presentation.user.views.UserEditView
import com.example.cookbook.presentation.user.views.UserView
import com.example.cookbook.utils.WebViewScreen

// Prueba
@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun MyAppNavigationView(onboardingViewModel: OnboardingViewModel = viewModel()) {
    val navController = rememberNavController()
    val isOnboardingCompleted by onboardingViewModel.isOnboardingCompleted.collectAsState(initial = null)
    val appContext = LocalContext.current

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
            composable(Routes.AboutView) {
                AboutView(navController)
            }
            composable(
                Routes.WebView,
                arguments = listOf(navArgument("url") { type = NavType.StringType })
            ) { backStackEntry ->
                val url = backStackEntry.arguments?.getString("url") ?: ""
                WebViewScreen(url = url, navController)
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
                val specifiedFinderRepository = SpecifiedFinderRepository
                val ingredientByCategory = IngredientByCategory
                val specifiedFinderViewModel: SpecifiedFinderViewModel = viewModel(
                    factory = FinderViewModelFactory(
                        specifiedFinderRepository,
                        ingredientByCategory,
                        appContext
                    )
                )

                InitialFinderView(navController, specifiedFinderViewModel)
            }

            composable(Routes.MyRecipeView) {
                MyRecipeView(navController)
            }
            composable(Routes.HomeView) {
                val homeRepository = HomeRepository // Instancia de HomeRepository
                val viewModel: HomeViewModel = viewModel(
                    factory = HomeViewModelFactory(
                        homeRepository,
                        appContext
                    )
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
                val specifiedFinderRepository = SpecifiedFinderRepository
                val ingredientByCategory = IngredientByCategory
                val specifiedFinderViewModel: SpecifiedFinderViewModel = viewModel(
                    factory = FinderViewModelFactory(
                        specifiedFinderRepository,
                        ingredientByCategory,
                        appContext
                    )
                )

                SearchView(navController, specifiedFinderViewModel)
            }

            composable(
                Routes.RecipeDetailView,
                arguments = listOf(navArgument("recipeId") { type = NavType.StringType })
            ) { backStackEntry ->
                val recipeId = backStackEntry.arguments?.getString("recipeId") ?: ""
                RecipeDetailView(recipeId = recipeId, navController = navController)
            }
            composable(
                Routes.CreateListView,
                arguments = listOf(navArgument("recipeId") { type = NavType.StringType })
            ) { backStackEntry ->
                val recipeId = backStackEntry.arguments?.getString("recipeId")
                CreateListView(navController = navController, recipeId = recipeId)
            }
            composable(
                Routes.ListRecipesView,
                arguments = listOf(navArgument("listId") { type = NavType.StringType })
            ) { backStackEntry ->
                val listId = backStackEntry.arguments?.getString("listId") ?: ""
                ListRecipesView(listId = listId, navController = navController)
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

