package com.georgeren.juejin.view.home

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.georgeren.base_ui.BaseFragment
import com.georgeren.juejin.R
import com.georgeren.juejin.databinding.FragmentHomeBinding
import com.georgeren.juejin.repo.Composers
import com.georgeren.juejin.repo.JueJinApis
import com.georgeren.juejin.viewmodel.HomePagesVM
import com.georgeren.net.ApiFactory

/**
 * Created by georgeRen on 2017/9/27.
 * 主页：首页--》HomeListFragment
 *
 * 9大类[Android, 前端, iOS, 产品, 设计, 工具资源, 阅读, 后端, 人工智能] --》HomeCategoryListFragment
 *
5562b410e4b00c57d9b94a92, Android
5562b415e4b00c57d9b94ac8, 前端
5562b405e4b00c57d9b94a41, iOS
569cbe0460b23e90721dff38, 产品
5562b41de4b00c57d9b94b0f, 设计
5562b422e4b00c57d9b94b53, 工具资源
5562b428e4b00c57d9b94b9d, 阅读
5562b419e4b00c57d9b94ae2, 后端
57be7c18128fe1005fa902de  人工智能
 *
 */
class HomeFragment : BaseFragment() {
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<FragmentHomeBinding>(inflater, R.layout.fragment_home, container, false).apply {
            // 初始化
            binding = this // 初始化binding
            this.pages = HomePagesVM() // 初始化VM
        }.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 从网络获取数据,填充tab数据
        ApiFactory.getApi(JueJinApis.Tags::class.java)
                .getCategories() // 请求地址
                .compose(Composers.handleError())// 错误处理
                .subscribe({
                    // 请求数据结果
                    val list = it.categoryList
                    binding.pages.tabList.apply {
                        // 主页：顶部tab。首页、ANDROID、前端、IOS、产品、等。
                        Log.d("HomeFragment-", "name:" + list.map { it.name })

                        this.clear()
                        this.add("首页") // 默认添加
                        this.addAll(list.map { it.name })// 网络获取后的：ANDROID、前端、IOS、产品、等。
                    }

                    binding.pages.pageList.apply {
                        // 主页：内容page(Fragment)。
                        Log.d("HomeFragment-", "name:" + list.map { it.id })
                        this.clear()
                        this.add(HomeListFragment())
                        this.addAll(list.map { HomeCategoryListFragment(it.id ?: "") })
                    }
                }, {})

    }

}