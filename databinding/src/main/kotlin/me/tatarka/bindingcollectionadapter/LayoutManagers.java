package me.tatarka.bindingcollectionadapter;

import android.support.annotation.IntDef;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by georgeRen on 2017/9/27.
 * 布局管理：
 * 类型：线性
 * 方向：水平
 */

public class LayoutManagers {
    protected LayoutManagers() {
    }

    /**
     *
     */
    public interface LayoutManagerFactory {
        /**
         *
         * @param recyclerView RecyclerView 的 LayoutManager
         * @return
         */
        RecyclerView.LayoutManager create(RecyclerView recyclerView);
    }

    /**
     * A {@link LinearLayoutManager}.
     * RecyclerView 的 线性布局
     */
    public static LayoutManagerFactory linear() {
        return new LayoutManagerFactory() {
            @Override
            public RecyclerView.LayoutManager create(RecyclerView recyclerView) {
                return new LinearLayoutManager(recyclerView.getContext());
            }
        };
    }

    /**
     * A {@link LinearLayoutManager}.
     * 水平线性布局
     */
    public static LayoutManagerFactory linearHorizontal() {
        return linear(LinearLayoutManager.HORIZONTAL, false);
    }

    /**
     * A {@link LinearLayoutManager} with the given orientation and reverseLayout.
     * @param orientation 方向
     * @param reverseLayout 是否反
     * @return
     */
    public static LayoutManagerFactory linear(@Orientation final int orientation, final boolean reverseLayout) {
        return new LayoutManagerFactory() {
            @Override
            public RecyclerView.LayoutManager create(RecyclerView recyclerView) {
                return new LinearLayoutManager(recyclerView.getContext(), orientation, reverseLayout);
            }
        };
    }

    /**
     * A {@link GridLayoutManager} with the given spanCount.
     */
    public static LayoutManagerFactory grid(final int spanCount) {
        return new LayoutManagerFactory() {
            @Override
            public RecyclerView.LayoutManager create(RecyclerView recyclerView) {
                return new GridLayoutManager(recyclerView.getContext(), spanCount);
            }
        };
    }

    /**
     * A {@link GridLayoutManager} with the given spanCount, orientation and reverseLayout.
     **/
    public static LayoutManagerFactory grid(final int spanCount, @Orientation final int orientation, final boolean reverseLayout) {
        return new LayoutManagerFactory() {
            @Override
            public RecyclerView.LayoutManager create(RecyclerView recyclerView) {
                return new GridLayoutManager(recyclerView.getContext(), spanCount, orientation, reverseLayout);
            }
        };
    }

    /**
     * A {@link StaggeredGridLayoutManager} with the given spanCount and orientation.
     */
    public static LayoutManagerFactory staggeredGrid(final int spanCount, @Orientation final int orientation) {
        return new LayoutManagerFactory() {
            @Override
            public RecyclerView.LayoutManager create(RecyclerView recyclerView) {
                return new StaggeredGridLayoutManager(spanCount, orientation);
            }
        };
    }

    /**
     * 自定义注解：
     * 用注解 替代 枚举。
     */
    @IntDef({LinearLayoutManager.HORIZONTAL, LinearLayoutManager.VERTICAL})// 全部常量声明
    @Retention(RetentionPolicy.SOURCE)// 表明注解的生命周期，声明其保留级别,在原文件中有效，被编译器丢弃
    public @interface Orientation {
    }

}
