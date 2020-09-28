package com.tuzixiansheng.pda.aty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.pedaily.yc.ycdialoglib.toast.ToastUtils;
import com.tuzixiansheng.pda.R;
import com.tuzixiansheng.pda.adapter.FragmentAdapter;
import com.tuzixiansheng.pda.aty.fragment.CollectTodayFragment;
import com.tuzixiansheng.pda.aty.fragment.CommodityFragment;
import com.tuzixiansheng.pda.base.BaseActivity;
import com.tuzixiansheng.pda.bean.NumBean;
import com.tuzixiansheng.pda.utils.StatusBarUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PickedUpActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_dimension)
    TextView tvDimension;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.tv_pick)
    TextView tvPick;
    @BindView(R.id.tv_clear)
    TextView tvClear;
    @BindView(R.id.ll_phone)
    LinearLayout llPhone;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    List<Fragment> fragments = new ArrayList<>();
    List<Fragment> fragments1 = new ArrayList<>();
    @BindView(R.id.tv_today)
    TextView tvToday;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.ll_today)
    LinearLayout llToday;
    @BindView(R.id.tv_history)
    TextView tvHistory;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.ll_history)
    LinearLayout llHistory;
    @BindView(R.id.tv_picking)
    TextView tvPicking;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.ll_picking)
    LinearLayout llPicking;
    @BindView(R.id.ll_classify)
    LinearLayout llClassify;
    @BindView(R.id.rl_user)
    RelativeLayout rlUser;
    @BindView(R.id.edit_sku)
    EditText editSku;
    @BindView(R.id.tv_query)
    TextView tvQuery;
    @BindView(R.id.tv_clear_one)
    TextView tvClearOne;
    @BindView(R.id.ll_sku)
    LinearLayout llSku;
    @BindView(R.id.tv_today_one)
    TextView tvTodayOne;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.ll_today_one)
    LinearLayout llTodayOne;
    @BindView(R.id.tv_history_one)
    TextView tvHistoryOne;
    @BindView(R.id.view4)
    View view4;
    @BindView(R.id.ll_history_one)
    LinearLayout llHistoryOne;
    @BindView(R.id.ll_classify_one)
    LinearLayout llClassifyOne;
    @BindView(R.id.viewPager1)
    ViewPager viewPager1;
    @BindView(R.id.rl_commodity)
    RelativeLayout rlCommodity;
    private boolean isClick = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initWhite();
        StatusBarUtil.StatusBarLightMode(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picked_up);
        ButterKnife.bind(this);
        initView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 100, sticky = false) //在ui线程执行，优先级为100
    public void onEvent(NumBean numBean) {
        if (numBean.getType().equals("1")) {
            tvToday.setText("当日待取(" + numBean.getNum() + ")");
        } else if (numBean.getType().equals("2")) {
            tvHistory.setText("昨日待取(" + numBean.getNum() + ")");
        } else if (numBean.getType().equals("3")) {
            tvPicking.setText("取货中(" + numBean.getNum() + ")");
        } else if (numBean.getType().equals("4")) {
            tvTodayOne.setText("当日待取(" + numBean.getNum() + ")");
        }else if (numBean.getType().equals("5")) {
            tvHistoryOne.setText("昨日待取(" + numBean.getNum() + ")");
        }
    }

    private void initView() {
        for (int i = 0; i < 3; i++) {
            fragments.add(new CollectTodayFragment(i + 1 + ""));
        }
        FragmentAdapter adatper = new FragmentAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adatper);
        viewPager.setOffscreenPageLimit(fragments.size());
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    view.setVisibility(View.VISIBLE);
                    view1.setVisibility(View.GONE);
                    view2.setVisibility(View.GONE);
                } else if (position == 1) {
                    view.setVisibility(View.GONE);
                    view1.setVisibility(View.VISIBLE);
                    view2.setVisibility(View.GONE);
                } else if (position == 2) {
                    view.setVisibility(View.GONE);
                    view1.setVisibility(View.GONE);
                    view2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        for (int i = 0; i < 2; i++) {
            fragments1.add(new CommodityFragment(i + 1 + ""));
        }
        FragmentAdapter adatper1 = new FragmentAdapter(getSupportFragmentManager(), fragments1);
        viewPager1.setAdapter(adatper1);
        viewPager1.setOffscreenPageLimit(fragments1.size());
        viewPager1.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    view3.setVisibility(View.VISIBLE);
                    view4.setVisibility(View.GONE);
                } else if (position == 1) {
                    view3.setVisibility(View.GONE);
                    view4.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick({R.id.iv_back, R.id.ll_today, R.id.ll_history, R.id.ll_picking, R.id.tv_pick,
            R.id.tv_clear, R.id.tv_dimension, R.id.tv_query, R.id.tv_clear_one, R.id.ll_today_one, R.id.ll_history_one})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_today:
                view.setVisibility(View.VISIBLE);
                view1.setVisibility(View.GONE);
                view2.setVisibility(View.GONE);
                viewPager.setCurrentItem(0);
                break;
            case R.id.ll_history:
                view.setVisibility(View.GONE);
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.GONE);
                viewPager.setCurrentItem(1);
                break;
            case R.id.ll_picking:
                view.setVisibility(View.GONE);
                view1.setVisibility(View.GONE);
                view2.setVisibility(View.VISIBLE);
                viewPager.setCurrentItem(2);
                break;
            case R.id.tv_pick:
                if (editPhone.getText().toString().length() > 0) {
                    String telRegex = "[1][3456789]\\d{9}";
                    boolean matches = editPhone.getText().toString().matches(telRegex);
                    if (matches == false) {
                        ToastUtils.showToast("请输入正确的手机号码");
                    } else {
                        Intent intent = new Intent(this, PickingUpDetailActivity.class);
                        intent.putExtra("phone", editPhone.getText().toString());
                        intent.putExtra("pos", "1");
                        startActivity(intent);
                    }
                } else {
                    ToastUtils.showToast("请输入手机号");
                }
                break;
            case R.id.tv_clear:
                editPhone.setText("");
                break;
            case R.id.tv_clear_one:
                editSku.setText("");
                break;
            case R.id.tv_dimension:
                if (isClick) {
                    isClick = false;
                    tvDimension.setText("用户维度");
                    rlUser.setVisibility(View.GONE);
                    rlCommodity.setVisibility(View.VISIBLE);
                } else {
                    isClick = true;
                    tvDimension.setText("商品维度");
                    rlUser.setVisibility(View.VISIBLE);
                    rlCommodity.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_query:
                if (editSku.getText().toString().length() > 0) {

                } else {
                    ToastUtils.showToast("请输入SKU编码");
                }
                break;
            case R.id.ll_today_one:
                view3.setVisibility(View.VISIBLE);
                view4.setVisibility(View.GONE);
                viewPager1.setCurrentItem(0);
                break;
            case R.id.ll_history_one:
                view3.setVisibility(View.GONE);
                view4.setVisibility(View.VISIBLE);
                viewPager1.setCurrentItem(1);
                break;
        }
    }
}