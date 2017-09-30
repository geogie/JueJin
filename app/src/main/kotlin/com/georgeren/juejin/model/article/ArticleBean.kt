package com.georgeren.juejin.model.article

import com.georgeren.juejin.model.user.UserBean
import java.io.Serializable

/**
 * Created by georgeRen on 2017/9/27.
 */
data class ArticleBean(var content: String? = null,
                       var title: String? = null): Serializable {
    var collectionCount: Int = 0
    var commentsCount: Int = 0
    var gfw: Boolean = false
    var entryView: String? = null
    var subscribersCount: Int = 0
    var ngxCachedTime: Int = 0
    /**
     * ngxCachedTime : 1504630959
     * ngxCached : true
     * title : 开源
     * id : 5597a3cae4b08a686ce5d0fb
     */
    var tags: List<TagsBean>? = null
    var updatedAt: String? = null
    var rankIndex: Double = 0.toDouble()
    var hot: Boolean = false
    var objectId: String? = null
    var originalUrl: String? = null
    var buildTime: Double = 0.toDouble()
    var createdAt: String? = null
    /**
     * community : {"weibo":{"uid":"5885816355","nickname":"墨镜猫jacky"},"wechat":{"nickname":"Android王世昌","uid":"oDv1Eww5c17raaESRVI8l9M6zsuE","expires_at":"1468246472191"},"github":{"nickname":"Jacky Wang","uid":"17797018","expires_at":"1468239341180"}}
     * collectedEntriesCount : 3
     * company : 映客
     * followersCount : 916
     * followeesCount : 4
     * role : editor
     * postedPostsCount : 5
     * isAuthor : true
     * postedEntriesCount : 16
     * totalCommentsCount : 184
     * ngxCachedTime : 1504630984
     * ngxCached : true
     * viewedEntriesCount : 1713
     * jobTitle : Android架构师
     * subscribedTagsCount : 42
     * totalCollectionsCount : 4575
     * username : 墨镜猫
     * avatarLarge : https://dn-mhke0kuv.qbox.me/4ERoRptV8ySFHwtZ06bHGY7twEfJ0VJ1cVMU0tml
     * objectId : 562dc7cc60b20fc9817962a2
     */
    var user: UserBean? = null
    var author: String? = null
    var screenshot: String? = null
    var original: Boolean = false
    var hotIndex: Double = 0.toDouble()

    var lastCommentTime: String? = null
    var type: String? = null
    var english: Boolean = false
    /**
     * ngxCached : true
     * title : article
     * id : 5562b428e4b00c57d9b94b9d
     * name : 阅读
     * ngxCachedTime : 1504630889
     */
    var category: CategoryBean? = null
    var viewsCount: Int = 0
    var isCollected: Boolean = false

    class CategoryBean: Serializable {
        var isNgxCached: Boolean = false
        var title: String? = null
        var id: String? = null
        var name: String? = null
        var ngxCachedTime: Int = 0
    }

    class TagsBean: Serializable {
        var ngxCachedTime: Int = 0
        var isNgxCached: Boolean = false
        var title: String? = null
        var id: String? = null
    }
}
