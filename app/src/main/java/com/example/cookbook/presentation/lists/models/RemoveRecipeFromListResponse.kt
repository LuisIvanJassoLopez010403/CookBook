package com.example.cookbook.presentation.lists.models

data class RemoveRecipeFromListResponse(
    val message: String,
    val list: UserListsResponse?
)