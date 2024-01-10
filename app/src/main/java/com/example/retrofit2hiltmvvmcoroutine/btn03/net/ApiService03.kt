package com.example.retrofit2hiltmvvmcoroutine.btn03.net

import com.example.retrofit2hiltmvvmcoroutine.common.bean.ListBean
import com.example.retrofit2hiltmvvmcoroutine.common.net.response.ResponseData
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService03 {

    @GET("article/list/{pageNum}/json")
    suspend fun getList(@Path("pageNum") pageNum: Int): ResponseData<ListBean>

}