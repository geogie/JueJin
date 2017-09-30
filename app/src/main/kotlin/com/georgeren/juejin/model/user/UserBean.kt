package com.georgeren.juejin.model.user

import java.io.Serializable

/**
 * Created by georgeRen on 2017/9/26.
 * 用户信息
 */
class UserBean : Serializable{
    var role: String? = null
    var username: String? = null
    var selfDescription: String? = null
    var email: String? = null
    var mobilePhoneNumber: String? = null
    var jobTitle: String? = null
    var company: String? = null
    var avatarHd: String? = null
    var avatarLarge: String? = null
    var blogAddress: String? = null
    var deviceType: String? = null
    var editorType: String? = null
    var allowNotification: Boolean = false
    var emailVerified: Boolean = false
    var mobilePhoneVerified: Boolean = false
    var isAuthor: Boolean = false
    var isUnitedAuthor: Boolean = false
    var blacklist: Boolean = false
    var followeesCount: Int = 0
    var followersCount: Int = 0
    var postedPostsCount: Int = 0
    var postedEntriesCount: Int = 0
    var collectedEntriesCount: Int = 0
    var viewedEntriesCount: Int = 0
    var subscribedTagsCount: Int = 0
    var totalCollectionsCount: Int = 0
    var totalViewsCount: Int = 0
    var totalCommentsCount: Int = 0
    var latestLoginedInAt: String? = null
    var createdAt: String? = null
    var updatedAt: String? = null
    var collectionSetCount: Int = 0
    var useLeancloudPwd: Boolean = false
    var objectId: String? = null
    var uid: String? = null

    class CommunityBean: Serializable {
        /**
         * uid : 5885816355
         * nickname : 墨镜猫jacky
         */

        var weibo: WeiboBean? = null
        /**
         * nickname : Android王世昌
         * uid : oDv1Eww5c17raaESRVI8l9M6zsuE
         * expires_at : 1468246472191
         */

        var wechat: WechatBean? = null
        /**
         * nickname : Jacky Wang
         * uid : 17797018
         * expires_at : 1468239341180
         */

        var github: GithubBean? = null

        class WeiboBean : Serializable{
            var uid: String? = null
            var nickname: String? = null
        }

        class WechatBean : Serializable{
            var nickname: String? = null
            var uid: String? = null
            var expires_at: String? = null
        }

        class GithubBean : Serializable{
            var nickname: String? = null
            var uid: String? = null
            var expires_at: String? = null
        }
    }

    class TokenBean: Serializable{
        var token: String? = null
        var user_id: String? = null
        var state: String? = null
    }
}
