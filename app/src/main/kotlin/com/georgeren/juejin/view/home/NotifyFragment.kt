package com.georgeren.juejin.view.home

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.georgeren.base_ui.BaseFragment
import com.georgeren.juejin.R
import com.georgeren.juejin.databinding.FragmentNotifyBinding
import com.georgeren.juejin.viewmodel.NotifyListVM

/**
 * Created by georgeRen on 2017/9/27.
 * 通知
 */
class NotifyFragment : BaseFragment(){
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<FragmentNotifyBinding>(inflater, R.layout.fragment_notify, container, false).apply{
            this.listVM = NotifyListVM()
        }.root
    }
}