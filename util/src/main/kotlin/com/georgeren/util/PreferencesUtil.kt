package com.georgeren.util

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

/**
 * Created by georgeRen on 2017/9/26.
 * 本地存储：SharedPreferences：key-value
 */
class PreferencesUtil {
    private lateinit var mSharedPreferences: SharedPreferences
    private var mGson: Gson = Gson()

    companion object {
        private const val NAME_SHARED_PREFERENCES = "NAME_SHARED_PREFERENCES"

        fun getInstance(context: Context) = PreferencesUtil(context)
    }

    private constructor()// 默认构造
    private constructor(context: Context) {
        mSharedPreferences = context.getSharedPreferences(NAME_SHARED_PREFERENCES, Context.MODE_PRIVATE)
    }

    /**
     * key
     * value: 对象(类似于Object)
     */
    fun putEntity(key: String, value: Any): Boolean {
        try {
            val editor = mSharedPreferences.edit()
            editor.putString(key, mGson.toJson(value))// 对象转GSon字符串后存入
            return editor.commit()
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    /**
     * 删除key(包括对应的value)
     */
    fun remove(key: String): Boolean {
        return mSharedPreferences.edit().remove(key).commit()
    }

    /**
     * key:
     * classOfT: 模型
     * 根据key取出value，根据 classOfT 生成对应的对象
     */
    fun <T> getEntity(key: String, classOfT: Class<T>): T? {
        var entity: T? = null
        try {
            entity = mGson.fromJson(mSharedPreferences.getString(key, null), classOfT)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return entity
    }

}