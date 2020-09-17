package com.tuzixiansheng.pda;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itheima.wheelpicker.WheelPicker;
import com.pedaily.yc.ycdialoglib.dialog.loading.ViewLoading;
import com.pedaily.yc.ycdialoglib.toast.ToastUtils;
import com.tuzixiansheng.pda.base.BaseActivity;
import com.tuzixiansheng.pda.base.MyApp;
import com.tuzixiansheng.pda.bean.ModuleBean;
import com.tuzixiansheng.pda.bean.PdaLoginRecord;
import com.tuzixiansheng.pda.bean.ShopBean;
import com.tuzixiansheng.pda.nets.DataRepository;
import com.tuzixiansheng.pda.nets.Injection;
import com.tuzixiansheng.pda.nets.RemotDataSource;
import com.tuzixiansheng.pda.utils.SpUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements View.OnTouchListener {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    @BindView(R.id.tv_welcome)
    TextView tvWelcome;
    @BindView(R.id.et_login_phone)
    EditText etLoginPhone;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.cb_login)
    CheckBox cbLogin;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.wheel_picker)
    WheelPicker wheelPicker;
    @BindView(R.id.ll_shop)
    LinearLayout llShop;
    @BindView(R.id.tv_sure)
    TextView tvSure;
    private DataRepository dataRepository;
    private List<PdaLoginRecord.DataBean.ShopsBean> shops;
    private List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initTransparent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        dataRepository = Injection.dataRepository(this);
        llShop.setOnTouchListener(this);
    }

    private void loginNet(String phone, String pwd) {
        ViewLoading.show(this);
        ModuleBean moduleBean = new ModuleBean();
        moduleBean.username = phone;
        moduleBean.password = pwd;
        dataRepository.pdaLogin(moduleBean, new RemotDataSource.getCallback() {
            @Override
            public void onFailure(String info) {
                ViewLoading.dismiss(LoginActivity.this);
            }

            @Override
            public void onSuccess(Object data) {
                ViewLoading.dismiss(LoginActivity.this);
                PdaLoginRecord pdaLoginRecord = (PdaLoginRecord) data;
                if (pdaLoginRecord.getCode() == 200) {
                    if (pdaLoginRecord.getData() != null) {
                        shops = pdaLoginRecord.getData().getShops();
                        SpUtil.saveString(LoginActivity.this, "token", pdaLoginRecord.getData().getToken());
                        ToastUtils.showToast("登录成功");

                        for (PdaLoginRecord.DataBean.ShopsBean shop : shops) {
                            mList.add(shop.getName());
                            ShopBean shopBean = new ShopBean();
                            shopBean.setShopId(shop.getId());
                            shopBean.setName(shop.getName());
                            shopBean.setPhone(shop.getPhone());
                            shopBean.setLinker(shop.getLinker());
                            shopBean.setDistance(shop.getDistance());
                            shopBean.setCode(shop.getCode());
                            shopBean.setAddress(shop.getAddress());
                            MyApp.getInstance().getDaoSession().getShopBeanDao().insert(shopBean);
                        }
                        if (mList.size() > 0) {
                            llShop.setVisibility(View.VISIBLE);
                            wheelPicker.setData(mList);
                        }
                    }
                } else {
                    ToastUtils.showToast(pdaLoginRecord.getMsg());
                }
            }
        });
    }

    @OnClick({R.id.tv_login, R.id.tv_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                String phone = etLoginPhone.getText().toString();
                String pwd = etPassword.getText().toString();
                phone = "15155119947";
                pwd = "123456";
                String telRegex = "[1][3456789]\\d{9}";
                boolean matches = phone.matches(telRegex);
                if (TextUtils.isEmpty(phone)) {
                    ToastUtils.showToast("请输入手机号码");
                } else if (matches == false) {
                    ToastUtils.showToast("请输入正确的手机号码");
                } else if (TextUtils.isEmpty(pwd)) {
                    ToastUtils.showToast("请输入密码");
                } else {
                    //进行登录的网络请求
                    loginNet(phone, pwd);
                }
                break;
            case R.id.tv_sure:
                SpUtil.saveString(LoginActivity.this, "shopId", shops.get(wheelPicker.getCurrentItemPosition()).getId());
                SpUtil.saveString(LoginActivity.this, "shop", mList.get(wheelPicker.getCurrentItemPosition()));
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }
}