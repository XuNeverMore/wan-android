package com.nevermore.androidplay

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {


    companion object {
//        @Volatile
//        private var instance: RetrofitHelper? = null

//        fun getInstance(): RetrofitHelper {
//
//            return instance ?: synchronized(this) {
//                instance ?: RetrofitHelper().also { instance = it }
//            }
//        }

        private var appService: AppService? = null

        fun getService(): AppService {

            return appService ?: buildAppService().apply { appService = this }
        }

        private fun buildAppService(): AppService {
            return Retrofit.Builder()
                .baseUrl(AppService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .build()
                .create(AppService::class.java)
        }
    }
}