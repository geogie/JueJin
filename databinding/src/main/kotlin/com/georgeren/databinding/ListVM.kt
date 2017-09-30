package com.georgeren.databinding

import android.databinding.ObservableArrayList
import com.georgeren.adapter.OnItemClickListener

/**
 * Created by georgeRen on 2017/9/27.
 * open：可以被子类重写
 * 数据
 * item点击事件：子类去实现
 * 头数据
 */
open class ListVM<T> {
    open var data = ObservableArrayList<T>()
        set(value) {
            field.clear()
            field.addAll(value)
        }
    open val onItemClick: OnItemClickListener<T>? = null
    open val headerData: Any? = null // Any相当于 java 的 Object
}