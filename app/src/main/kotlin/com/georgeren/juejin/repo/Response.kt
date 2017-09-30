package com.georgeren.juejin.repo

import com.google.gson.Gson

/**
 * Created by georgeRen on 2017/9/26.
 */
class Response<T> {
    companion object Initializer {
        private val GSON: Gson by lazy { Gson() }
    }

    var s = -1 // errorCode
    var m = "" // errorMsg
    var d: T? = null // data
    override fun toString(): String {
        return GSON.toJson(this)
    }
}