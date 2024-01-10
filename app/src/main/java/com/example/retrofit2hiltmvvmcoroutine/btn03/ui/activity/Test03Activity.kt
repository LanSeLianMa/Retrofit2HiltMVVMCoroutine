package com.example.retrofit2hiltmvvmcoroutine.btn03.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit2hiltmvvmcoroutine.btn03.viewmodel.Test03ViewModel
import com.example.retrofit2hiltmvvmcoroutine.common.adapter.MPagingDataAdapter
import com.example.retrofit2hiltmvvmcoroutine.databinding.AtyTest03Binding
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Test03Activity : AppCompatActivity() {

    private lateinit var bind: AtyTest03Binding
    private lateinit var pagingAdapter: MPagingDataAdapter

    private val viewModel: Test03ViewModel by viewModels<Test03ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = AtyTest03Binding.inflate(layoutInflater)
        setContentView(bind.root)

        init()
    }

    private fun init() {
        val manager: LinearLayoutManager = LinearLayoutManager(this)
        pagingAdapter = MPagingDataAdapter()
        bind.rv.layoutManager = manager
        bind.rv.adapter = pagingAdapter

        lifecycleScope.launch {
            viewModel.loadListBean().collectLatest { pagingData ->
                pagingAdapter.submitData(pagingData)
            }
        }

        // viewModel.listBean.observe(this,object : Observer<ListBean> {
        //    override fun onChanged(value: ListBean) {
        //        // 完整的请求数据
        //        Log.d("TAG", "listBean：${value.pageCount}")
        //    }
        // })

        pagingAdapter.addLoadStateListener { it ->

            // 下拉刷新
            when(it.refresh) {
                is LoadState.NotLoading -> { // 刷新结束
                    if (it.refresh.endOfPaginationReached) { // 没有更多数据了
                        bind.refreshLayout.finishRefreshWithNoMoreData()
                    } else {
                        bind.refreshLayout.finishRefresh()
                    }
                }
                is LoadState.Loading -> {

                }
                is LoadState.Error -> {
                    val state:LoadState.Error = it.refresh as LoadState.Error
                    Log.d("TAG", "err：${state.error.message}")
                    bind.refreshLayout.finishRefresh(false)
                }
            }

            // 上拉加载更多
            when(it.append) {
                is LoadState.NotLoading -> {
                    // 刷新结束
                    if (it.append.endOfPaginationReached) { // 没有更多数据了
                        bind.refreshLayout.finishLoadMoreWithNoMoreData()
                    } else {
                        bind.refreshLayout.finishLoadMore()
                    }
                }
                is LoadState.Loading -> {

                }
                is LoadState.Error -> {
                    val state: LoadState.Error = it.append as LoadState.Error
                    Log.d("TAG", "err：${state.error.message}")
                    bind.refreshLayout.finishLoadMore(false)
                }
            }

        }

        bind.refreshLayout.setOnRefreshListener(object : OnRefreshListener {
            override fun onRefresh(refreshLayout: RefreshLayout) {
                pagingAdapter.refresh()
            }
        })

        bind.search.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                viewModel.isReset = true
                pagingAdapter.refresh()
                bind.rv.scrollToPosition(0)
            }
        })
    }

}