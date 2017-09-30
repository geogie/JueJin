package com.georgeren.juejin.viewmodel

import android.content.Intent
import android.databinding.ObservableField
import android.support.annotation.DrawableRes
import android.text.TextUtils
import android.view.View
import com.georgeren.databinding.ListVM
import com.georgeren.databinding.adapters.annotation.ResHolder
import com.georgeren.juejin.R
import com.georgeren.juejin.model.user.UserBean
import com.georgeren.juejin.view.profile.login.LoginActivity

/**
 * Created by georgeRen on 2017/9/27.
 * 个人的
 * item：
 */
@ResHolder(R.layout.item_profile_list)
class ProfileVM : ListVM<ProfileVM.Item>() {
    // login area
    var user = ObservableField<UserBean>(null)

    val toLogin = View.OnClickListener {
        // 点击 登录/注册
        it.context.startActivity(Intent(it.context, LoginActivity::class.java))
    }

    fun parseJobAndCompany(user: UserBean?): String {
        return (user?.jobTitle?.takeIf { !TextUtils.isEmpty(it) } ?: "添加职位") + " @ " +
                (user?.company?.takeIf { !TextUtils.isEmpty(it) } ?: "添加公司")
    }

    fun getDefaultList() = listOf(
            Item(R.drawable.profile_i_like, "我喜欢的", user.get()),
            Item(R.drawable.profile_collection_set, "收藏集", user.get()),
            Item(R.drawable.profile_view, "阅读过的文章", user.get()),
            Item(R.drawable.profile_tag, "标签管理", user.get()),
            Item(R.drawable.profile_feed_back, "意见反馈", user.get()),
            Item(R.drawable.profile_settings, "设置", user.get())
    )

    class Item(@DrawableRes val iconRes: Int = 0,
               val name: String = "",
               val user: UserBean? = null) {

        fun parseItemCount(user: UserBean?) = when (iconRes) {
            R.drawable.profile_i_like -> "" + (user?.collectedEntriesCount ?: 0) + "篇"
            R.drawable.profile_collection_set -> "" + (user?.collectionSetCount ?: 0) + "个"
            R.drawable.profile_view -> "" + (user?.viewedEntriesCount ?: 0) + "篇"
            R.drawable.profile_tag -> "" + (user?.subscribedTagsCount ?: 0) + "个"
            else -> ""
        }
    }
}
