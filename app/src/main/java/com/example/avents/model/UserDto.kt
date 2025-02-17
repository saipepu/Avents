package com.example.avents.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetUserByIdResponse(
    @SerialName("success") val success: Boolean,
    @SerialName("message") val message: User? = null,
    @SerialName("error") val error: String? = null,
)

@Serializable
data class User(
    @SerialName("_id") val _id: String,
    @SerialName("firstName") val firstName: String,
    @SerialName("email") val email: String,
    @SerialName("phone") val phone: Int,
    @SerialName("hashedPassword") val hashedPassword: String,
    @SerialName("isDeleted") val isDeleted: Boolean,
    @SerialName("createdAt") val createdAt: String,
    @SerialName("updatedAt") val updatedAt: String,
    @SerialName("__v") val __v: Int,
    @SerialName("isOrganizer") val isOrganizer: Boolean
)