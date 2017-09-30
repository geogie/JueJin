package com.georgeren.juejin.viewmodel

import android.databinding.ObservableBoolean
import android.databinding.ObservableInt
import com.georgeren.adapter.OnItemClickListener
import com.georgeren.adapter.ViewHolder
import com.georgeren.databinding.ListVM
import com.georgeren.databinding.adapters.annotation.ResHolder
import com.georgeren.juejin.R

/**
 * Created by georgeRen on 2017/9/26.
 * VM：底部tab
 */
@ResHolder(R.layout.item_main_tab)
class TabVM() : ListVM<TabVM.Item>() {
    var curIndex = ObservableInt(0) // 当前位置

    override var onItemClick = object : OnItemClickListener<Item>() {
        override fun onItemClick(holder: ViewHolder?, data: Item?, position: Int) {// data: 是否被选中
            this@TabVM.data.forEach { // 遍历，选中true，其它false
                it.isSelected.set(false)
                if (it == data)
                    it.isSelected.set(true)
            }
            curIndex.set(position) // 改变当前位置
        }
    }

    /**
     * tab的图标：
     * isSelected：当前图标状态标示
     * iconResNormal：没选中图标id
     * iconResSelected：选中图标id
     */
    class Item(var isSelected: ObservableBoolean, var iconResNormal: Int, var iconResSelected: Int)

}