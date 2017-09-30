package com.georgeren.juejin.widget

import android.content.Context
import android.util.AttributeSet
import com.georgeren.databinding.TwoWayListVM
import com.liaoinstan.springview.container.DefaultFooter
import com.liaoinstan.springview.container.DefaultHeader
import com.liaoinstan.springview.widget.SpringView

/**
 * Created by georgeRen on 2017/9/27.
 * 继承接口：TwoWayListVM.Refreshable
 * 父类：SpringView
 * 上拉刷新，下拉加载
 */
class MySpringView(context: Context, attrs: AttributeSet) : SpringView(context, attrs), TwoWayListVM.Refreshable {
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        this.header = DefaultHeader(context)
        this.footer = DefaultFooter(context)
    }

    override fun setOnRefresh(callback: TwoWayListVM.Refreshable.CallBack?) {
        this.setListener(object : SpringView.OnFreshListener {
            /**
             * 刷新
             */
            override fun onRefresh() {
                callback?.onRefresh()
            }
            /**
             * 加载更多
             */
            override fun onLoadmore() {
                callback?.onLoadMore()
            }
        })
    }

    /**
     * 结束刷新
     */
    override fun endRefresh() {
        this.onFinishFreshAndLoad()
    }
}
