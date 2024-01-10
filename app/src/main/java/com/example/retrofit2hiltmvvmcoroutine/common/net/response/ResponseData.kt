package com.example.retrofit2hiltmvvmcoroutine.common.net.response

/**
 * 响应
 * @param <T> 显示在视图上的 Bean
 */
data class ResponseData<T>(
    val data: T,
    val errorCode: Int,
    val errorMsg: String
)