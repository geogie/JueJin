package com.georgeren.juejin.viewmodel

import android.databinding.ObservableArrayList
import android.support.v4.app.Fragment
import com.georgeren.juejin.view.home.HomeListFragment

/**
 * Created by georgeRen on 2017/9/27.
 */
class HomePagesVM {
    var tabList = ObservableArrayList<String>().apply {
        this.add("首页")
    }
    var pageList = ObservableArrayList<Fragment>().apply {
        this.add(HomeListFragment())
    }
}