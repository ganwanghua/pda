package com.tuzixiansheng.pda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tuzixiansheng.pda.R;
import com.tuzixiansheng.pda.bean.PickUpListForGoods;
import com.tuzixiansheng.pda.utils.ScreenUtil;
import com.tuzixiansheng.pda.weight.CustomRoundAngleImageView;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {

    private Context mContext;
    private List<String> mList;

    public ImageAdapter(Context context, List<String> mList) {
        this.mContext = context;
        this.mList = mList;
    }

    public interface OnItemClickListener {
        void onItemClick1(int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_image, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (position < mList.size()) {
            //代表+号之前的需要正常显示图片
            String picUrl = mList.get(position); //图片路径
            Glide.with(mContext).load(picUrl).centerCrop().into(holder.pic_iv);
            holder.pic_delete.setVisibility(View.VISIBLE);
        } else {
            holder.pic_delete.setVisibility(View.GONE);
            holder.pic_iv.setImageResource(R.mipmap.photo);//最后一个显示加号图片
            holder.pic_iv.setBackgroundResource(R.drawable.bg_white_5);
            holder.pic_iv.setPadding(ScreenUtil.dip2px(mContext,28), ScreenUtil.dip2px(mContext,28), ScreenUtil.dip2px(mContext,28), ScreenUtil.dip2px(mContext,28));
        }
        holder.pic_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.rl_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick1(position);
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
        int count = mList == null ? 1 : mList.size() + 1;
        if (count > 5) {
            return mList.size();
        } else {
            return count;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final CustomRoundAngleImageView pic_iv;
        private final ImageView pic_delete;
        private RelativeLayout rl_image;

        public MyViewHolder(View itemView) {
            super(itemView);
            pic_iv = itemView.findViewById(R.id.pic_iv);
            pic_delete = itemView.findViewById(R.id.pic_delete);
            rl_image = itemView.findViewById(R.id.rl_image);
        }
    }
}
