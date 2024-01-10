package com.example.retrofit2hiltmvvmcoroutine.btn02.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit2hiltmvvmcoroutine.btn02.net.Test02RequestRepository
import com.example.retrofit2hiltmvvmcoroutine.common.bean.ListBean
import com.example.retrofit2hiltmvvmcoroutine.common.net.exception.ApiException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Test02ViewModel @Inject constructor(private val retroRepository: Test02RequestRepository) : ViewModel() {

    val listBean: MutableLiveData<ListBean> = MutableLiveData<ListBean>()

    fun loadListBean(pageNum: Int) {
        viewModelScope.launch {
            try {
                val data: ListBean = retroRepository.getListBean(pageNum)
                listBean.value = data
            } catch (e: Exception) {
                val ex = ApiException.handleException(e)
                Log.e("TAG","ex：${ex.message}")

                // 如果担心返回null，导致view报错，可以返回一个空实体
                // listBean.value = ListBean()
            } finally {

            }
        }
    }

    fun searchListBean(keyword: Map<String, String>) {
        viewModelScope.launch {
            try {
                val data: ListBean = retroRepository.getSearchListBean(keyword)
                listBean.value = data
            } catch (e: Exception) {
                val ex = ApiException.handleException(e)
                Log.e("TAG","ex：${ex.message}")
            } finally {

            }
        }
    }

}