package com.example.retrofit2hiltmvvmcoroutine.btn01.net

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.retrofit2hiltmvvmcoroutine.common.bean.ListBean
import com.example.retrofit2hiltmvvmcoroutine.common.net.response.ResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Test01RequestRepository @Inject constructor(private val apiService01: ApiService01) {

    // Test01RequestRepository 和 它的参数对象apiService01，都会被Hilt 自动实例化
    // @Inject constructor(private val apiService01: ApiService01)

    fun getSearchListBean(
        keyword: Map<String, String>,
        listBean: MutableLiveData<ListBean>
    ): Call<ResponseData<ListBean>> {
        val responseCall: Call<ResponseData<ListBean>> = apiService01.searchList(keyword)
        responseCall.enqueue(object : Callback<ResponseData<ListBean>> {
            override fun onResponse(
                call: Call<ResponseData<ListBean>>,
                response: Response<ResponseData<ListBean>>
            ) {
                // Log.d("TAG", "onResponse：" + response.body()?.data?.datas?.size)
                listBean.postValue(response.body()?.data)
            }

            override fun onFailure(call: Call<ResponseData<ListBean>>, t: Throwable) {
                // Log.d("TAG", "onFailure：$t")
            }

        })
        return responseCall
    }

    fun getListBean(
        pageNum: Int,
        listBean: MutableLiveData<ListBean>
    ): Call<ResponseData<ListBean>> {
        val responseCall: Call<ResponseData<ListBean>> = apiService01.getList(pageNum)
        responseCall.enqueue(object : Callback<ResponseData<ListBean>> {
            override fun onResponse(
                call: Call<ResponseData<ListBean>>,
                response: Response<ResponseData<ListBean>>
            ) {
                // Log.d("TAG", "onResponse：" + response.body()?.data?.datas?.size)
                listBean.postValue(response.body()?.data)
            }

            override fun onFailure(call: Call<ResponseData<ListBean>>, t: Throwable) {
                // Log.d("TAG", "onFailure：$t")
            }

        })
        return responseCall
    }

}