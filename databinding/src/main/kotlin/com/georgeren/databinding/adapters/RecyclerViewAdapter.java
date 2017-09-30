package com.georgeren.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewParent;

import com.georgeren.adapter.OnItemClickListener;
import com.georgeren.adapter.ViewHolder;
import com.georgeren.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.georgeren.databinding.BR;
import com.georgeren.databinding.ListVM;
import com.georgeren.databinding.R;
import com.georgeren.databinding.TwoWayListVM;
import com.georgeren.databinding.adapters.annotation.HeaderResHolder;
import com.georgeren.databinding.adapters.annotation.ResHolder;
import com.georgeren.databinding.adapters.annotation.ResUtils;
import com.georgeren.databinding.adapters.recyclerview.CommonRvAdapter;

import java.util.Arrays;
import java.util.List;

import me.tatarka.bindingcollectionadapter.ItemView;
import me.tatarka.bindingcollectionadapter.LayoutManagers;

/**
 * Created by georgeRen on 2017/9/27.
 * RecyclerView 适配
 * 自定义属性：layoutManager、vm、data
 */

public class RecyclerViewAdapter {
    public static final String TAG = "RecyclerViewAdapter - binding RecyclerView...: ";

    @BindingConversion
    public static ItemView resToResHolder(@LayoutRes int layoutRes) {
        int defaultBindingVariable = BR.item;
        return ItemView.of(defaultBindingVariable, layoutRes);
    }

    private static void bind(RecyclerView container, final ItemView item, final List<?> datas,
                             final List<? extends ItemView> header, final List<?> headerDatas,
                             final OnItemClickListener<?> onItemClickListener) {


        RecyclerView.Adapter<?> adapter;
        if (datas != null) {
            // set default LayoutManager.
            if(container.getLayoutManager() == null)
                setLayoutManager(container, LayoutManagers.linear());

            adapter = container.getAdapter();
            if(adapter == null) {

                // initialize, adapter is only set once !!!
                CommonRvAdapter innerAdapter;
                container.setAdapter(adapter = new HeaderAndFooterWrapper<>(innerAdapter = new CommonRvAdapter<Object>(container.getContext(), item.layoutRes(), (List<Object>)datas) {
                    @Override
                    protected void convert(ViewHolder holder, Object data, int position) {
                        DataBindingUtil.bind(holder.itemView).setVariable(item.bindingVariable(), data);
                    }
                }));
                innerAdapter.setOnItemClickListener(onItemClickListener);

                // add headers !!!
                if (header!=null && headerDatas != null) {
                    HeaderAndFooterWrapper headerAdapter = (HeaderAndFooterWrapper) adapter;
                    headerAdapter.removeHeaderViews();
                    for (int i = 0; i < header.size() && i < headerDatas.size(); i++) {
                        ItemView _itemView = header.get(i);
                        Object _data = headerDatas.get(i);
                        LayoutInflater inflater = LayoutInflater.from(container.getContext());

                        ViewDataBinding binding = DataBindingUtil.inflate(inflater, _itemView.layoutRes(), container, false);
                        headerAdapter.addHeaderView(binding.getRoot());
                        binding.setVariable(_itemView.bindingVariable(), _data);
                    }
                }
            }

            adapter.notifyDataSetChanged();
        }
    }

    private static void bind(RecyclerView container, final ItemView item, final List<?> datas,
                             final ItemView header, final Object headerData,
                             final OnItemClickListener<?> onItemClickListener) {
        if(header != null && headerData != null) {
            bind(container, item, datas, Arrays.asList(header), Arrays.asList(headerData), onItemClickListener);
        }else
            bind(container, item, datas, Arrays.<ItemView>asList(), Arrays.asList(), onItemClickListener);
    }

    @BindingAdapter("layoutManager")
    public static void setLayoutManager(RecyclerView recyclerView, LayoutManagers.LayoutManagerFactory layoutManagerFactory) {
        recyclerView.setLayoutManager(layoutManagerFactory.create(recyclerView));
    }

    /**
     * 单向 databinding:
     *      需要手动触发 {@link ListVM#setData(ObservableArrayList)}
     *      然后自动更新 RecyclerView
     *
     * @param container
     * @param vm
     * @param datas
     * @param <T>
     */
    private static <T> void setData(RecyclerView container, ListVM<T> vm, List<T> datas){
        // <include> 标签有 bug, 会多调用bind(), 若不判空将导致空指针
        if(vm == null){
            return ;
        }

        ItemView item = ResUtils.INSTANCE.getItemView(vm.getClass().getAnnotation(ResHolder.class)),
                header = ResUtils.INSTANCE.getItemView(vm.getClass().getAnnotation(HeaderResHolder.class));

        if(item == null)
            throw new IllegalArgumentException(TAG + "ItemView is null, maybe you forget @ResHolder(R.layout.XXX) in " + vm.getClass().getCanonicalName());

        bind(container, item, datas, header, vm.getHeaderData(), vm.getOnItemClick());
    }

    /**
     * (伪)双向 databinding: 自动调用 {@link TwoWayListVM#getLoadTask()},
     *      并自动触发 {@link TwoWayListVM#setData(ObservableArrayList)}
     *      然后自动更新 RecyclerView
     *
     * @param container
     * @param vm
     * @param datas
     * @param <T>
     */
    @BindingAdapter({"vm", "data"})
    public static <T> void setDataTwoWay(final RecyclerView container, final ListVM<T> vm, List<T> datas){
        if(vm == null){// vm判空
            return ;
        }
        setData(container, vm, datas);

        if(vm instanceof TwoWayListVM) {
            boolean isInited = container.getTag(R.id.db_inited) != null;
            if (!isInited) {// 没有初始化
                container.setTag(R.id.db_inited, true);

                final TwoWayListVM<T> _vm = ((TwoWayListVM<T>) vm);

                loadData(container, _vm, null, null);
                // 若 parent 可下拉刷新，设置回调
                ViewParent parent = container.getParent();
                if (parent != null && parent instanceof TwoWayListVM.Refreshable) {
                    final TwoWayListVM.Refreshable refreshable = (TwoWayListVM.Refreshable) parent;
                    ((TwoWayListVM.Refreshable) parent).setOnRefresh(new TwoWayListVM.Refreshable.CallBack() {
                        @Override
                        public void onRefresh() {
                            loadData(container, _vm, null, refreshable);
                        }

                        @Override
                        public void onLoadMore() {
                            List<T> data = _vm.getData();
                            if (data.size() - 1 >= 0) {
                                loadData(container, _vm, data.get(data.size() - 1), refreshable);
                            }
                        }
                    });
                }
            }
        }
    }

    private static <T> boolean equals(Object _oldData, List<T> newData) {
        if(_oldData != null && newData != null && _oldData instanceof List){
            List<?> oldData = ((List) _oldData);
            if(oldData.size() != newData.size())
                return false;
            for(int i=0; i<oldData.size(); i++)
                if(oldData.get(i) != newData.get(i))    // 仅判断引用相等
                    return false;
            return true;
        }
        return false;
    }

    public static <T> void loadData(final RecyclerView container, final TwoWayListVM<T> vm, T lastItem, final TwoWayListVM.Refreshable refreshable){
        if(vm.getLoadTask() != null && vm.getLoadTaskObserver() != null){
            vm.getLoadTask().invoke(lastItem)
                    .subscribe(vm.getLoadTaskObserver().invoke(vm.getData(), refreshable));
        }
    }

}
