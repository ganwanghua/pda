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

public class WaitAgoAdapter extends RecyclerView.Adapter<WaitAgoAdapter.WaitAgoViewHolder> {
    private LayoutInflater mInflater;
    private List<PickUpDetailRecord.YesterdaylistBean> mShowItems;
    private Context context;

    public WaitAgoAdapter(Context context) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public WaitAgoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_wait, parent, false);
        WaitAgoViewHolder viewHolder = new WaitAgoViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WaitAgoViewHolder holder, final int position) {
        if (mShowItems.size() > 0) {
            holder.tv_item_nickname.setText(mShowItems.get(position).getSku_code());
            holder.tv_item_phone.setText(mShowItems.get(position).getGoods_title());
            holder.tv_item_num.setText(mShowItems.get(position).getGoods_num() + "件");
            holder.tv_specification.setText(mShowItems.get(position).getGoods_sku_text());
        }
        holder.ll_wait_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClicks(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mShowItems == null ? 0 : mShowItems.size();
    }

    public void setData(List<PickUpDetailRecord.YesterdaylistBean> list) {
        mShowItems = list;
        notifyDataSetChanged();
    }


    //**********************itemClick************************
    public interface OnItemClickListener {
        void onItemClicks(int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
    //**************************************************************

    public static class WaitAgoViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout ll_wait_detail;
        private final TextView tv_item_nickname;
        private final TextView tv_item_phone;
        private final TextView tv_item_num;
        private final TextView tv_specification;

        public WaitAgoViewHolder(View itemView) {
            super(itemView);
            ll_wait_detail = itemView.findViewById(R.id.ll_wait_detail);
            tv_item_nickname = itemView.findViewById(R.id.tv_item_nickname);
            tv_item_phone = itemView.findViewById(R.id.tv_item_phone);
            tv_item_num = itemView.findViewById(R.id.tv_item_num);
            tv_specification = itemView.findViewById(R.id.tv_specification);
        }
    }


    public Object getItem(int position) {
        return mShowItems.get(position);
    }


}
