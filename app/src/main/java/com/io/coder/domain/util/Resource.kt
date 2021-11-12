package com.io.coder.domain.util

import java.lang.Exception

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String,val type: Type, data: T? = null) : Resource<T>(data, message){
        enum class Type{
            SERVER_ERROR,
            API_ERROR
        }
    }
    class Loading<T>(data: T? = null) : Resource<T>(data)
}
