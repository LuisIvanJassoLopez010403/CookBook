package com.example.cookbook.presentation.lists.network

import com.example.cookbook.network.ApiService
import com.example.cookbook.network.RetrofitClientInstance
import com.example.cookbook.presentation.lists.models.UserListsBody
import com.example.cookbook.presentation.lists.models.UserListsResponse

object UserListsBodyRepository {
    suspend fun getUserLists(userListsBody: UserListsBody, token: String): List<UserListsResponse> {
        val apiService = RetrofitClientInstance.getRetrofitInstance(token = token).create(ApiService::class.java)
        return apiService.getUserLists(userListsBody)
    }
}
