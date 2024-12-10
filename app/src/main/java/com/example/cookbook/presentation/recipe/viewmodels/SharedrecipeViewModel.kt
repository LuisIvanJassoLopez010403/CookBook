package com.example.cookbook.presentation.recipe.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SharedRecipeViewModel : ViewModel() {

    private val _deletedRecipeId = MutableStateFlow<String?>(null)
    val deletedRecipeId: StateFlow<String?> get() = _deletedRecipeId


    fun setDeletedRecipeId(recipeId: String?) {
        _deletedRecipeId.value = recipeId
    }
}