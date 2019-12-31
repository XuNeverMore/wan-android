package com.nevermore.androidplay

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface AppService {


    @FormUrlEncoded
    @POST("user/register")
    fun register(
        @Field(value = "username") username: String,
        @Field(value = "password") password: String,
        @Field(value = "repassword") repassword: String
    ): Call<String>


    companion object {
        const val BASE_URL: String = "https://www.wanandroid.com"


    }

}