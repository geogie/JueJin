package com.georgeren.juejin.view.home

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.georgeren.base_ui.BaseFragment
import com.georgeren.juejin.R
import com.georgeren.juejin.databinding.GTwoWayListBinding
import com.georgeren.juejin.viewmodel.HomeListVM

/**
 * Created by georgeRen on 2017/9/27.
 * 9大类[Android, 前端, iOS, 产品, 设计, 工具资源, 阅读, 后端, 人工智能] --》HomeCategoryListFragment
 *
 */
class HomeCategoryListFragment (val categoryId: String = "") : BaseFragment(){
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<GTwoWayListBinding>(inflater, R.layout.g_two_way_list, container, false).apply {
            Log.d("HomeCategoryListFragment-", "categoryId:" + categoryId)
            this.listVM = HomeListVM(categoryId)
        }.root
    }
}
