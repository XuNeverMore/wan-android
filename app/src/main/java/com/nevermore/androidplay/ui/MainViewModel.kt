package com.nevermore.androidplay.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nevermore.androidplay.RetrofitHelper
import com.nevermore.androidplay.data.*

class MainViewModel : ViewModel() {


    val articleList: MutableLiveData<List<Article>> = MutableLiveData()


    fun getArticleList(page: Int) {

        val call = RetrofitHelper.getService().getArticleList(page)
        call.enqueue(object : ResultCallback<ResponseEntity<ArticleData>>() {
            override fun onResult(result: Result<ResponseEntity<ArticleData>>) {
                if (result is Result.Success) {
                    val data = result.t.data
                    articleList.value = data.datas
                }
            }
        })

    }

}
