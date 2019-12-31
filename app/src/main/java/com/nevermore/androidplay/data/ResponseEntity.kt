package com.nevermore.androidplay.data

data class ResponseEntity<out T>(
    val data: T,
    val errorCode: Int,
    val errorMsg: String
)