package com.nevermore.androidplay

import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.nevermore.androidplay.data.ProjectCategoryBean
import com.nevermore.androidplay.data.ResponseEntity
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
//        tv_text.text = "XXX"
//
//        tv_text.setOnClickListener {
//
//            val appService = RetrofitHelper.getService()
//
//            appService.getProjectCategory()
//                .enqueue(object : Callback<ResponseEntity<List<ProjectCategoryBean>>> {
//                    override fun onFailure(
//                        call: Call<ResponseEntity<List<ProjectCategoryBean>>>,
//                        t: Throwable
//                    ) {
//                        Log.i(TAG, t.message ?: "empty")
//
//                    }
//
//                    override fun onResponse(
//                        call: Call<ResponseEntity<List<ProjectCategoryBean>>>,
//                        response: Response<ResponseEntity<List<ProjectCategoryBean>>>
//                    ) {
//                        response.takeIf {
//                            it.isSuccessful
//                        }?.apply {
//                            val body = body()
//                            val isMainThread = mainLooper == Looper.myLooper()
//                            Log.i(TAG, "isMainThread:$isMainThread")
//                        }
//                    }
//                })
//
//        }

    }
}


