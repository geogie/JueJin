package com.georgeren.juejin.viewmodel

import com.georgeren.databinding.TwoWayListVM
import com.georgeren.databinding.adapters.annotation.HeaderResHolder
import com.georgeren.databinding.adapters.annotation.ResHolder
import com.georgeren.juejin.R
import com.georgeren.juejin.model.notify.NotifyBean
import com.georgeren.juejin.repo.Composers
import com.georgeren.juejin.repo.JueJinApis
import com.georgeren.juejin.view.detail.ArticleActivity
import com.georgeren.net.ApiFactory

/**
 * Created by georgeRen on 2017/9/28.
 * 继承TwoWayListVM
 */
@ResHolder(R.layout.item_notify_list)
@HeaderResHolder(R.layout.header_notify)
class NotifyListVM : TwoWayListVM<NotifyBean>() {
    override val loadTask = { lastItem: NotifyBean? ->
        ApiFactory.getApi(JueJinApis.Notify:: class.java)
                .getUserNotification(lastItem?.createdAtString?: "")
                .compose(Composers.handleError())
    }
    override val onItemClick = ArticleActivity.START_FROM_NOTIFY
    override val headerData = Any()
}