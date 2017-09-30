package com.georgeren.databinding.adapters;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.webkit.WebView;

/**
 * Created by georgeRen on 2017/9/27.
 * webView适配器
 * 自定义属性：uri、
 */

public final class WebViewAdapter {
    /**
     *
     * @param webView
     * @param uri html 地址
     */
    @BindingAdapter("uri")
    public static void bind(WebView webView, String uri){
        webView.loadUrl(uri);
    }

    /**
     * 以 html 加载数据
     * @param webView
     * @param html 内容
     * @param cssPath 绝对路径, 如 assets 文件夹下: "file:///android_asset/article.css"
     */
    @BindingAdapter(value = {"html", "cssPath"}, requireAll = false)
    public static void bind(WebView webView, String html, String cssPath){
        loadDataWithCss(webView, html, cssPath);
    }

    public static final String CSS_URL = "file:///android_asset/" + "style.css";
    public static final String CSS_LINK_PLACE_HOLDER = "<link rel=\"stylesheet\" type=\"text/css\" href=\"%s\" />";

    /**
     *
     * @param webView
     * @param html 网页内容
     * @param cssPath
     */
    public static void loadDataWithCss(WebView webView, String html, String cssPath){
        String css = !TextUtils.isEmpty(cssPath)? String.format(CSS_LINK_PLACE_HOLDER, cssPath): "";
        webView.loadDataWithBaseURL(null, css + html, "text/html", "UTF-8", null);
    }
}
