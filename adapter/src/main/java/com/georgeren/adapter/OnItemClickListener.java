package com.georgeren.adapter;

/**
 * Created by georgeRen on 2017/9/27.
 * item：单机事件，长按事件
 */

public abstract class OnItemClickListener<T> {
    /**
     *
     * @param holder view
     * @param data 数据
     * @param position 下标
     */
    public abstract void onItemClick(ViewHolder holder, T data, int position);

    /**
     *
     * @param holder view
     * @param data 数据
     * @param position 下标
     * @return
     */
    public boolean onItemLongClick(ViewHolder holder, T data, int position) {
        return false;
    }
}
