package com.georgeren.juejin.repo

import android.util.Log
import android.widget.Toast
import com.georgeren.juejin.JueJinApp
import com.georgeren.juejin.repo.local.LocalUser
import com.georgeren.net.exception.ApiException
import com.georgeren.net.exception.ExceptionFactory
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by georgeRen on 2017/9/26.
 */
object Composers {
    private val TAG = "Composers"
    /**
     * 错误处理
     */
    fun <T> handleError(): ObservableTransformer<Response<T>, T> {
        return ObservableTransformer { observable ->
            observable.map { responseModel: Response<T>? ->
                if (responseModel != null && responseModel.s == 1 && responseModel.d != null) {
                    responseModel.d!!
                } else {
                    throw ExceptionFactory.ServerException(responseModel?.s ?: -1, responseModel?.m ?: "网络不给力")
                }
            }.onErrorResumeNext { throwable: Throwable ->
                Observable.error(ExceptionFactory.create(throwable))
            }.subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError { e: Throwable ->
                        Log.e(TAG, e.toString())
                        (e as? ApiException)?.apply {
                            // 登录失效
                            if (errorCode.equals("3") || errorMsg.equals("illegal token")) {
                                LocalUser.userToken = null
                                Toast.makeText(JueJinApp.instance, "登录已失效，请重新登录", Toast.LENGTH_SHORT).show()
                            } else {
                                // toast 前， 确保在 UI 线程 !!!
                                Toast.makeText(JueJinApp.instance, this.errorMsg, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
        }
    }

    /**
     * 普通的 ResponseBody 格式(非json), 处理 404 等 errorCode
     */
    fun <T> composeWithoutResponse(): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            observable.onErrorResumeNext { throwable: Throwable ->
                Observable.error(ExceptionFactory.create(throwable))
            }
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    /**
     * 接口数据缓存策略:
     *      1.检查缓存数据, 若有则先显示缓存
     *      2.回调网络数据, 若成功则覆盖先前的缓存数据
     *      3.拉取数据成功, 缓存数据到本地
     *
     * @param key 接口标识, 如: "接口名" + "请求参数(Json格式)"
     */
    fun <T> cache(key: String): ObservableTransformer<in T, out T>? {
        return null
    }
}
