package com.example.retrofit2hiltmvvmcoroutine.btn02.ui.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit2hiltmvvmcoroutine.btn02.viewmodel.Test02ViewModel
import com.example.retrofit2hiltmvvmcoroutine.common.adapter.MListAdapter
import com.example.retrofit2hiltmvvmcoroutine.common.bean.ListBean
import com.example.retrofit2hiltmvvmcoroutine.databinding.AtyTest02Binding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Random

@AndroidEntryPoint
class Test02Activity : AppCompatActivity() {

    private lateinit var bind: AtyTest02Binding
    private lateinit var adapter: MListAdapter

    private val viewModel: Test02ViewModel by viewModels<Test02ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = AtyTest02Binding.inflate(layoutInflater)
        setContentView(bind.root)

        initData()
        initRv()
    }

    /**
     * 搜索 列表数据
     */
    private fun searchData() {
        val random = Random()
        val result = random.nextInt(10)

        val map: MutableMap<String, String> = mutableMapOf<String, String>()
        map["k"] = result.toString()

        viewModel.searchListBean(map) // 请求接口
    }

    /**
     * 初始化加载列表
     */
    private fun initList() {
        viewModel.loadListBean(0) // 请求接口
    }

    /**
     * 初始化加载 列表数据
     */
    private fun initData() {
        viewModel.listBean.observe(this,object : Observer<ListBean> {
            override fun onChanged(value: ListBean) {
                // Log.d("TAG","listBen：$value")
                adapter.setListBean(value)
                adapter.notifyDataSetChanged()
            }
        })
        initList()
    }

    /**
     * 初始化 布局
     */
    private fun initRv() {
        val manager: LinearLayoutManager = LinearLayoutManager(this)
        adapter = MListAdapter()
        bind.rv.layoutManager = manager
        bind.rv.adapter = adapter

        bind.search.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                searchData()
            }
        })
    }

}