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
import com.tuzixiansheng.pda.bean.PickUpListForGoods;
import com.tuzixiansheng.pda.bean.PickUpRecord;

import java.util.ArrayList;
import java.util.List;

public class CommodityAdapter extends RecyclerView.Adapter<CommodityAdapter.MyViewHolder> {

    private Context mContext;
    private List<PickUpListForGoods.DataBean> mList = new ArrayList<>();
    private MyItemClickListener mItemClickListener;

    public void setData(List<PickUpListForGoods.DataBean> data) {
        mList = data;
        notifyDataSetChanged();
    }

    public interface MyItemClickListener {
        void onItemClick(int position);
    }

    public void setItemClickListener(MyItemClickListener myItemClickListener) {
        this.mItemClickListener = myItemClickListener;
    }

    public CommodityAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_classify_two, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_item_nickname.setText(mList.get(position).getSkuCode());
        holder.tv_item_phone.setText(mList.get(position).getSkuName());
        holder.tv_item_num.setText(mList.get(position).getPickNum() + "件");
        holder.tv_specifications.setText(mList.get(position).getSkuStandard());
        holder.ll_wait_detail.setOnClickListener(new View.OnClickListener() {
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
        private final LinearLayout ll_wait_detail;
        private final TextView tv_item_nickname;
        private final TextView tv_item_phone;
        private final TextView tv_item_num;
        private final TextView tv_specifications;

        public MyViewHolder(View itemView) {
            super(itemView);
            ll_wait_detail = itemView.findViewById(R.id.ll_wait_detail);
            tv_item_nickname = itemView.findViewById(R.id.tv_item_nickname);
            tv_item_phone = itemView.findViewById(R.id.tv_item_phone);
            tv_item_num = itemView.findViewById(R.id.tv_item_num);
            tv_specifications = itemView.findViewById(R.id.tv_specifications);
        }
    }
}
