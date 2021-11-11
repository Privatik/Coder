package com.io.coder.data.api_retrofit

import com.io.coder.data.model.EmployeeResponse
import com.io.coder.data.model.ItemsEmployeeResponse
import com.io.coder.util.Constants
import retrofit2.http.GET

interface EmployeeApi {

    @GET(Constants.ENDPOINT_GET_USERS)
    suspend fun getUsers(): ItemsEmployeeResponse
}