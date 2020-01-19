package com.nevermore.androidplay

import com.nevermore.androidplay.data.ArticleData
import com.nevermore.androidplay.data.ProjectCategoryBean
import com.nevermore.androidplay.data.ResponseEntity
import com.nevermore.androidplay.data.UserBean
import retrofit2.Call
import retrofit2.http.*

/**
 * 玩安卓
 * https://www.wanandroid.com/blog/show/2
 */
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


    @FormUrlEncoded
    @POST("user/login")
    fun login(
        @Field(value = "username") username: String,
        @Field(value = "password") password: String
    ): Call<ResponseEntity<UserBean>>


    /**
     * 首页文章
     */
//    https://www.wanandroid.com/article/list/0/json

    @GET("article/list/{page}/json")
    fun getArticleList(@Path("page") page: Int): Call<ResponseEntity<ArticleData>>


    @GET("project/tree/json")
    fun getProjectCategory(): Call<ResponseEntity<List<ProjectCategoryBean>>>

    companion object {
        const val BASE_URL: String = "https://www.wanandroid.com"

    }

}