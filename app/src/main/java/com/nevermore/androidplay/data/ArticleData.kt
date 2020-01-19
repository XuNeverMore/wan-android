package com.nevermore.androidplay.data

/**
 * @author xuchuanting
 * Create on 2020/1/19 11:39
 */
data class ArticleData(
    val curPage: Int,
    val offset: Int,
    val over: Boolean,
    val datas: List<Article>,
    val pageCount: Int,
    val size: Int,
    val total: Int
)