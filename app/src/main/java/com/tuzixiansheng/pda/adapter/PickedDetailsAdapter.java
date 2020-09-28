package com.tuzixiansheng.pda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tuzixiansheng.pda.R;
import com.tuzixiansheng.pda.base.Constant;
import com.tuzixiansheng.pda.bean.PickedDetail;
import com.tuzixiansheng.pda.weight.CustomRoundAngleImageView;

import java.util.ArrayList;
import java.util.List;

public class PickedDetailsAdapter extends RecyclerView.Adapter<PickedDetailsAdapter.MyViewHolder> {

    private Context mContext;
    private List<PickedDetail.DataBean.SkusBean> mList = new ArrayList<>();

    public PickedDetailsAdapter(Context context, List<PickedDetail.DataBean.SkusBean> data) {
        mList = data;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_piched_details, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_name.setText(mList.get(position).getSkuName());
        holder.tv_num.setText("x" + mList.get(position).getActualPickNum());
        holder.tv_specifications.setText("规格：" + mList.get(position).getSkuStandard());
        holder.tv_price.setText("¥" + mList.get(position).getOrderActualPrice());
        holder.tv_copies.setText("实收份数：" + mList.get(position).getActualPickNum());
        holder.tv_weight.setText("实收重量：" + mList.get(position).getActualWeight());
        holder.tv_give_change.setText("找零：¥" + mList.get(position).getChangePrice());
        holder.tv_stock.setText("缺货：" + mList.get(position).getOutStockPrice());
        holder.tv_money.setText("最终实付：" + mList.get(position).getActualPrice());
        Glide.with(mContext).load(Constant.IMAGE_BASE + mList.get(0).getSkuImg() + Constant.IMAGE_URL_FINAL).into(holder.iv_photo);
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
        private CustomRoundAngleImageView iv_photo;
        private final TextView tv_name;
        private final TextView tv_num, tv_specifications, tv_price, tv_copies, tv_weight, tv_give_change, tv_stock, tv_money;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv_photo = itemView.findViewById(R.id.iv_photo);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_num = itemView.findViewById(R.id.tv_num);
            tv_specifications = itemView.findViewById(R.id.tv_specifications);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_copies = itemView.findViewById(R.id.tv_copies);
            tv_weight = itemView.findViewById(R.id.tv_weight);
            tv_give_change = itemView.findViewById(R.id.tv_give_change);
            tv_stock = itemView.findViewById(R.id.tv_stock);
            tv_money = itemView.findViewById(R.id.tv_money);
        }
    }
}
