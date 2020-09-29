package com.tuzixiansheng.pda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tuzixiansheng.pda.R;
import com.tuzixiansheng.pda.bean.ReturnGoods;

import java.util.ArrayList;
import java.util.List;

public class ReturnHistoryAdapter extends RecyclerView.Adapter<ReturnHistoryAdapter.MyViewHolder> {

    private Context mContext;
    private List<ReturnGoods.DataBean.ListBean> mList = new ArrayList<>();
    private MyItemClickListener mItemClickListener;

    public void setData(List<ReturnGoods.DataBean.ListBean> data) {
        mList = data;
        notifyDataSetChanged();
    }

    public interface MyItemClickListener {
        void onItemClick(int position);
    }

    public void setItemClickListener(MyItemClickListener myItemClickListener) {
        this.mItemClickListener = myItemClickListener;
    }

    public ReturnHistoryAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_return_history, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_name.setText(mList.get(position).getNickName());
        holder.tv_phone.setText(mList.get(position).getPhone());
        holder.tv_goods_name.setText(mList.get(position).getSkuName());
        holder.tv_num.setText(mList.get(position).getSkuStandard() + mList.get(position).getSkuUnit() + "*" + mList.get(position).getSkuNum());
       holder.tv_time.setText("收货时间："+mList.get(position).getCreateDate());

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_phone, tv_goods_name, tv_num, tv_time;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_phone = itemView.findViewById(R.id.tv_phone);
            tv_goods_name = itemView.findViewById(R.id.tv_goods_name);
            tv_num = itemView.findViewById(R.id.tv_num);
            tv_time = itemView.findViewById(R.id.tv_time);
        }
    }
}
