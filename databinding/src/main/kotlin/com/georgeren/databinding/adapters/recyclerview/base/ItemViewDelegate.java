package com.georgeren.databinding.adapters.recyclerview.base;

import com.georgeren.adapter.ViewHolder;

/**
 * Created by georgeRen on 2017/9/27.
 */

public interface ItemViewDelegate<T>
{

    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    void convert(ViewHolder holder, T t, int position);

}