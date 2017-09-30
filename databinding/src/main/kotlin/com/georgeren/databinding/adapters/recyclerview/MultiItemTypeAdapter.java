package com.georgeren.databinding.adapters.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.georgeren.adapter.OnItemClickListener;
import com.georgeren.adapter.ViewHolder;
import com.georgeren.databinding.adapters.recyclerview.base.ItemViewDelegate;
import com.georgeren.databinding.adapters.recyclerview.base.ItemViewDelegateManager;

import java.util.List;

/**
 * Created by georgeRen on 2017/9/27.
 */

public class MultiItemTypeAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    protected final String TAG = this.getClass().getSimpleName();
    protected Context mContext;
    protected List<T> mDatas;

    protected ItemViewDelegateManager mItemViewDelegateManager;
    protected OnItemClickListener mOnItemClickListener;

    public List<T> getDataList() {
        return mDatas;
    }

    public void setDataList(List<T> dataList) {
        if(dataList != null) {
            this.mDatas = dataList;
            notifyDataSetChanged();
        }else{
            Log.e(TAG, "mDataList is null");
        }
    }

    public MultiItemTypeAdapter(Context context, List<T> datas) {
        mContext = context;
        mDatas = datas;
        mItemViewDelegateManager = new ItemViewDelegateManager();
    }

    @Override
    public int getItemViewType(int position) {
        if (!useItemViewDelegateManager()) return super.getItemViewType(position);
        return mItemViewDelegateManager.getItemViewType(mDatas.get(position), position);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemViewDelegate itemViewDelegate = mItemViewDelegateManager.getItemViewDelegate(viewType);
        int layoutId = itemViewDelegate.getItemViewLayoutId();
        ViewHolder holder = ViewHolder.createViewHolder(mContext, parent, layoutId);
        onViewHolderCreated(holder,holder.getConvertView());
        return holder;
    }

    public void onViewHolderCreated(ViewHolder holder,View itemView){

    }

    protected void convert(ViewHolder holder, T t, int position) {
        mItemViewDelegateManager.convert(holder, t, position);
    }

    protected boolean isEnabled(int viewType) {
        return true;
    }


    protected void setListener(final ViewHolder viewHolder, final int position) {
        if (!isEnabled(getItemViewType(position))) return;
        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(viewHolder, mDatas.get(position), position);
                }
            }
        });

        viewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemClickListener != null) {
                    return mOnItemClickListener.onItemLongClick(viewHolder, mDatas.get(position), position);
                }
                return false;
            }
        });
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        convert(holder, mDatas.get(position), position);
        setListener(holder, position);
    }

    @Override
    public int getItemCount() {
        int itemCount = mDatas.size();
        return itemCount;
    }

    public MultiItemTypeAdapter addItemViewDelegate(ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(itemViewDelegate);
        return this;
    }

    public MultiItemTypeAdapter addItemViewDelegate(int viewType, ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(viewType, itemViewDelegate);
        return this;
    }

    protected boolean useItemViewDelegateManager() {
        return mItemViewDelegateManager.getItemViewDelegateCount() > 0;
    }

    public MultiItemTypeAdapter setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
        return this;
    }
}
