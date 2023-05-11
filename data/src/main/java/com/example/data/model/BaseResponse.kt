package com.example.data.model

data class BaseResponse (
    val result: String,
    val code: Int
)

sealed class ApiState<T>(
    val data: T? = null,
    val message: String? = null
){
    class Success<T>(data: T) : ApiState<T>(data)
    class Error<T>(message: String, data: T? = null) : ApiState<T>(data, message)
    class Loading<T> : ApiState<T>()
}

