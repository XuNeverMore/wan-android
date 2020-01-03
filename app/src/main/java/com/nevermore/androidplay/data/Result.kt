package com.nevermore.androidplay.data

sealed class Result<T> {

    data class Success<T>(val t: T) : Result<T>()
    data class Error<T>(val e: Throwable) : Result<T>()
}