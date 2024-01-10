package com.example.retrofit2hiltmvvmcoroutine.btn01.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit2hiltmvvmcoroutine.btn01.viewmodel.Test01ViewModel
import com.example.retrofit2hiltmvvmcoroutine.common.adapter.MListAdapter
import com.example.retrofit2hiltmvvmcoroutine.common.bean.ListBean
import com.example.retrofit2hiltmvvmcoroutine.common.net.response.ResponseData
import com.example.retrofit2hiltmvvmcoroutine.databinding.AtyTest01Binding
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import java.util.Random

@AndroidEntryPoint
class Test01Activity : AppCompatActivity() {

    private lateinit var bind: AtyTest01Binding
    private lateinit var adapter: MListAdapter
    private var responseCall: Call<ResponseData<ListBean>>? = null

    private val viewModel: Test01ViewModel by viewModels<Test01ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = AtyTest01Binding.inflate(layoutInflater)
        setContentView(bind.root)

        initData()
        initRv()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (responseCall != null && !responseCall?.isCanceled!!) {
            responseCall!!.cancel()
        }
    }

    /**
     * 搜索 列表数据
     */
    private fun searchData() {
        val random = Random()
        val result = random.nextInt(10)

        val map: MutableMap<String, String> = mutableMapOf<String, String>()
        map["k"] = result.toString()

        responseCall = viewModel.searchListBean(map) // 请求接口
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