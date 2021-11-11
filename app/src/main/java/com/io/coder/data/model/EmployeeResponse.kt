package com.io.coder.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ItemsEmployeeResponse(
    val items: List<EmployeeResponse>
)

@Serializable
data class EmployeeResponse(
    val avatarUrl: String,
    val birthday: String,
    val department: DepartmentResponse,
    val firstName: String,
    val id: String,
    val lastName: String,
    val phone: String,
    val position: String,
    val userTag: String
)