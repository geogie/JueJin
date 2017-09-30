package com.georgeren.databinding.adapters;

import android.databinding.BindingAdapter;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.georgeren.imageloader.ImageLoader;

/**
 * Created by georgeRen on 2017/9/27.
 * ImageView添加属性：
 * <p>
 * uri：图片地址
 * placeholder：图片id
 * onImageLoad：图片加载情况回调
 * radius：图片圆角半径
 */

public final class ImageViewAdapter {
    /**
     * @param imageView
     * @param uri         图标地址
     * @param placeholder 图片id
     * @param callback    图片加载情况回调
     */
    @BindingAdapter(value = {"uri", "placeholder", "onImageLoad"}, requireAll = false)
    public static void loadImage(ImageView imageView, String uri, @DrawableRes int placeholder, ImageLoader.Callback callback) {
        ImageLoader.INSTANCE.loadImage(imageView, uri, placeholder, callback);
    }

    /**
     * @param imageView
     * @param uri         图片地址
     * @param placeholder 图片id
     * @param radius      图片圆角半径
     */
    @BindingAdapter(value = {"uri", "placeholder", "radius"}, requireAll = false)
    public static void loadRoundImage(ImageView imageView, String uri, @DrawableRes int placeholder, float radius) {
        ImageLoader.INSTANCE.loadRoundImage(imageView, uri, placeholder, (int) radius);
    }
}
