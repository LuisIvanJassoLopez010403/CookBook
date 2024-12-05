package com.example.cookbook.presentation.finder.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cookbook.presentation.finder.network.IngredientByCategory
import com.example.cookbook.presentation.finder.network.SpecifiedFinderRepository

class FinderViewModelFactory (
    private val finderRepository: SpecifiedFinderRepository,
    private val ingredientRepository: IngredientByCategory
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SpecifiedFinderViewModel::class.java)) {
            return SpecifiedFinderViewModel(
                FinderBodyRepository = finderRepository,
                IngredientBody = ingredientRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}