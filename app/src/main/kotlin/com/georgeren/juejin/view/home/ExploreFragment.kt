package com.georgeren.juejin.view.home

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.georgeren.base_ui.BaseFragment
import com.georgeren.juejin.R
import com.georgeren.juejin.databinding.FragmentExploreBinding
import com.georgeren.juejin.viewmodel.ExploreListVM

/**
 * Created by georgeRen on 2017/9/27.
 * 搜索
 */
class ExploreFragment : BaseFragment(){
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<FragmentExploreBinding>(inflater, R.layout.fragment_explore, container, false).apply{
            this.listVM = ExploreListVM()
        }.root
    }
}