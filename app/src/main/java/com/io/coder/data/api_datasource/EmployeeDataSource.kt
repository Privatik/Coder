package com.io.coder.data.api_datasource

import com.io.coder.data.model.ItemsEmployeeResponse

interface EmployeeDataSource {

    suspend fun getUsers(): Result<ItemsEmployeeResponse>
}