package com.izzatismail.pokepal.model.response

sealed class ResultWrapper<out T> {
    data class Success<out T>(val  value: T): ResultWrapper<T>()
    data class GenericError(val  statusCode: Int?, val apiErrorResponse: ApiErrorResponse?, val throwable: Throwable): ResultWrapper<Nothing>()
    object NetworkError: ResultWrapper<Nothing>()
}