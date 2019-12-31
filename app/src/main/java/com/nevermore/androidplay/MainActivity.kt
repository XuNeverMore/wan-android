package com.nevermore.androidplay

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG: String = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_text.text = "XXX"

        tv_text.setOnClickListener {

            val register = RetrofitHelper.getService().register("xct123", "cccccc", "cccccc")
            register.enqueue(object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.i(TAG, t.message ?: "")

                }

                override fun onResponse(call: Call<String>, response: Response<String>) {

                    Log.i(TAG, response.message())
                }
            })
        }

    }
}
