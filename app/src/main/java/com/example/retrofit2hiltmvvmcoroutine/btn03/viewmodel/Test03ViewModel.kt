package com.example.retrofit2hiltmvvmcoroutine.btn03.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.retrofit2hiltmvvmcoroutine.btn03.net.Test03RequestRepository
import com.example.retrofit2hiltmvvmcoroutine.common.bean.DatasBean
import com.example.retrofit2hiltmvvmcoroutine.common.bean.ListBean
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Test03ViewModel @Inject constructor(private val retroRepository: Test03RequestRepository) : ViewModel() {

    val listBean: MutableLiveData<ListBean> = MutableLiveData<ListBean>()

    var isReset: Boolean = false

    fun loadListBean(): Flow<PagingData<DatasBean>> {
        return retroRepository.getListBean(this)
    }
}