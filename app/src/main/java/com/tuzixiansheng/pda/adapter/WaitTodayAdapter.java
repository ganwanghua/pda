package com.tuzixiansheng.pda.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tuzixiansheng.pda.R;
import com.tuzixiansheng.pda.bean.PickUpDetailRecord;

import java.util.List;


/**
 * @author: xl
 * @date: 2017/7/19
 */

public class WaitTodayAdapter extends RecyclerView.Adapter<WaitTodayAdapter.WaitTodayViewHolder> {
    private LayoutInflater mInflater;
    private List<PickUpDetailRecord.DataBean> mShowItems;
    private Context context;

    public WaitTodayAdapter(Context context) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public WaitTodayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_wait, parent, false);
        WaitTodayViewHolder viewHolder = new WaitTodayViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WaitTodayViewHolder holder, final int position) {
        holder.tv_item_nickname.setText(mShowItems.get(position).getSkuCode());
        holder.tv_item_phone.setText(mShowItems.get(position).getSkuName());
        holder.tv_item_num.setText(mShowItems.get(position).getSkuNum());
        holder.ll_wait_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mShowItems == null ? 0 : mShowItems.size();
    }

    public void setData(List<PickUpDetailRecord.DataBean> list) {
        mShowItems = list;
        notifyDataSetChanged();
    }


    //**********************itemClick************************
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
    //**************************************************************

    public static class WaitTodayViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout ll_wait_detail;
        private final TextView tv_item_nickname;
        private final TextView tv_item_phone;
        private final TextView tv_item_num;

        public WaitTodayViewHolder(View itemView) {
            super(itemView);
            ll_wait_detail = itemView.findViewById(R.id.ll_wait_detail);
            tv_item_nickname = itemView.findViewById(R.id.tv_item_nickname);
            tv_item_phone = itemView.findViewById(R.id.tv_item_phone);
            tv_item_num = itemView.findViewById(R.id.tv_item_num);
        }
    }


    public Object getItem(int position) {
        return mShowItems.get(position);
    }


}
