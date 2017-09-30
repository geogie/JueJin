package com.georgeren.net.exception

/**
 * Created by georgeRen on 2017/9/27.
 * api请求中的 返回码和错误信息 提示
 */
class ApiException (throwable: Throwable,
                    val errorCode: Int,
                    var errorMsg: String? = null) : Exception(throwable) {
    override fun toString(): String {
        return "ApiException(errorCode=$errorCode, errorMsg=$errorMsg)"
    }
}