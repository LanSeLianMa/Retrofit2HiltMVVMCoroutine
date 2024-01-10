package com.example.retrofit2hiltmvvmcoroutine.common.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit2hiltmvvmcoroutine.R
import com.example.retrofit2hiltmvvmcoroutine.common.bean.DatasBean
import com.example.retrofit2hiltmvvmcoroutine.common.bean.ListBean
import com.example.retrofit2hiltmvvmcoroutine.databinding.LvListItemBinding

class MPagingDataAdapter :
    PagingDataAdapter<DatasBean, MPagingDataAdapter.ViewHolder>(object :
        DiffUtil.ItemCallback<DatasBean>() {
        override fun areItemsTheSame(oldItem: DatasBean, newItem: DatasBean): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: DatasBean, newItem: DatasBean): Boolean {
            return oldItem == newItem
        }
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lv_list_item, parent, false)
        val bind: LvListItemBinding = LvListItemBinding.bind(view)
        return ViewHolder(bind)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bean: DatasBean? = getItem(position)
        holder.bindData(bean)
    }

    class ViewHolder(private val bind: LvListItemBinding) : RecyclerView.ViewHolder(bind.root) {
        fun bindData(datasBean: DatasBean?) {
            bind.bean = datasBean
        }
    }
}