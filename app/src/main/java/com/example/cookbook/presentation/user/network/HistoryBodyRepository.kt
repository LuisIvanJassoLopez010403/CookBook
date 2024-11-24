package com.example.cookbook.presentation.user.network

import com.example.cookbook.network.ApiService
import com.example.cookbook.network.RetrofitClientInstance
import com.example.cookbook.presentation.user.models.HistoryBody
import com.example.cookbook.presentation.user.models.HistoryItem
import com.example.cookbook.presentation.user.models.HistoryResponse

object HistoryBodyRepository {
    suspend fun viewHistory(history: HistoryBody, token: String): List<HistoryItem> {
        val apiService = RetrofitClientInstance.getRetrofitInstance(token = token).create(ApiService::class.java)
        return apiService.viewHistory(history)
    }
}