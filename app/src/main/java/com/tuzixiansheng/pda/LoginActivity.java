package com.tuzixiansheng.pda;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import com.tuzixiansheng.pda.bean.PdaShopList;
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
    private List<PdaShopList.DataBean> shops;
    private List<String> mList = new ArrayList<>();
    private boolean isClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initTransparent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        if (SpUtil.getString(this, "isLogin", "").equals("1")) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        etLoginPhone.setText(SpUtil.getString(this, "username", ""));
        etPassword.setText(SpUtil.getString(this, "password", ""));
        if (SpUtil.getString(LoginActivity.this, "isChecked", "").equals("1")) {
            cbLogin.setChecked(true);
            isClick = true;
        } else {
            cbLogin.setChecked(false);
            isClick = false;
        }
        dataRepository = Injection.dataRepository(this);
        llShop.setOnTouchListener(this);
        cbLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isClick = true;
                    SpUtil.saveString(LoginActivity.this, "isChecked", "1");
                } else {
                    isClick = false;
                    SpUtil.saveString(LoginActivity.this, "isChecked", "0");
                }
            }
        });
    }

    private void loginNet(String phone, String pwd) {
        ViewLoading.show(this);
        ModuleBean moduleBean = new ModuleBean();
        moduleBean.account = phone;
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
                if (pdaLoginRecord.getCode() == 1) {
                    if (pdaLoginRecord.getData() != null) {
                        SpUtil.saveString(LoginActivity.this, "username", phone);
                        if (isClick) {
                            SpUtil.saveString(LoginActivity.this, "password", pwd);
                        } else {
                            SpUtil.saveString(LoginActivity.this, "password", "");
                        }
                        if(pdaLoginRecord.getData().getIs_store() == 1){
                            SpUtil.saveString(LoginActivity.this, "token", pdaLoginRecord.getData().getUserinfo().getToken());
                            SpUtil.saveString(LoginActivity.this, "isLogin", "1");
                            ToastUtils.showToast("登录成功");
                            pdaList(pdaLoginRecord.getData().getUserinfo().getToken());
                        }else {
                            ToastUtils.showToast("暂无权限");
                        }
                    }
                } else {
                    ToastUtils.showToast(pdaLoginRecord.getMsg());
                }
            }
        });
    }


    private void pdaList(String token) {
        dataRepository.pdaList(token, new RemotDataSource.getCallback() {
            @Override
            public void onFailure(String info) {

            }

            @Override
            public void onSuccess(Object data) {
                PdaShopList pdaShopList = (PdaShopList) data;
                if (pdaShopList.getCode() == 1) {
                    if (pdaShopList.getData() != null) {
                        shops = pdaShopList.getData();
                        for (PdaShopList.DataBean shop : shops) {
                            mList.add(shop.getName());
                            ShopBean shopBean = new ShopBean();
                            shopBean.setShopId(shop.getId() + "");
                            shopBean.setName(shop.getName());
                            shopBean.setPhone(shop.getPhone());
                            shopBean.setAddress(shop.getAddress());
                            MyApp.getInstance().getDaoSession().getShopBeanDao().insert(shopBean);
                        }
                        if (mList.size() > 1) {
                            llShop.setVisibility(View.VISIBLE);
                            wheelPicker.setData(mList);
                        } else if (mList.size() == 1) {
                            SpUtil.saveInt(LoginActivity.this, "shopId", shops.get(0).getId());
                            SpUtil.saveString(LoginActivity.this, "shop", mList.get(0));
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        }
                    }
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
                SpUtil.saveInt(LoginActivity.this, "shopId", shops.get(wheelPicker.getCurrentItemPosition()).getId());
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