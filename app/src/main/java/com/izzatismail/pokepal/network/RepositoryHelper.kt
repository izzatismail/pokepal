package com.izzatismail.pokepal.network

import android.util.Log
import com.izzatismail.pokepal.model.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.IOException

suspend fun <T> makeSafeApiCall(dispatcher: CoroutineDispatcher, apiCall: suspend () -> T): ResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            ResultWrapper.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> ResultWrapper.NetworkError
                else -> {
                    Log.d(this::class.java.name, "API Error Exception : $throwable")
                    ResultWrapper.GenericError(
                        statusCode = null,
                        apiErrorResponse = null,
                        throwable = throwable
                    )
                }
            }
        }
    }
}