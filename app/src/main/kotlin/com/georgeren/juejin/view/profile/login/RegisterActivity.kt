package com.georgeren.juejin.view.profile.login

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.georgeren.base_ui.BaseActivity
import com.georgeren.juejin.R
import com.georgeren.juejin.databinding.ActivityRegisterBinding
import com.georgeren.juejin.viewmodel.RegisterVM

/**
 * Created by georgeRen on 2017/9/29.
 */
class RegisterActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityRegisterBinding>(this, R.layout.activity_register).apply{
            this.registerVM = RegisterVM()
        }
    }
}