package com.example.cookbook.presentation.finder.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cookbook.presentation.finder.network.FinderBodyRepository

class FinderViewModelFactory (
    private val repository: FinderBodyRepository
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FinderViewModel::class.java)) {
            return FinderViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}