package com.tuzixiansheng.pda.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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

public class OriginalDeliveryAdapter extends RecyclerView.Adapter<OriginalDeliveryAdapter.WaitTodayViewHolder> {
    private LayoutInflater mInflater;
    private Context context;

    public OriginalDeliveryAdapter(Context context) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public WaitTodayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_original_delivery, parent, false);
        WaitTodayViewHolder viewHolder = new WaitTodayViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WaitTodayViewHolder holder, final int position) {
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public void setData(List<PickUpDetailRecord.TodayListBean> list) {
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
        private final TextView tv_num;
        private final EditText edit_num;
        private final TextView tv_company;

        public WaitTodayViewHolder(View itemView) {
            super(itemView);
            tv_num = itemView.findViewById(R.id.tv_num);
            edit_num = itemView.findViewById(R.id.edit_num);
            tv_company = itemView.findViewById(R.id.tv_company);
        }
    }
}
