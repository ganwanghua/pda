package com.tuzixiansheng.pda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tuzixiansheng.pda.R;
import com.tuzixiansheng.pda.bean.PickUpListForGoods;
import com.tuzixiansheng.pda.bean.PickedDetail;

import java.util.ArrayList;
import java.util.List;

public class PickedDetailAdapter extends RecyclerView.Adapter<PickedDetailAdapter.MyViewHolder> {

    private Context mContext;
    private List<PickedDetail.DataBean> mList = new ArrayList<>();

    public void setData(List<PickedDetail.DataBean> data) {
        mList = data;
        notifyDataSetChanged();
    }

    public PickedDetailAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_piched_detail, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_order_number.setText(mList.get(position).getOrderNo());
        holder.tv_pick_time.setText(mList.get(position).getActualPickTime());
        PickedDetailsAdapter pickedDetailsAdapter = new PickedDetailsAdapter(mContext,mList.get(position).getSkus());
        holder.recycler_view.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        holder.recycler_view.setAdapter(pickedDetailsAdapter);
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
        private final TextView tv_order_number;
        private final TextView tv_pick_time;
        private final RecyclerView recycler_view;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_order_number = itemView.findViewById(R.id.tv_order_number);
            tv_pick_time = itemView.findViewById(R.id.tv_pick_time);
            recycler_view = itemView.findViewById(R.id.recycler_view);
        }
    }
}
