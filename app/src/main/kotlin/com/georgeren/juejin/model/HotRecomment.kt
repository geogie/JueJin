package com.georgeren.juejin.model

import com.georgeren.juejin.BannerListBean
import com.georgeren.juejin.model.article.ArticleListBean
import java.io.Serializable

/**
 * Created by georgeRen on 2017/9/27.
 */
class HotRecomment : Serializable {
    var banner: BannerListBean? = null
    var entry: ArticleListBean? = null

}