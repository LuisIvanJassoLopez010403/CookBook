package com.example.cookbook.navigation

object Routes {

    // Rutas existentes
    const val OnboardingView = "OnboardingView"
    const val TitleView = "TitleView"
    const val AboutView = "AboutView"
    const val LoginView = "LoginView"
    const val UserView = "UserView"
    const val WebView = "webview/{url}"

    // Home
    const val HomeView = "HomeView"

    // Finder
    const val InitialFinderView = "InitialFinderView"
    const val SearchView = "SearchView"

    // Otras vistas
    const val SignupView = "SignupView"
    const val ForgotPasswordView = "ForgotPasswordView"
    const val VerificationCodeView = "VerificationCodeView"
    const val ChangePasswordView = "ChangePasswordView"

    const val MyRecipeView = "MyRecipeView"
    const val AddRecipeView = "AddRecipeView"
    const val UserEditView = "UserEditView"

    // Rutas dinámicas
    const val RecipeDetailView = "recipe_detail/{recipeId}"
    const val CreateListView = "createList/{recipeId}"
    const val ListRecipesView = "list_recipes/{listId}"

    // Helpers
    fun recipeDetailRoute(recipeId: String) = "recipe_detail/$recipeId"
    fun createListRoute(recipeId: String) = "createList/$recipeId"
}
