package com.io.coder

import android.app.Application
import com.io.coder.data.api_retrofit.EmployeeApi
import com.io.coder.util.Constants
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class App: Application() {

    companion object{
        val client = OkHttpClient().let {
            val logger = HttpLoggingInterceptor()

            logger.level =  if (BuildConfig.DEBUG)  HttpLoggingInterceptor.Level.BODY
                            else                    HttpLoggingInterceptor.Level.NONE


            it.newBuilder()
                .addInterceptor(logger)
                .build()
        }

        @OptIn(ExperimentalSerializationApi::class)
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .client(client)
            .build()

        val api = retrofit.create(EmployeeApi::class.java)
    }
}