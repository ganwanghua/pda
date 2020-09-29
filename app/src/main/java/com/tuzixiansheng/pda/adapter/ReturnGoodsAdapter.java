package com.tuzixiansheng.pda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tuzixiansheng.pda.R;
import com.tuzixiansheng.pda.bean.PickUpRecord;
import com.tuzixiansheng.pda.bean.ReturnGoods;

import java.util.ArrayList;
import java.util.List;

public class ReturnGoodsAdapter extends RecyclerView.Adapter<ReturnGoodsAdapter.MyViewHolder> {

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

    public ReturnGoodsAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_return_goods, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_name.setText(mList.get(position).getNickName());
        holder.tv_phone.setText(mList.get(position).getPhone());
        holder.tv_goods_name.setText(mList.get(position).getSkuName());
        holder.tv_num.setText(mList.get(position).getSkuStandard() + mList.get(position).getSkuUnit() + "*" + mList.get(position).getSkuNum());
        holder.btn_return_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(position);
                }
            }
        });

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
        private TextView tv_name, tv_phone, tv_goods_name, tv_num;
        private Button btn_return_goods;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_phone = itemView.findViewById(R.id.tv_phone);
            tv_goods_name = itemView.findViewById(R.id.tv_goods_name);
            tv_num = itemView.findViewById(R.id.tv_num);
            btn_return_goods = itemView.findViewById(R.id.btn_return_goods);
        }
    }
}
