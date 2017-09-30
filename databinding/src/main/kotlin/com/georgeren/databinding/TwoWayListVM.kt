package com.georgeren.databinding

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Created by georgeRen on 2017/9/27.
 * open：显示的定义一个父类
 * 刷新接口
 * 加载更多、刷新
 * 加载完成、加载出错
 *
 */
open class TwoWayListVM<T> : ListVM<T>() {
    /**
     * open:可被重写
     * val:只能初始化一次
     *
     * lastItem: T          视图列表里的最后一条数据，无则为 null
     * 分两种情况:
     *      onRefresh(): lastItem = null, 直接拉取数据
     *      onLoadMore(): lastItem = 最后一条, 由lastItem作为参数，加载更多
     */
    open val loadTask: ((T?) -> Observable<List<T>>)? = null

    /**
     * originData: List<T>          当前视图列表里的数据
     * refreshable: Refreshable?    触发 loadTask 的下拉刷新控件
     * 分两种情况:
     *      onRefresh(): lastItem = null,       newData = netData
     *      onLoadMore(): lastItem = 最后一条,   netData = newData + originData
     */
    open val loadTaskObserver: ((List<T>, Refreshable?) -> Observer<List<T>>)? = { originData: List<T>, refreshable: Refreshable? ->
        object : Observer<List<T>> {
            override fun onNext(netData: List<T>) {
                val lastItem = if (!originData.isEmpty()) originData[originData.size - 1] else null
                val isLoadMore = lastItem != null

                val newData = ArrayList<T>()
                if (isLoadMore) {// 加载更多：先添加原始数据，再添加新数据。不是加载更多，就只添加新数据
                    newData.addAll(originData)
                }
                newData.addAll(netData)

                data.clear()
                data.addAll(newData)
            }

            override fun onComplete() {
                refreshable?.endRefresh()
            }

            override fun onError(e: Throwable) {}

            override fun onSubscribe(d: Disposable) {}
        }
    }

    /**
     * 刷新接口
     */
    interface Refreshable {
        fun setOnRefresh(callback: CallBack?)
        fun endRefresh()

        interface CallBack {
            fun onRefresh()
            fun onLoadMore()
        }
    }
}