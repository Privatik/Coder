package com.io.coder.data.datasource

import com.io.coder.App
import com.io.coder.data.api_datasource.EmployeeDataSource
import com.io.coder.data.api_retrofit.EmployeeApi
import com.io.coder.util.errorHandler

class EmployeeDataSourceImpl(
    private val api: EmployeeApi = App.api
): EmployeeDataSource {

    override suspend fun getUsers() =
        errorHandler {
            api.getUsers()
        }
}