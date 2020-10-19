package com.tuzixiansheng.pda;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itheima.wheelpicker.WheelPicker;
import com.pedaily.yc.ycdialoglib.toast.ToastUtils;
import com.tbruyelle.rxpermissions3.RxPermissions;
import com.tuzixiansheng.pda.aty.PickedActivity;
import com.tuzixiansheng.pda.aty.PickedUpActivity;
import com.tuzixiansheng.pda.aty.PickingUpDetailActivity;
import com.tuzixiansheng.pda.aty.ReturnGoodsActivity;
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
    @BindView(R.id.ll_return_goods)
    LinearLayout llReturnGoods;
    private List<ShopBean> historyList;
    private List<String> mList = new ArrayList<>();
    private BroadcastReceiver mReceiver;
    private IntentFilter mFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initWhite();
        StatusBarUtil.StatusBarLightMode(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mReceiver, mFilter);
    }

    @Override
    protected void onPause() {
        this.unregisterReceiver(mReceiver);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mReceiver = null;
        mFilter = null;
//        MyApp.Controll.close();
        super.onDestroy();
    }

    private void initView() {
        rxPermissionTest();
        homeAddress.setText(SpUtil.getString(this, "shop", ""));
        mFilter = new IntentFilter("android.intent.action.SCANRESULT");
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                final String scanResult = intent.getStringExtra("value");
                Intent intent1 = new Intent(MainActivity.this, PickingUpDetailActivity.class);
                intent1.putExtra("code", scanResult);
                intent1.putExtra("pos", "2");
                startActivity(intent1);
            }
        };
    }

    private void rxPermissionTest() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) {
                        // I can control the camera now
                    } else {
                        // Oups permission denied
                    }
                });
    }

    @OnClick({R.id.rl_location, R.id.tv_sure, R.id.tv_quit, R.id.ll_mine_contact, R.id.view, R.id.view1,
            R.id.tv_pick_up, R.id.ll_mine_about, R.id.ll_mine_help, R.id.ll_return_goods})
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
                SpUtil.saveInt(this, "shopId", Integer.parseInt(historyList.get(wheelPicker.getCurrentItemPosition()).getShopId()));
                SpUtil.saveString(this, "shop", mList.get(wheelPicker.getCurrentItemPosition()));
                homeAddress.setText(mList.get(wheelPicker.getCurrentItemPosition()));
                llShop.setVisibility(View.GONE);
                mList.clear();
                wheelPicker.setData(mList);
                break;
            case R.id.tv_quit:
                SpUtil.saveString(this, "isLogin", "0");
                MyApp.getInstance().getDaoSession().getShopBeanDao().queryBuilder().buildDelete().executeDeleteWithoutDetachingEntities();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
            case R.id.ll_mine_contact:
                llPickUp.setVisibility(View.VISIBLE);
                editPhone.setFocusableInTouchMode(true);
                editPhone.setFocusable(true);
                editPhone.requestFocus();
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
                    } else {
                        llPickUp.setVisibility(View.GONE);
                        Intent intent = new Intent(this, PickingUpDetailActivity.class);
                        intent.putExtra("code", editPhone.getText().toString());
                        intent.putExtra("pos", "2");
                        startActivity(intent);
                    }
                } else {
                    ToastUtils.showToast("请输入手机号");
                }
                break;
            case R.id.ll_mine_about:
                startActivity(new Intent(this, PickedUpActivity.class));
                break;
            case R.id.ll_mine_help:
                startActivity(new Intent(this, PickedActivity.class));
                break;
            case R.id.ll_return_goods:
                startActivity(new Intent(this, ReturnGoodsActivity.class));
                break;
        }
    }
}