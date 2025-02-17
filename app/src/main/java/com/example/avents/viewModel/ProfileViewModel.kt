package com.example.avents.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avents.model.User
import com.example.avents.network.ApiService
import com.example.avents.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel: ViewModel() {
    private val repository = UserRepository(apiService = ApiService())
    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun getUserById(userId: String) {
        viewModelScope.launch {
            try {
                val result = repository.getUserById(userId)
                println("RESULT: $result")
                if (result.success) {
                    _user.value = result.message // Nullable, handled safely
                } else {
                    _error.value = result.error ?: "Unknown error"
                }

            } catch (e: Exception) {
                _error.value = e.localizedMessage ?: "Network error"
            }
        }
    }
}