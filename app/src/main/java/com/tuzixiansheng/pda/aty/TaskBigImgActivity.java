package com.tuzixiansheng.pda.aty;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.tuzixiansheng.pda.R;
import com.tuzixiansheng.pda.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskBigImgActivity extends BaseActivity {

    @BindView(R.id.big_img_vp)
    ViewPager bigImgVp;
    private List<String> paths;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initTransparent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_big_img);
        ButterKnife.bind(this);
        position = getIntent().getIntExtra("position", -1);
        paths = getIntent().getStringArrayListExtra("paths");
        initView();
    }

    private void initView() {
        bigImgVp.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return paths == null ? 0 : paths.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object o) {
                return view == o;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View adView = LayoutInflater.from(TaskBigImgActivity.this).inflate(R.layout.item_big_img, null);
                PhotoView icon = (PhotoView) adView.findViewById(R.id.flaw_img);
                icon.setBackgroundColor(Color.parseColor("#333333"));
                Glide.with(TaskBigImgActivity.this)
                        .load(paths.get(position))
                        .into(icon);
                container.addView(adView);
                icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                return adView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });

        bigImgVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });

        bigImgVp.setCurrentItem(position, true);
    }
}