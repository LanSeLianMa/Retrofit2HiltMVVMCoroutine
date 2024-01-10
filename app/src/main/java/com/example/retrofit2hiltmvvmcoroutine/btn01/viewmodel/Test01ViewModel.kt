package com.example.retrofit2hiltmvvmcoroutine.btn01.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofit2hiltmvvmcoroutine.btn01.net.Test01RequestRepository
import com.example.retrofit2hiltmvvmcoroutine.common.bean.ListBean
import com.example.retrofit2hiltmvvmcoroutine.common.net.response.ResponseData
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import javax.inject.Inject

@HiltViewModel
class Test01ViewModel @Inject constructor(private val retroRepository: Test01RequestRepository) :
    ViewModel() {

    // Test01ViewModel 和 它的参数对象retroRepository，都会被Hilt 自动实例化
    // @Inject constructor(private val retroRepository: Test01RequestRepository)

    val listBean: MutableLiveData<ListBean> = MutableLiveData<ListBean>()

    fun loadListBean(pageNum: Int): Call<ResponseData<ListBean>> {
        return retroRepository.getListBean(pageNum, listBean)
    }

    fun searchListBean(keyword: Map<String, String>): Call<ResponseData<ListBean>> {
        return retroRepository.getSearchListBean(keyword, listBean)
    }

}