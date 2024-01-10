package com.example.retrofit2hiltmvvmcoroutine.btn03.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.retrofit2hiltmvvmcoroutine.btn03.net.ApiService03
import com.example.retrofit2hiltmvvmcoroutine.btn03.viewmodel.Test03ViewModel
import com.example.retrofit2hiltmvvmcoroutine.common.bean.DatasBean
import com.example.retrofit2hiltmvvmcoroutine.common.bean.ListBean
import com.example.retrofit2hiltmvvmcoroutine.common.net.response.ResponseData

class MPagingSource(
    private val apiService03: ApiService03,
    private val viewModel: Test03ViewModel
) : PagingSource<Int, DatasBean>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DatasBean> {
        return try {
            // Start refresh at page 1 if undefined.
            var nextPageNumber = params.key ?: 1
            if (viewModel.isReset) {
                nextPageNumber = 1
            }
            val response: ResponseData<ListBean> = apiService03.getList(nextPageNumber)
            viewModel.listBean.postValue(response.data)
            viewModel.isReset = false

            LoadResult.Page(
                data = response.data.datas,
                prevKey = null, // Only paging forward.
                nextKey = response.data.curPage // 下一页，自动累加
            )
        } catch (e: Exception) {
            // Handle errors in this block and return LoadResult.Error for
            // expected errors (such as a network failure).
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DatasBean>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}