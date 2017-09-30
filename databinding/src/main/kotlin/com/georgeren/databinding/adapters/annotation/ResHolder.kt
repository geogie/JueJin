package com.georgeren.databinding.adapters.annotation

import android.support.annotation.LayoutRes

/**
 * Created by georgeRen on 2017/9/26.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ResHolder(@LayoutRes val layout: Int,
                           val br: Int = ResUtils.NO_BR)