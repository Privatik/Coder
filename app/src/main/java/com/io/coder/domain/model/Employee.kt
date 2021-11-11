package com.io.coder.domain.model

import com.io.coder.domain.state.Department

data class Employee(
    val avatarUrl: String,
    val birthday: BirthDay,
    val department: Department,
    val firstName: String,
    val id: String,
    val lastName: String,
    val phone: String,
    val position: String,
    val userTag: String
)