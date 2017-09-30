package com.georgeren.juejin.viewmodel

import android.content.Intent
import android.databinding.ObservableField
import android.view.View
import android.widget.Toast
import com.blankj.utilcode.util.RegexUtils
import com.georgeren.juejin.repo.Composers
import com.georgeren.juejin.repo.JueJinApis
import com.georgeren.juejin.repo.local.LocalUser
import com.georgeren.juejin.view.MainActivity
import com.georgeren.net.ApiFactory

/**
 * Created by georgeRen on 2017/9/29.
 */
class RegisterVM {
    var phoneNum = ObservableField<String>("")
    var userName = ObservableField<String>("")
    var passWord = ObservableField<String>("")

    val doRegister = View.OnClickListener{ view: View ->
        if(RegexUtils.isMobileSimple(phoneNum.get())){
            Toast.makeText(view.context, "不支持手机号注册", Toast.LENGTH_SHORT).show()
            return@OnClickListener
        }
        ApiFactory.getApi(JueJinApis.User.Storage::class.java)
                .register(email = phoneNum.get(),
                        username = userName.get(),
                        password = passWord.get())
                .compose(Composers.handleError())
                .flatMap { LoginVM.getLoginTask(phoneNum.get(), passWord.get()) }
                .subscribe({
                    LocalUser.userToken = it

                    view.context.apply {
                        this.startActivity(Intent(this, MainActivity::class.java)
                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        )
                    }
                }, {})
    }
}