package com.georgeren.databinding.adapters.annotation

import com.georgeren.databinding.BR
import me.tatarka.bindingcollectionadapter.ItemView

/**
 * Created by georgeRen on 2017/9/26.
 */
object ResUtils {
    const val NO_BR = -1
    fun getItemView(res: HeaderResHolder?): ItemView? {
        return res?.let {
            ItemView.of(
                    if (it.br != NO_BR) it.br else BR.item,
                    it.layout
            )
        }
    }

    fun getItemView(res: ResHolder?): ItemView? {
        return res?.let {
            ItemView.of(
                    if (it.br != NO_BR) it.br else BR.item,
                    it.layout
            )
        }
    }
}