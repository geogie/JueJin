package com.georgeren.juejin.viewmodel

import android.app.Activity
import android.content.Intent
import android.databinding.ObservableField
import android.net.Uri
import android.view.View
import com.blankj.utilcode.util.RegexUtils
import com.georgeren.juejin.repo.Composers
import com.georgeren.juejin.repo.JueJinApis
import com.georgeren.juejin.repo.local.LocalUser
import com.georgeren.juejin.view.profile.login.RegisterActivity
import com.georgeren.net.ApiFactory

/**
 * Created by georgeRen on 2017/9/28.
 */
class LoginVM {
    companion object {
        fun getLoginTask(userName: String, passWord: String) =
                ApiFactory.getApi(JueJinApis.User::class.java)
                        .login(login_type = if(RegexUtils.isMobileSimple(userName)) "tel" else "email",
                                user = userName,
                                psd = passWord)
                        .compose(Composers.handleError())
    }

    var userName = ObservableField<String>("")
    var passWord = ObservableField<String>("")

    val doLogin = View.OnClickListener{ view: View ->
        getLoginTask(userName.get(), passWord.get())
                .subscribe({
                    LocalUser.userToken = it
                    (view.context as Activity).finish()
                }, {})
    }

    val toRegister = View.OnClickListener {
        it.context.apply {
            this.startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    /**
     * 跳转项目地址
     */
    val toGithub = View.OnClickListener {
        Intent().setAction(Intent.ACTION_VIEW)
                .setData(Uri.parse("https://github.com/fashare2015/MVVM-JueJin"))
                .apply { it.context.startActivity(this) }
    }
}
