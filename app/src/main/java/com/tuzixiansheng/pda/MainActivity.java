package com.tuzixiansheng.pda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itheima.wheelpicker.WheelPicker;
import com.pedaily.yc.ycdialoglib.toast.ToastUtils;
import com.tuzixiansheng.pda.aty.PickingUpActivity;
import com.tuzixiansheng.pda.base.BaseActivity;
import com.tuzixiansheng.pda.base.MyApp;
import com.tuzixiansheng.pda.bean.ShopBean;
import com.tuzixiansheng.pda.greendao.ShopBeanDao;
import com.tuzixiansheng.pda.utils.SpUtil;
import com.tuzixiansheng.pda.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.home_address)
    TextView homeAddress;
    @BindView(R.id.rl_location)
    RelativeLayout rlLocation;
    @BindView(R.id.iv_down)
    ImageView ivDown;
    @BindView(R.id.rl_down)
    RelativeLayout rlDown;
    @BindView(R.id.rl_shop)
    RelativeLayout rlShop;
    @BindView(R.id.tv_mine_order)
    TextView tvMineOrder;
    @BindView(R.id.ll_mine_contact)
    LinearLayout llMineContact;
    @BindView(R.id.ll_mine_about)
    LinearLayout llMineAbout;
    @BindView(R.id.ll_mine_help)
    LinearLayout llMineHelp;
    @BindView(R.id.tv_sure)
    TextView tvSure;
    @BindView(R.id.wheel_picker)
    WheelPicker wheelPicker;
    @BindView(R.id.ll_shop)
    RelativeLayout llShop;
    @BindView(R.id.tv_quit)
    TextView tvQuit;
    @BindView(R.id.tv_pick_up)
    TextView tvPickUp;
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.ll_pick_up)
    RelativeLayout llPickUp;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.view1)
    View view1;
    private List<ShopBean> historyList;
    private List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initWhite();
        StatusBarUtil.StatusBarLightMode(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        homeAddress.setText(SpUtil.getString(this, "shop", ""));
    }

    @OnClick({R.id.rl_location, R.id.tv_sure, R.id.tv_quit, R.id.ll_mine_contact, R.id.view, R.id.view1, R.id.tv_pick_up})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_location:
                historyList = MyApp.getInstance().getDaoSession().getShopBeanDao().queryBuilder().orderDesc(ShopBeanDao.Properties.Id).list();
                for (int i = 0; i < historyList.size(); i++) {
                    mList.add(historyList.get(i).getName());
                    if (homeAddress.getText().toString().equals(historyList.get(i).getName())) {
                        llShop.setVisibility(View.VISIBLE);
                        wheelPicker.setData(mList);
                        wheelPicker.setSelectedItemPosition(i);
                    }
                }
                break;
            case R.id.tv_sure:
                SpUtil.saveString(this, "shopId", historyList.get(wheelPicker.getCurrentItemPosition()).getShopId());
                SpUtil.saveString(this, "shop", mList.get(wheelPicker.getCurrentItemPosition()));
                homeAddress.setText(mList.get(wheelPicker.getCurrentItemPosition()));
                llShop.setVisibility(View.GONE);
                mList.clear();
                wheelPicker.setData(mList);
                break;
            case R.id.tv_quit:
                MyApp.getInstance().getDaoSession().getShopBeanDao().queryBuilder().buildDelete().executeDeleteWithoutDetachingEntities();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
            case R.id.ll_mine_contact:
                llPickUp.setVisibility(View.VISIBLE);
                break;
            case R.id.view:
                llPickUp.setVisibility(View.GONE);
                break;
            case R.id.view1:
                llShop.setVisibility(View.GONE);
                mList.clear();
                wheelPicker.setData(mList);
                break;
            case R.id.tv_pick_up:
                if (editPhone.getText().toString().length() > 0) {
                    String telRegex = "[1][3456789]\\d{9}";
                    boolean matches = editPhone.getText().toString().matches(telRegex);
                    if (matches == false) {
                        ToastUtils.showToast("请输入正确的手机号码");
                    }else {
                        llPickUp.setVisibility(View.GONE);
                        Intent intent = new Intent(this, PickingUpActivity.class);
                        intent.putExtra("phone", editPhone.getText().toString());
                        startActivity(intent);
                    }
                }else {
                    ToastUtils.showToast("请输入手机号");
                }
                break;
        }
    }
}