package com.georgeren.net.widget

import com.georgeren.net.util.HttpsUtil
import com.socks.library.KLog
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * Created by georgeRen on 2017/9/26.
 * object：单例，不能有构造，第一次使用的时候被创建。
 * 链接、读、写 分别20s超时
 */
object OkHttpFactory {
    private var CONNECT_TIMEOUT_SECONDS = 20L
    private var READ_TIMEOUT_SECONDS = 20L
    private var WRITE_TIMEOUT_SECONDS = 20L

    val client: OkHttpClient by lazy { create() }

    /**
     * enableLog:是否添加log拦截器
     * 初始化OkHttpClient：ssl、超时、拦截器
     */
    fun create(customInterceptor: Interceptor? = null, enableLog: Boolean = true): OkHttpClient {
        val sslParams = HttpsUtil.getSslSocketFactory(null, null, null) // ssl处理

        // log拦截器
        val loggingInterceptor = HttpLoggingInterceptor{ chain, msg ->
            KLog.json("okhttp-${chain.request().url().uri().path}", msg)
        }.apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)// ssl参数
                .readTimeout(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS) // 超时时间
                .writeTimeout(WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .apply {
                    if (customInterceptor != null) // 添加自定义拦截器
                        this.addInterceptor(customInterceptor)
                }
                .apply {
                    if (enableLog) // 是否添加log拦截器
                        this.addInterceptor(loggingInterceptor)
                }
                .build()
    }
}