package com.georgeren.juejin.view.profile.login

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.georgeren.base_ui.BaseActivity
import com.georgeren.juejin.R
import com.georgeren.juejin.databinding.ActivityLoginBinding
import com.georgeren.juejin.viewmodel.LoginVM

/**
 * Created by georgeRen on 2017/9/28.
 * 登录
 */
class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login).apply{
            this.loginVM = LoginVM()
        }
    }
}
