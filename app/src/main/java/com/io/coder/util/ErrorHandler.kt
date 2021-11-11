package com.io.coder.util

import retrofit2.HttpException


internal suspend fun <T> errorHandler(request: suspend () -> T): Result<T> {
    return try {
        Result.success(request.invoke())
    } catch (e: Exception) {
        Result.failure(e)
    }
}
