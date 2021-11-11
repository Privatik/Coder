package com.io.coder.data.repository

import com.io.coder.data.api_datasource.EmployeeDataSource
import com.io.coder.data.datasource.EmployeeDataSourceImpl
import com.io.coder.data.mapper.toModel
import com.io.coder.domain.repository.EmployeeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EmployeeRepositoryImpl(
    private val dataSource: EmployeeDataSource =  EmployeeDataSourceImpl()
): EmployeeRepository {

    override suspend fun getUsers() = withContext(Dispatchers.IO){
        val res = dataSource.getUsers()

        res.mapCatching { response -> response.items.map { it.toModel() } }
    }

}