package com.georgeren.juejin.viewmodel

import android.text.TextUtils
import android.util.Log
import com.georgeren.databinding.TwoWayListVM
import com.georgeren.databinding.adapters.annotation.HeaderResHolder
import com.georgeren.databinding.adapters.annotation.ResHolder
import com.georgeren.juejin.R
import com.georgeren.juejin.model.article.ArticleBean
import com.georgeren.juejin.repo.Composers
import com.georgeren.juejin.repo.JueJinApis
import com.georgeren.juejin.view.detail.ArticleActivity
import com.georgeren.net.ApiFactory

/**
 * Created by georgeRen on 2017/9/27.
 * [首页，Android, 前端, iOS, 产品, 设计, 工具资源, 阅读, 后端, 人工智能]共享item
 * item类型：
 * item_home_list: post/vote、article
 */
@ResHolder(R.layout.item_home_list)
@HeaderResHolder(R.layout.header_home)
class HomeListVM(val categoryId: String = "") : TwoWayListVM<ArticleBean>() {
    override val loadTask = { lastItem: ArticleBean? ->
        ApiFactory.getApi(JueJinApis::class.java)
                .getEntryByTimeLine(categoryId = categoryId, before = lastItem?.createdAt ?: "")
                .compose(Composers.handleError())
                .map { it.entrylist ?: emptyList() }
    }

    override val onItemClick = ArticleActivity.START

    override val headerData = HeaderVM(categoryId)

    @ResHolder(R.layout.header_item_home)
    class HeaderVM(val categoryId: String = "") : TwoWayListVM<ArticleBean>() {
        override val loadTask = { lastItem: ArticleBean? ->
            if (TextUtils.isEmpty(categoryId)) {
                ApiFactory.getApi(JueJinApis::class.java)
                        .getEntryByHotRecomment()
                        .compose(Composers.handleError())
                        .map { it.entry?.entrylist ?: emptyList() }
                        .map { if (it.size > 3) it.subList(0, 3) else it }
            } else {
                ApiFactory.getApi(JueJinApis::class.java)
                        .getEntryByPeriod(categoryId = categoryId)
                        .compose(Composers.handleError())
                        .map { it.entrylist ?: emptyList() }
                        .map { if (it.size > 3) it.subList(0, 3) else it }
            }
        }

        override val onItemClick = ArticleActivity.START
    }
}