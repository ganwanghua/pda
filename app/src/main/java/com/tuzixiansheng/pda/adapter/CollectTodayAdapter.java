package com.tuzixiansheng.pda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tuzixiansheng.pda.R;
import com.tuzixiansheng.pda.bean.PickUpRecord;
import com.tuzixiansheng.pda.bean.TitleBean;

import java.util.ArrayList;
import java.util.List;

public class CollectTodayAdapter extends RecyclerView.Adapter<CollectTodayAdapter.MyViewHolder> {

    private Context mContext;
    private List<PickUpRecord.DataBeanX.DataBean> mList = new ArrayList<>();
    private MyItemClickListener mItemClickListener;

    public void setData(List<PickUpRecord.DataBeanX.DataBean> data) {
        mList = data;
        notifyDataSetChanged();
    }

    public interface MyItemClickListener {
        void onItemClick(int position);
    }

    public void setItemClickListener(MyItemClickListener myItemClickListener) {
        this.mItemClickListener = myItemClickListener;
    }

    public CollectTodayAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_classify_one, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_item_nickname.setText(mList.get(position).getNickname());
        holder.tv_item_phone.setText(mList.get(position).getMobile());
        holder.tv_specifications.setText(mList.get(position).getOrder_num() + "件");
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
        private TextView tv_item_nickname, tv_item_phone, tv_specifications;
        private LinearLayout ll_wait_detail;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_item_nickname = itemView.findViewById(R.id.tv_item_nickname);
            tv_item_phone = itemView.findViewById(R.id.tv_item_phone);
            tv_specifications = itemView.findViewById(R.id.tv_specifications);
            ll_wait_detail = itemView.findViewById(R.id.ll_wait_detail);
        }
    }
}
