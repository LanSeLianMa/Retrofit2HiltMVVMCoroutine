package com.example.retrofit2hiltmvvmcoroutine.common

import android.app.Application
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    companion object {
        init {
            ClassicsHeader.REFRESH_HEADER_REFRESHING = "正在刷新..."
            ClassicsHeader.REFRESH_HEADER_LOADING = "正在加载..."
            ClassicsHeader.REFRESH_HEADER_RELEASE = "释放立即刷新"
            ClassicsHeader.REFRESH_HEADER_FINISH = "刷新完成"
            ClassicsHeader.REFRESH_HEADER_FAILED = "刷新失败"
            ClassicsHeader.REFRESH_HEADER_UPDATE = "上次更新 M-d HH:mm"

            ClassicsFooter.REFRESH_FOOTER_PULLING = "上拉加载更多"
            ClassicsFooter.REFRESH_FOOTER_RELEASE = "释放立即加载"
            ClassicsFooter.REFRESH_FOOTER_REFRESHING = "正在刷新..."
            ClassicsFooter.REFRESH_FOOTER_LOADING = "正在加载..."
            ClassicsFooter.REFRESH_FOOTER_FINISH = "加载完成"
            ClassicsFooter.REFRESH_FOOTER_FAILED = "加载失败"
            ClassicsFooter.REFRESH_FOOTER_NOTHING = "没有更多数据了"

            // 设置全局的Header构建器
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
                ClassicsHeader(context)
            }

            //设置全局的Footer构建器
            SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout -> //指定为经典Footer，默认是 BallPulseFooter
                ClassicsFooter(context).setDrawableSize(20f)
            }
        }
    }

}