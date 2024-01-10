package com.example.retrofit2hiltmvvmcoroutine.btn03.net

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.retrofit2hiltmvvmcoroutine.btn03.paging.MPagingSource
import com.example.retrofit2hiltmvvmcoroutine.btn03.viewmodel.Test03ViewModel
import com.example.retrofit2hiltmvvmcoroutine.common.bean.DatasBean
import com.example.retrofit2hiltmvvmcoroutine.common.bean.ListBean
import com.example.retrofit2hiltmvvmcoroutine.common.net.response.ResponseData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Test03RequestRepository @Inject constructor(private val apiService03: ApiService03) {

    fun getListBean(
        viewModel: Test03ViewModel
    ): Flow<PagingData<DatasBean>> {
        val config = PagingConfig(
            20, // 每页数量
            2, // 预加载，比如滚动到还剩2个的时候，开始加载下一页
            false,
            20 // 初始化数量
        )
        return Pager(config = config,
            initialKey = 1,
            pagingSourceFactory = {
                MPagingSource(apiService03, viewModel)
            }).flow.cachedIn(viewModel.viewModelScope)
    }

}