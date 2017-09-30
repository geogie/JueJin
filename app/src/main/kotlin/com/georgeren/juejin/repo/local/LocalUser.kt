package com.georgeren.juejin.repo.local

import com.georgeren.juejin.JueJinApp
import com.georgeren.juejin.model.user.UserBean
import com.georgeren.util.PreferencesUtil

/**
 * Created by georgeRen on 2017/9/26.
 */
object LocalUser {
    val appContext by lazy { JueJinApp.instance }

    val isLogin: Boolean
        get() = userToken?.token != null

    //    var user: UserBean? = null
    var userToken: UserBean.TokenBean? = null
        set(value) {
            field = value
            if (field != null) {
                // 保存 token 到本地
                PreferencesUtil.getInstance(appContext)
                        .putEntity("userToken", field!!)
            } else {
                // 清除本地 token
                PreferencesUtil.getInstance(appContext)
                        .remove("userToken")
            }
        }
        get() {
            return if (field != null) field else
                PreferencesUtil.getInstance(appContext)
                        .getEntity("userToken", UserBean.TokenBean::class.java)
        }

}