package com.example.retrofit2hiltmvvmcoroutine.btn02.net

import com.example.retrofit2hiltmvvmcoroutine.common.bean.ListBean
import com.example.retrofit2hiltmvvmcoroutine.common.net.response.ResponseData
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService02 {

    @GET("article/list/{pageNum}/json")
    suspend fun getList(@Path("pageNum") pageNum: Int): ResponseData<ListBean>

    @POST("article/query/0/json")
    @FormUrlEncoded
    suspend fun searchList(@FieldMap keyword: Map<String, String>): ResponseData<ListBean>

}