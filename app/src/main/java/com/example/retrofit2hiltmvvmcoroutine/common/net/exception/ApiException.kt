package com.example.retrofit2hiltmvvmcoroutine.common.net.exception

import com.google.gson.JsonParseException
import org.json.JSONException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

class ApiException(private val code: Int, private val displayMessage: String?) : Exception() {

    companion object {
        const val UNKNOWN: Int = 1000 // 未知错误
        const val PARSE_ERROR: Int = 1001 // 解析错误
        const val NETWORK_ERROR: Int = 1002 // 网络错误 / 连接错误

        fun handleException(e: Throwable): ApiException {
            var ex: ApiException = when (e) {
                is JsonParseException,
                is JSONException,
                is ParseException -> {
                    ApiException(PARSE_ERROR, e.message)
                }

                is ConnectException -> {
                    ApiException(NETWORK_ERROR, e.message)
                }

                is UnknownHostException,
                is SocketTimeoutException -> {
                    ApiException(NETWORK_ERROR, e.message)
                }

                else -> {
                    ApiException(UNKNOWN, e.message)
                }
            }
            return ex
        }
    }

}