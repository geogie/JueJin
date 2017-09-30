package com.georgeren.databinding.adapters;

import android.database.DataSetObserver;
import android.databinding.BindingAdapter;
import android.os.Parcelable;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by georgeRen on 2017/9/27.
 * TabLayout的适配器
 * 自定义属性：data、viewPagerIdRes
 */

public class TabLayoutAdapter {
    /**
     *
     * @param container 适配对应的view
     * @param datas 数据
     * @param viewPagerIdRes viewPager的id
     */
    @BindingAdapter(value = {"data", "viewPagerIdRes"}, requireAll = false)
    public static void bind(final TabLayout container, final List<String> datas, @IdRes final int viewPagerIdRes) {
        ViewPager viewPager = findViewPager(container, viewPagerIdRes);// 获取viewpager对象

        if (datas != null && !datas.isEmpty()) {// 数据空判断
            if (viewPager == null) {// viewPager空判断
                for (String title : datas) {// 根据datas，遍历添加title
                    container.addTab(container.newTab().setText(title));
                }
            } else { // 如果有viewPager，则tabLayout和viewPager关联
                /** TabLayout tabs text not displaying: https://stackoverflow.com/questions/38049076/tablayout-tabs-text-not-displaying/38049409#38049409 */
                container.setupWithViewPager(viewPager);
                /** setupWithViewPager will automatically add tabItems into TabLayout from viewPager.adapter {@link TabLayout#populateFromPagerAdapter()}.
                 * so we should bind datas into viewPager.adapter.
                 */
//                viewPager.addOnAdapterChangeListener(new ViewPager.OnAdapterChangeListener() {
//                    @Override
//                    public void onAdapterChanged(@NonNull final ViewPager viewPager, @Nullable PagerAdapter oldAdapter, @Nullable final PagerAdapter newAdapter) {
//                        if(newAdapter != null && !(newAdapter instanceof PagerAdapterProxy)) {
//                            viewPager.postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//                                    viewPager.setAdapter(new PagerAdapterProxy(viewPager.getAdapter()) {
//                                        @Override
//                                        public CharSequence getPageTitle(int position) {
//                                            return position>=0 && position<datas.size()? datas.get(position): "";
//                                        }
//                                    });
//                                }
//                            }, 1000);
//                        }
//                    }
//                });

                /** setupWithViewPager() will call {@link TabLayout#selectTab(TabLayout.Tab)} after fetching datas from viewPager.adapter.
                 *  so we just listen on the TabSelected event, and then hack the datas.
                 */
                container.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {// 添加tab的选择监听事件
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        for (int i = 0; i < container.getTabCount() && i < datas.size(); i++) {
                            String title = datas.get(i);
                            container.getTabAt(i).setText(title);
                        }
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                    }
                });
            }
        }
    }

    private static ViewPager findViewPager(View root, @IdRes int viewPagerIdRes) {
        View _vp;
        while (root.getParent() != null && root.getParent() instanceof View) {
            root = (View) root.getParent();
            if (root != null && (_vp = root.findViewById(viewPagerIdRes)) != null && _vp instanceof ViewPager) {
                return (ViewPager) _vp;
            }
        }
        return null;
    }

    public static class PagerAdapterProxy extends PagerAdapter {
        @NonNull
        private PagerAdapter delegate;

        public PagerAdapterProxy(@NonNull PagerAdapter delegate) {
            this.delegate = delegate;
        }

        public int getCount() {
            return delegate.getCount();
        }

        public void startUpdate(ViewGroup container) {
            delegate.startUpdate(container);
        }

        public Object instantiateItem(ViewGroup container, int position) {
            return delegate.instantiateItem(container, position);
        }

        public void destroyItem(ViewGroup container, int position, Object object) {
            delegate.destroyItem(container, position, object);
        }

        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            delegate.setPrimaryItem(container, position, object);
        }

        public void finishUpdate(ViewGroup container) {
            delegate.finishUpdate(container);
        }

        @Deprecated
        public void startUpdate(View container) {
            delegate.startUpdate(container);
        }

        @Deprecated
        public Object instantiateItem(View container, int position) {
            return delegate.instantiateItem(container, position);
        }

        @Deprecated
        public void destroyItem(View container, int position, Object object) {
            delegate.destroyItem(container, position, object);
        }

        @Deprecated
        public void setPrimaryItem(View container, int position, Object object) {
            delegate.setPrimaryItem(container, position, object);
        }

        @Deprecated
        public void finishUpdate(View container) {
            delegate.finishUpdate(container);
        }

        public boolean isViewFromObject(View view, Object object) {
            return delegate.isViewFromObject(view, object);
        }

        public Parcelable saveState() {
            return delegate.saveState();
        }

        public void restoreState(Parcelable state, ClassLoader loader) {
            delegate.restoreState(state, loader);
        }

        public int getItemPosition(Object object) {
            return delegate.getItemPosition(object);
        }

        public void notifyDataSetChanged() {
            delegate.notifyDataSetChanged();
        }

        public void registerDataSetObserver(DataSetObserver observer) {
            delegate.registerDataSetObserver(observer);
        }

        public void unregisterDataSetObserver(DataSetObserver observer) {
            delegate.unregisterDataSetObserver(observer);
        }

        public CharSequence getPageTitle(int position) {
            return delegate.getPageTitle(position);
        }

        public float getPageWidth(int position) {
            return delegate.getPageWidth(position);
        }
    }

}
