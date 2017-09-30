package com.georgeren.juejin.widget

import android.app.Activity
import android.content.Context
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.util.Log
import com.georgeren.juejin.R

/**
 * Created by georgeRen on 2017/9/27.
 */
class TitleBar : Toolbar {
    val TAG = javaClass.simpleName
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    @Suppress("DEPRECATION")
    private fun init() {
        if(navigationIcon == null) {
            setNavigationIcon(R.drawable.g_back_white)
        }
        setNavigationOnClickListener { (context as Activity).finish() }

        if(background == null)
            setBackgroundColor(context.resources.getColor(R.color.colorPrimary))

        if(getTitleTextColor() == 0)
            setTitleTextColor(context.resources.getColor(android.R.color.white))
    }

    private fun getTitleTextColor(): Int {
        return try {
            Toolbar::class.java.getDeclaredField("mTitleTextColor").apply {
                this.isAccessible = true
            }.get(this) as Int
        }catch (e: NoSuchFieldException){
            Log.e(TAG, e.toString())
            0
        }
    }
}