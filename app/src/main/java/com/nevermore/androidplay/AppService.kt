package com.nevermore.androidplay

import com.nevermore.androidplay.data.ProjectCategoryBean
import com.nevermore.androidplay.data.ResponseEntity
import com.nevermore.androidplay.data.UserBean
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface AppService {


    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("user/register")
    fun register(
        @Field(value = "username") username: String,
        @Field(value = "password") password: String,
        @Field(value = "repassword") repassword: String
    ): Call<ResponseEntity<UserBean>>


    @POST("user/login")
    fun login(@Field(value = "username") username: String,
              @Field(value = "password") password: String)


    @GET("project/tree/json")
    fun getProjectCategory(): Call<ResponseEntity<List<ProjectCategoryBean>>>

    companion object {
        const val BASE_URL: String = "https://www.wanandroid.com"


    }

}