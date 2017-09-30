package com.georgeren.juejin.viewmodel

import android.support.annotation.LayoutRes
import com.georgeren.databinding.ListVM
import com.georgeren.databinding.TwoWayListVM
import com.georgeren.databinding.adapters.annotation.HeaderResHolder
import com.georgeren.databinding.adapters.annotation.ResHolder
import com.georgeren.juejin.BannerListBean
import com.georgeren.juejin.R
import com.georgeren.juejin.model.article.ArticleBean
import com.georgeren.juejin.repo.Composers
import com.georgeren.juejin.repo.JueJinApis
import com.georgeren.juejin.view.detail.ArticleActivity
import com.georgeren.net.ApiFactory

/**
 * Created by georgeRen on 2017/9/27.
 */
@ResHolder(R.layout.item_explore_list)
@HeaderResHolder(R.layout.header_explore)
class ExploreListVM : TwoWayListVM<ArticleBean>() {
    override val loadTask = { lastItem: ArticleBean? ->
        ApiFactory.getApi(JueJinApis::class.java)
                .getEntryByRank(before = lastItem?.rankIndex?.toString() ?: "")
                .compose(Composers.handleError())
                .map { it.entrylist ?: emptyList() } // 需要去重？？
    }

    override val onItemClick = ArticleActivity.START

    override val headerData = HeaderVM()

    class HeaderVM {
        val banner = Banner()
        val entrys = Entrys().apply {
            this.data.addAll(listOf(
                    Entrys.Item(R.drawable.explore_hot, "本周热门"),
                    Entrys.Item(R.drawable.explore_collection_set, "收藏集"),
                    Entrys.Item(R.drawable.explore_offline, "线下活动"),
                    Entrys.Item(R.drawable.explore_post, "专栏")
            ))
        }
        val topics = Topics()

        // banner
        @ResHolder(R.layout.header_item_explore_banner)
        class Banner : TwoWayListVM<BannerListBean.Item>() {
            override val loadTask = { lastItem: BannerListBean.Item? ->
                ApiFactory.getApi(JueJinApis.BannerStorage::class.java)
                        .getBanner(position = "explore")
                        .compose(Composers.handleError())
                        .map { it.banner ?: emptyList() }
            }

            override val onItemClick = ArticleActivity.START_FROM_BANNER
        }

        // 4 个入口
        @ResHolder(R.layout.header_item_explore_entry)
        class Entrys : ListVM<Entrys.Item>() {

            class Item(@LayoutRes val icon: Int, val title: String)
        }

        // 沸点
        @ResHolder(R.layout.header_item_explore_topic)
        class Topics : TwoWayListVM<ArticleBean>() {
            override val loadTask = { lastItem: ArticleBean? ->
                ApiFactory.getApi(JueJinApis::class.java)
                        .getEntryByTimeLine(categoryId = "all", type = "vote", limit = "5")
                        .compose(Composers.handleError())
                        .map { it.entrylist ?: emptyList() }
            }

            override val onItemClick = ArticleActivity.START
        }
    }
}