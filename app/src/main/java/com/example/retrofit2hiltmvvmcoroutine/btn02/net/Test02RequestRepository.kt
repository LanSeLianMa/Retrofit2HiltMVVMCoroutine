package com.example.retrofit2hiltmvvmcoroutine.btn02.net

import com.example.retrofit2hiltmvvmcoroutine.common.bean.ListBean
import com.example.retrofit2hiltmvvmcoroutine.common.net.response.ResponseData
import javax.inject.Inject

class Test02RequestRepository @Inject constructor(private val apiService02: ApiService02) {

    suspend fun getSearchListBean(
        keyword: Map<String, String>,
    ): ListBean {
        val responseData: ResponseData<ListBean> = apiService02.searchList(keyword)
        return responseData.data
    }

    suspend fun getListBean(
        pageNum: Int,
    ): ListBean {
        val responseData: ResponseData<ListBean> = apiService02.getList(pageNum)
        return responseData.data
    }

}