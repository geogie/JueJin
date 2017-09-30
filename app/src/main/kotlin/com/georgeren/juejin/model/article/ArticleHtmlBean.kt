package com.georgeren.juejin.model.article

import java.io.Serializable

/**
 * Created by georgeRen on 2017/9/27.
 */
class ArticleHtmlBean : Serializable {

    var entryViewId: String? = null
    var entryId: String? = null
    var content: String? = null
    /**
     * screenShot : null
     * imageUrlArray : ["https://user-gold-cdn.xitu.io/2017/9/15/e4728e628394957924f586517e8d8b1b","https://user-gold-cdn.xitu.io/2017/9/15/bf8a8ab4587aae27ea45047b05de8df7","https://user-gold-cdn.xitu.io/2017/9/15/c904169f71100abd071015c2bd8beb68","https://user-gold-cdn.xitu.io/2017/9/15/3dddd589018737544c8160c5ff2be7e4","https://user-gold-cdn.xitu.io/2017/9/15/316c6e23b89e4c447b415b59937bd0e4"]
     */

    var imageCache: ImageCacheBean? = null
    var auto: Boolean = false
    var version: Int = 0
    var createdAt: String? = null
    var updatedAt: String? = null

    class ImageCacheBean: Serializable {
        var screenShot: Any? = null
        var imageUrlArray: List<String>? = null
    }
}