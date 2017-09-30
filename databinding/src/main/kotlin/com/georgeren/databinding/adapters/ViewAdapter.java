package com.georgeren.databinding.adapters;

import android.databinding.BindingAdapter;
import android.view.View;

/**
 * Created by georgeRen on 2017/9/27.
 * View的adapter
 * 自定义属性：visibility
 */

public class ViewAdapter {
    /**
     * @param view
     * @param isShow 是否可见 VISIBLE GONE
     */
    @BindingAdapter(value = {"visibility"})
    public static void setVisibility(View view, boolean isShow) {
        view.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }
}
