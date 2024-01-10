package com.example.retrofit2hiltmvvmcoroutine.common.net

import android.util.Log
import com.example.retrofit2hiltmvvmcoroutine.btn01.net.ApiService01
import com.example.retrofit2hiltmvvmcoroutine.btn02.net.ApiService02
import com.example.retrofit2hiltmvvmcoroutine.btn03.net.ApiService03
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RequestManager {

    // ========================= 每个模块的Api =========================

    @Singleton
    @Provides
    fun ApiService03(retrofit: Retrofit): ApiService03 {
        return retrofit.create(ApiService03::class.java)
    }

    @Singleton
    @Provides
    fun test02ApiService(retrofit: Retrofit): ApiService02 {
        return retrofit.create(ApiService02::class.java)
    }

    /**
     * @param retrofit 这个retrofit 就是 getRetrofitInstance()，自动扫描赋值
     * @return
     */
    @Singleton
    @Provides
    fun test01ApiService(retrofit: Retrofit): ApiService01 {
        return retrofit.create(ApiService01::class.java)
    }

    // ================================================================


    private val baseUrl = "https://www.wanandroid.com"

    private val CONNECTION_TIME_OUT = 10L // 连接超时时间

    private val WRITE_TIME_OUT = 10L // 写入超时时间

    private val READ_TIME_OUT = 10L // 读取超时时间

    @Singleton
    @Provides
    fun getOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.i("TAG", message)
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
            .build()
    }

    /**
     * @param client 这个client 就是 getOkHttpClient()，自动扫描赋值
     * @return
     */
    @Singleton
    @Provides
    fun getRetrofitInstance(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client) // 可以不设置，Retrofit会自动生成一个client
            // 增加返回值为String的支持
            .addConverterFactory(ScalarsConverterFactory.create())
            // 增加返回值为Gson的支持(以实体类返回)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}