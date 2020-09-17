package com.tuzixiansheng.pda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tuzixiansheng.pda.R;
import com.tuzixiansheng.pda.bean.TitleBean;

import java.util.List;

public class CollectTodayAdapter extends RecyclerView.Adapter<CollectTodayAdapter.MyViewHolder> {

    private Context mContext;
    private List<TitleBean> mList;
    private MyItemClickListener mItemClickListener;

    public interface MyItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setItemClickListener(MyItemClickListener myItemClickListener) {
        this.mItemClickListener = myItemClickListener;
    }

    public CollectTodayAdapter(Context context, List<TitleBean> mList) {
        this.mContext = context;
        this.mList = mList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_classify, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

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
        private TextView tv_today;
        private View view_today;
        private LinearLayout ll_today;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_today = itemView.findViewById(R.id.tv_today);
            view_today = itemView.findViewById(R.id.view_today);
            ll_today = itemView.findViewById(R.id.ll_today);
        }
    }
}
