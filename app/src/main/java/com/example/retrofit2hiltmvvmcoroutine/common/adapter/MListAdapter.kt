package com.example.retrofit2hiltmvvmcoroutine.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit2hiltmvvmcoroutine.R
import com.example.retrofit2hiltmvvmcoroutine.common.bean.DatasBean
import com.example.retrofit2hiltmvvmcoroutine.common.bean.ListBean
import com.example.retrofit2hiltmvvmcoroutine.databinding.LvListItemBinding

class MListAdapter : RecyclerView.Adapter<MListAdapter.ViewHolder>() {

    private var listBean: ListBean? = null

    fun setListBean(listBean: ListBean?) {
        this.listBean = listBean
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lv_list_item, parent, false)
        val bind = LvListItemBinding.bind(view)
        return ViewHolder(bind)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(listBean?.datas?.get(position))
    }

    override fun getItemCount(): Int {
        return if (listBean?.datas?.isNotEmpty() == true) {
            listBean?.datas?.size ?: 0
        } else 0
    }

    class ViewHolder(private val bind: LvListItemBinding) : RecyclerView.ViewHolder(bind.root) {

        fun bindData(datasBean: DatasBean?) {
            bind.bean = datasBean
        }

    }
}