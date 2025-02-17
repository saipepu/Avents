package com.example.avents.repository

import com.example.avents.model.GetUserByIdResponse
import com.example.avents.model.User
import com.example.avents.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val apiService: ApiService) {
    suspend fun getUserById(userId: String): GetUserByIdResponse {
        return apiService.getUserById(userId)
    }
}