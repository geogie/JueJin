package com.georgeren.juejin.view.home

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.georgeren.base_ui.BaseFragment
import com.georgeren.juejin.R
import com.georgeren.juejin.databinding.FragmentProfileBinding
import com.georgeren.juejin.repo.Composers
import com.georgeren.juejin.repo.JueJinApis
import com.georgeren.juejin.repo.local.LocalUser
import com.georgeren.juejin.viewmodel.ProfileVM
import com.georgeren.net.ApiFactory

/**
 * Created by georgeRen on 2017/9/27.
 * 我的
 */
class ProfileFragment : BaseFragment(){
    lateinit var binding: FragmentProfileBinding
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<FragmentProfileBinding>(inflater, R.layout.fragment_profile, container, false).apply {
            binding = this
            this.profileVM = ProfileVM().apply{
                this.data.clear()
                this.data.addAll(this.getDefaultList())
            }
        }.root
    }

    override fun onResume() {
        super.onResume()
        LocalUser.userToken?.apply{
            ApiFactory.getApi(JueJinApis.User.Storage::class.java)
                    .getUserInfo()
                    .compose(Composers.handleError())
                    .subscribe({
                        binding.profileVM.user.set(it)
                        binding.profileVM.data.apply{
                            this.clear()
                            this.addAll(binding.profileVM.getDefaultList())
                        }
                    }, {})
        }
    }
}