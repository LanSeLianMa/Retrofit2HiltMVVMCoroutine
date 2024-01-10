package com.example.retrofit2hiltmvvmcoroutine.common.net.baseurlfactory

import android.util.Log
import okhttp3.Call
import okhttp3.HttpUrl
import okhttp3.Request

/**
 * 替换 baseUrl
 */
abstract class CallFactoryProxy(private val delegate: Call.Factory) : Call.Factory {

    companion object {
        private const val NAME_BASE_URL: String = "BaseUrlName"
    }

    override fun newCall(request: Request): Call {
        val baseUrlName = request.header(CallFactoryProxy.NAME_BASE_URL)
        if (baseUrlName != null) {
            val newHttpUrl: HttpUrl? = getNewUrl(baseUrlName, request)
            if ((newHttpUrl != null)) {
                val newRequest = request.newBuilder().url(newHttpUrl).build()
                return delegate.newCall(newRequest)
            } else {
                Log.d("TAG", "getNewUrl() return null when baseUrlName==$baseUrlName")
            }
        }
        return delegate.newCall(request)
    }

    protected abstract fun getNewUrl(baseUrlName: String?, request: Request?): HttpUrl?

}