package com.tuzixiansheng.pda.aty;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.pedaily.yc.ycdialoglib.toast.ToastUtils;
import com.timmy.tdialog.TDialog;
import com.timmy.tdialog.base.BindViewHolder;
import com.timmy.tdialog.listener.OnBindViewListener;
import com.timmy.tdialog.listener.OnViewClickListener;
import com.tuzixiansheng.pda.R;
import com.tuzixiansheng.pda.adapter.FragmentAdapter;
import com.tuzixiansheng.pda.aty.fragment.PickedFragment;
import com.tuzixiansheng.pda.base.BaseActivity;
import com.tuzixiansheng.pda.utils.SpUtil;
import com.tuzixiansheng.pda.utils.StatusBarUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.DatePicker;

public class PickedActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_clear)
    TextView tvClear;
    @BindView(R.id.ll_phone)
    LinearLayout llPhone;
    @BindView(R.id.tv_today)
    TextView tvToday;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.ll_today)
    LinearLayout llToday;
    @BindView(R.id.tv_three_days)
    TextView tvThreeDays;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.ll_three_days)
    LinearLayout llThreeDays;
    @BindView(R.id.tv_seven_days)
    TextView tvSevenDays;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.ll_seven_days)
    LinearLayout llSevenDays;
    @BindView(R.id.tv_month)
    TextView tvMonth;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.ll_month)
    LinearLayout llMonth;
    @BindView(R.id.tv_other)
    TextView tvOther;
    @BindView(R.id.view4)
    View view4;
    @BindView(R.id.ll_other)
    LinearLayout llOther;
    @BindView(R.id.ll_classify)
    LinearLayout llClassify;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    List<Fragment> fragments = new ArrayList<>();
    private TextView et_start, et_end;
    private TDialog tDialog;
    private int pos = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initWhite();
        StatusBarUtil.StatusBarLightMode(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picked);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        SpUtil.saveString(PickedActivity.this, "type", "1");
        for (int i = 0; i < 5; i++) {
            fragments.add(new PickedFragment(i + 1 + ""));
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
                    pos = 0;
                    view.setVisibility(View.VISIBLE);
                    view1.setVisibility(View.GONE);
                    view2.setVisibility(View.GONE);
                    view3.setVisibility(View.GONE);
                    view4.setVisibility(View.GONE);
                    SpUtil.saveString(PickedActivity.this, "type", "1");
                } else if (position == 1) {
                    pos = 1;
                    view.setVisibility(View.GONE);
                    view1.setVisibility(View.VISIBLE);
                    view2.setVisibility(View.GONE);
                    view3.setVisibility(View.GONE);
                    view4.setVisibility(View.GONE);
                    SpUtil.saveString(PickedActivity.this, "type", "2");
                } else if (position == 2) {
                    pos = 2;
                    view.setVisibility(View.GONE);
                    view1.setVisibility(View.GONE);
                    view2.setVisibility(View.VISIBLE);
                    view3.setVisibility(View.GONE);
                    view4.setVisibility(View.GONE);
                    SpUtil.saveString(PickedActivity.this, "type", "3");
                } else if (position == 3) {
                    pos = 3;
                    view.setVisibility(View.GONE);
                    view1.setVisibility(View.GONE);
                    view2.setVisibility(View.GONE);
                    view3.setVisibility(View.VISIBLE);
                    view4.setVisibility(View.GONE);
                    SpUtil.saveString(PickedActivity.this, "type", "4");
                } else if (position == 4) {
                    view.setVisibility(View.GONE);
                    view1.setVisibility(View.GONE);
                    view2.setVisibility(View.GONE);
                    view3.setVisibility(View.GONE);
                    view4.setVisibility(View.VISIBLE);
                    showDialog();
                    SpUtil.saveString(PickedActivity.this, "type", "5");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void showDialog() {
        tDialog = new TDialog.Builder(getSupportFragmentManager())
                .setLayoutRes(R.layout.dialog_date)
                .setScreenWidthAspect(this, 0.8f)
                .setGravity(Gravity.CENTER)
                .setCancelableOutside(false)
                .addOnClickListener(R.id.tv_cancel, R.id.tv_sure, R.id.et_start, R.id.et_end)
                .setOnBindViewListener(new OnBindViewListener() {
                    @Override
                    public void bindView(BindViewHolder viewHolder) {
                        et_start = viewHolder.itemView.findViewById(R.id.et_start);
                        et_end = viewHolder.itemView.findViewById(R.id.et_end);
                    }
                })
                .setOnViewClickListener(new OnViewClickListener() {
                    @Override
                    public void onViewClick(BindViewHolder viewHolder, View view, TDialog tDialog) {
                        switch (view.getId()) {
                            case R.id.tv_cancel:
                                if (pos == 0) {
                                    view.setVisibility(View.VISIBLE);
                                    view1.setVisibility(View.GONE);
                                    view2.setVisibility(View.GONE);
                                    view3.setVisibility(View.GONE);
                                    view4.setVisibility(View.GONE);
                                } else if (pos == 1) {
                                    view.setVisibility(View.GONE);
                                    view1.setVisibility(View.VISIBLE);
                                    view2.setVisibility(View.GONE);
                                    view3.setVisibility(View.GONE);
                                    view4.setVisibility(View.GONE);
                                } else if (pos == 2) {
                                    view.setVisibility(View.GONE);
                                    view1.setVisibility(View.GONE);
                                    view2.setVisibility(View.VISIBLE);
                                    view3.setVisibility(View.GONE);
                                    view4.setVisibility(View.GONE);
                                } else if (pos == 3) {
                                    view.setVisibility(View.GONE);
                                    view1.setVisibility(View.GONE);
                                    view2.setVisibility(View.GONE);
                                    view3.setVisibility(View.VISIBLE);
                                    view4.setVisibility(View.GONE);
                                }
                                viewPager.setCurrentItem(pos);
                                tDialog.dismiss();
                                break;
                            case R.id.tv_sure:
                                tDialog.dismiss();
                                break;
                            case R.id.et_start:
                                setYearDate(PickedActivity.this);
                                break;
                            case R.id.et_end:
                                setYearDate1(PickedActivity.this);
                                break;
                        }
                    }
                })
                .create()
                .show();
    }

    public void setYearDate(final Activity mContext) {
        DatePicker picker = new DatePicker(mContext);
        //获取当前时间
        Time time = new Time();
        time.setToNow();
        int year = time.year;
        int month = time.month;
        int monthDay = time.monthDay;
        //设置时间区间
        picker.setRange(2000, 2020);
        //设置默认显示时间
        picker.setSelectedItem(year, month + 1, monthDay);
        //加入动画
        picker.setAnimationStyle(R.style.main_menu_anim_style);
        //回传数据
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                et_start.setText(year + "-" + month + "-" + day);
            }
        });
        picker.show();
    }

    public void setYearDate1(final Activity mContext) {
        DatePicker picker = new DatePicker(mContext);
        //获取当前时间
        Time time = new Time();
        time.setToNow();
        int year = time.year;
        int month = time.month;
        int monthDay = time.monthDay;
        //设置时间区间
        picker.setRange(2000, 2020);
        //设置默认显示时间
        picker.setSelectedItem(year, month + 1, monthDay);
        //加入动画
        picker.setAnimationStyle(R.style.main_menu_anim_style);
        //回传数据
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                et_end.setText(year + "-" + month + "-" + day);
            }
        });
        picker.show();
    }

    @OnClick({R.id.iv_back, R.id.tv_cancel, R.id.tv_clear, R.id.ll_today, R.id.ll_three_days, R.id.ll_seven_days, R.id.ll_month, R.id.ll_other})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_cancel:
                if (editPhone.getText().toString().length() > 0) {
                    String telRegex = "[1][3456789]\\d{9}";
                    boolean matches = editPhone.getText().toString().matches(telRegex);
                    if (matches == false) {
                        ToastUtils.showToast("请输入正确的手机号码");
                    } else {

                    }
                } else {
                    ToastUtils.showToast("请输入手机号");
                }
                break;
            case R.id.tv_clear:
                editPhone.setText("");
                break;
            case R.id.ll_today:
                pos = 0;
                view.setVisibility(View.VISIBLE);
                view1.setVisibility(View.GONE);
                view2.setVisibility(View.GONE);
                view3.setVisibility(View.GONE);
                view4.setVisibility(View.GONE);
                viewPager.setCurrentItem(0);
                SpUtil.saveString(PickedActivity.this, "type", "1");
                break;
            case R.id.ll_three_days:
                pos = 1;
                view.setVisibility(View.GONE);
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.GONE);
                view3.setVisibility(View.GONE);
                view4.setVisibility(View.GONE);
                viewPager.setCurrentItem(1);
                SpUtil.saveString(PickedActivity.this, "type", "2");
                break;
            case R.id.ll_seven_days:
                pos = 2;
                view.setVisibility(View.GONE);
                view1.setVisibility(View.GONE);
                view2.setVisibility(View.VISIBLE);
                view3.setVisibility(View.GONE);
                view4.setVisibility(View.GONE);
                viewPager.setCurrentItem(2);
                SpUtil.saveString(PickedActivity.this, "type", "3");
                break;
            case R.id.ll_month:
                pos = 3;
                view.setVisibility(View.GONE);
                view1.setVisibility(View.GONE);
                view2.setVisibility(View.GONE);
                view3.setVisibility(View.VISIBLE);
                view4.setVisibility(View.GONE);
                viewPager.setCurrentItem(3);
                SpUtil.saveString(PickedActivity.this, "type", "4");
                break;
            case R.id.ll_other:
                view.setVisibility(View.GONE);
                view1.setVisibility(View.GONE);
                view2.setVisibility(View.GONE);
                view3.setVisibility(View.GONE);
                view4.setVisibility(View.VISIBLE);
                viewPager.setCurrentItem(4);
                SpUtil.saveString(PickedActivity.this, "type", "5");
                break;
        }
    }
}