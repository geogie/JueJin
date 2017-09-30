package com.georgeren.net

import com.georgeren.net.widget.OkHttpFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by georgeRen on 2017/9/26.
 * object：单例，不能有构造，第一次使用的时候被创建。
 * 封装网络请求factory
 *
 */
object ApiFactory {
    /**
     * baseUrl of each Api
     * 定义注解
     */
    @Target(AnnotationTarget.CLASS) // 可以注解的元素类型：类
    @Retention(AnnotationRetention.RUNTIME)// 运行时获取注解的相关信息
    annotation class BaseUrl(val value: String) // 自定义注解 @ApiFactory.BaseUrl(url)

    // 可被外部访问
    var okhttpClient = OkHttpFactory.client

    // 私有，不可变变量 mRetrofitBuilder 用懒加载的方式初始化
    private val mRetrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
                .client(okhttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }

    /**
     * 获取api
     * 可被外部访问
     */
    fun <T> getApi(clazz: Class<T>): T {
        val baseUrl = clazz.getAnnotation(BaseUrl::class.java).value // 获取类指定的注解，获取值
        if (baseUrl.isEmpty())
            throw IllegalArgumentException("@BaseUrl of ${clazz.simpleName} is empty")
        return mRetrofitBuilder.baseUrl(baseUrl).build().create(clazz)
    }
}