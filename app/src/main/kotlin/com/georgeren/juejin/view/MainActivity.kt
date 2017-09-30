package com.georgeren.juejin.view

import android.databinding.DataBindingUtil
import android.databinding.ObservableBoolean
import android.os.Bundle
import com.georgeren.base_ui.BaseActivity
import com.georgeren.juejin.R
import com.georgeren.juejin.databinding.ActivityMainBinding
import com.georgeren.juejin.view.home.ExploreFragment
import com.georgeren.juejin.view.home.HomeFragment
import com.georgeren.juejin.view.home.NotifyFragment
import com.georgeren.juejin.view.home.ProfileFragment
import com.georgeren.juejin.viewmodel.TabPagesVM
import com.georgeren.juejin.viewmodel.TabVM

/**
 * Created by georgeRen on 2017/9/26.
 * 主界面
 */
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            // 顶部和中间部分 fragment
            this.pages = TabPagesVM(listOf(
                    HomeFragment(), // 主页
                    ExploreFragment(), // 搜索
                    NotifyFragment(), // 通知
                    ProfileFragment() // 我的
            ))

            // 底部导航栏
            this.tab = TabVM().apply{
                this.data.addAll(listOf(
                        // 是否选中 没选中图标id 选中图标id
                        TabVM.Item(ObservableBoolean(true), R.drawable.tab_home_normal, R.drawable.tab_home),
                        TabVM.Item(ObservableBoolean(false), R.drawable.tab_explore_normal, R.drawable.tab_explore),
                        TabVM.Item(ObservableBoolean(false), R.drawable.tab_notifications_normal, R.drawable.tab_notifications),
                        TabVM.Item(ObservableBoolean(false), R.drawable.tab_profile_normal, R.drawable.tab_profile)
                ))
            }
        }
    }
}