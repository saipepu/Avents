package com.example.avents.model

import kotlinx.serialization.Serializable

@Serializable
data class UesrDto (
    val _id: String,
    val firstName: String,
    val email: String,
    val hashedPassword: String,
)
