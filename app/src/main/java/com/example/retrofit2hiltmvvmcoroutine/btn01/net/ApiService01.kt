package com.example.retrofit2hiltmvvmcoroutine.btn01.net

import com.example.retrofit2hiltmvvmcoroutine.common.bean.ListBean
import com.example.retrofit2hiltmvvmcoroutine.common.net.response.ResponseData
import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * 定义接口
 */
interface ApiService01 {

    @GET("article/list/{pageNum}/json")
    fun getList(@Path("pageNum") pageNum: Int): Call<ResponseData<ListBean>>

    /**
     * 设置不同的 BaseUrl
     */
    // @Headers("BaseUrlName:baidu")
    // @GET("article/list/{pageNum}/json")
    // fun getList02(@Path("pageNum") pageNum: Int): Call<ResponseData<ListBean>>

    @POST("article/query/0/json")
    @FormUrlEncoded
    fun searchList(@FieldMap keyword: Map<String, String>): Call<ResponseData<ListBean>>

}