package com.nevermore.androidplay.data

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class ResultCallback<T> : Callback<T> {

    override fun onFailure(call: Call<T>, t: Throwable) {
        onResult(Result.Error(t))
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {

        if (response.isSuccessful) {
            response.body()
                ?.apply {
                    onResult(Result.Success(this))
                }
                ?: onResult(Result.Error(Throwable()))
        } else {
            onResult(Result.Error(Throwable()))
        }
    }

    abstract fun onResult(result: Result<T>)
}