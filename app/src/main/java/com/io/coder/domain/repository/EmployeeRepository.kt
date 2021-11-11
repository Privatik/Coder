package com.io.coder.domain.repository

import com.io.coder.data.model.EmployeeResponse
import com.io.coder.data.model.ItemsEmployeeResponse
import com.io.coder.domain.model.Employee

interface EmployeeRepository {

    suspend fun getUsers(): Result<List<Employee>>
}