package com.georgeren.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.georgeren.adapter.OnItemClickListener;
import com.georgeren.adapter.ViewHolder;
import com.georgeren.databinding.ListVM;
import com.georgeren.databinding.R;
import com.georgeren.databinding.TwoWayListVM;
import com.georgeren.databinding.adapters.annotation.ResHolder;
import com.georgeren.databinding.adapters.annotation.ResUtils;

import java.util.List;

import me.tatarka.bindingcollectionadapter.ItemView;

/**
 * Created by georgeRen on 2017/9/27.
 * viewGroup适配器
 * 自定义属性：vm、data
 * 给viewGroup添加子view，类似listView
 */

public class ViewGroupAdapter {
    public static final String TAG = "ViewGroupAdapter - binding ViewGroup...: ";

    /**
     *
     * 添加子view，类似listView
     * @param viewGroup 根布局
     * @param itemView item--》listObject
     * @param viewModelList dataList
     * @param onItemClickListener 单机事件
     */
    private static void bind(ViewGroup viewGroup, ItemView itemView, List<?> viewModelList, final OnItemClickListener<?> onItemClickListener) {
        if (viewModelList != null && !viewModelList.isEmpty()) {
            viewGroup.removeAllViews();// 清空viewGroup
            for (int pos=0; pos<viewModelList.size(); pos++) {// 遍历
                final Object viewModel = viewModelList.get(pos);
                // 把 itemView 放进 viewGroup
                final ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        itemView.layoutRes(), viewGroup, true);
                binding.setVariable(itemView.bindingVariable(), viewModel);// itemView 和 itemModel绑定。data--view绑定
                // 设置点击事件
                setListener(onItemClickListener, ViewHolder.createViewHolder(viewGroup.getContext(), binding.getRoot()), viewModel, pos);
            }
        }
    }

    /**
     * 单机事件
     * @param onItemClickListener
     * @param viewHolder
     * @param data
     * @param pos
     */
    private static void setListener(final OnItemClickListener onItemClickListener, final ViewHolder viewHolder, final Object data, final int pos) {
        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(viewHolder, data, pos);
                }
            }
        });
    }

    /**
     * bind之前的空处理
     * @param viewGroup
     * @param vm
     * @param datas
     * @param <T>
     */
    private static <T> void bind(ViewGroup viewGroup, ListVM<T> vm, List<T> datas) {
        if(vm == null)
            return ;

        ItemView item = ResUtils.INSTANCE.getItemView(vm.getClass().getAnnotation(ResHolder.class));

        if(item == null)
            throw new IllegalArgumentException(TAG + "ItemView is null, maybe you forget @ResHolder(R.layout.XXX) in " + vm.getClass().getCanonicalName());

        bind(viewGroup, item, datas, vm.getOnItemClick());
    }

    /**
     * 自定义属性：vm、data
     * (伪)双向 databinding: 同 {@link RecyclerViewAdapter#setDataTwoWay(RecyclerView, ListVM, List)}
     *
     * @param container
     * @param vm
     * @param datas
     * @param <T>
     */
    @BindingAdapter({"vm", "data"})
    public static <T> void setDataTwoWay(final ViewGroup container, final ListVM<T> vm, List<T> datas){
        if(vm == null){
            return ;
        }
        bind(container, vm, datas);// 绑定并添加ziview

        if(vm instanceof TwoWayListVM){
            boolean isInited = container.getTag(R.id.db_inited) != null;// 获取是否初始化的标签
            if(!isInited) {
                container.setTag(R.id.db_inited, true);// 添加初始化过标签
                loadData(container, (TwoWayListVM<T>)vm, null, null);
            }
        }
    }

    /**
     * 拉取数据
     * @param container
     * @param vm
     * @param lastItem
     * @param refreshable 对象（刷新处理）
     * @param <T>
     */
    public static <T> void loadData(final ViewGroup container, final TwoWayListVM<T> vm, T lastItem, final TwoWayListVM.Refreshable refreshable){
        if(vm.getLoadTask() != null && vm.getLoadTaskObserver() != null){
            vm.getLoadTask().invoke(lastItem)// 调用这个方法
                    .subscribe(vm.getLoadTaskObserver().invoke(vm.getData(), refreshable));
        }
    }

}
