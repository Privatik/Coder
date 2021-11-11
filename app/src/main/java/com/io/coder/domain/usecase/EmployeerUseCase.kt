package com.io.coder.domain.usecase

import com.io.coder.data.repository.EmployeeRepositoryImpl
import com.io.coder.domain.model.Employee
import com.io.coder.domain.repository.EmployeeRepository
import com.io.coder.domain.util.Resource
import kotlinx.coroutines.flow.Flow

import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class EmployeeUseCase(
    val employeeRepository: EmployeeRepository = EmployeeRepositoryImpl()
) {

    operator fun invoke(): Flow<Resource<List<Employee>>> = flow {
        try {
            emit(Resource.Loading<List<Employee>>())
            val employees = employeeRepository.getUsers().getOrThrow()
            emit(Resource.Success<List<Employee>>(employees))
        } catch (e: HttpException){
            emit(Resource.Error<List<Employee>>(e.localizedMessage ?: "Server error"))
        } catch (e: IOException){
            emit(Resource.Error<List<Employee>>(e.localizedMessage ?: "IO error"))
        }
    }
}