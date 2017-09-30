package com.georgeren.juejin.viewmodel

import android.databinding.ObservableField
import android.support.v7.widget.RecyclerView
import android.view.View
import com.georgeren.databinding.TwoWayListVM
import com.georgeren.databinding.adapters.annotation.HeaderResHolder
import com.georgeren.databinding.adapters.annotation.ResHolder
import com.georgeren.juejin.R
import com.georgeren.juejin.model.article.ArticleBean
import com.georgeren.juejin.model.comment.CommentListBean
import com.georgeren.juejin.repo.Composers
import com.georgeren.juejin.repo.JueJinApis
import com.georgeren.juejin.view.detail.ArticleActivity
import com.georgeren.net.ApiFactory

/**
 * Created by georgeRen on 2017/9/27.
 */
@ResHolder(R.layout.item_article_comment_list)
@HeaderResHolder(R.layout.header_article)
class ArticleVM(val rv: RecyclerView) : TwoWayListVM<CommentListBean.Item>() {
    val article = ObservableField<ArticleBean>(ArticleBean("", ""))

    override val loadTask = { lastItem: CommentListBean.Item?->
        ApiFactory.getApi(JueJinApis.Comment::class.java)
                .getComments(article.get().objectId?: "", lastItem?.createdAt?: "")
                .compose(Composers.handleError())
                .map { it.comments }
    }

    override val headerData = HeaderVM()

    @ResHolder(R.layout.header_item_home)
    class HeaderVM : TwoWayListVM<ArticleBean>() {
        val html = ObservableField<String>("")
        val article = ObservableField<ArticleBean>(ArticleBean("", ""))

        override val loadTask = { lastItem: ArticleBean? ->
            ApiFactory.getApi(JueJinApis:: class.java)
                    .getRelatedEntry(entryId = article.get()?.objectId?: "")
                    .compose(Composers.handleError())
                    .map{ it.entrylist?: emptyList() }
        }

        override val onItemClick = ArticleActivity.START
    }

    val scrollToComment = View.OnClickListener {
        val header = rv.layoutManager.findViewByPosition(0)
        header?.apply{
            if(this.height > 0)
                rv.smoothScrollBy(0, this.height - rv.computeVerticalScrollOffset())
        }
    }
}