package com.georgeren.juejin.view.home

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.georgeren.base_ui.BaseFragment
import com.georgeren.juejin.R
import com.georgeren.juejin.databinding.GTwoWayListBinding
import com.georgeren.juejin.viewmodel.HomeListVM

/**
 * Created by georgeRen on 2017/9/27.
 * 首页
 */
class HomeListFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<GTwoWayListBinding>(inflater, R.layout.g_two_way_list, container, false).apply {
            this.listVM = HomeListVM()
        }.root
    }
}