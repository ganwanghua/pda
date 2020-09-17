package com.tuzixiansheng.pda.base;

import android.content.Context;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.tuzixiansheng.pda.R;
import com.tuzixiansheng.pda.receiver.NetUtils;
import com.tuzixiansheng.pda.receiver.NetworkChangeEvent;
import com.tuzixiansheng.pda.receiver.NetworkConnectChangedReceiver;
import com.tuzixiansheng.pda.utils.StatusBarUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class BaseActivity extends AppCompatActivity {
    protected Context mContext;
    protected boolean mCheckNetWork = true; //默认检查网络状态
    View mTipView;
    WindowManager mWindowManager;
    WindowManager.LayoutParams mLayoutParams;
    private NetworkConnectChangedReceiver netBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        StatusBarUtil.StatusBarLightMode(this);
        initTipView();//初始化提示View
        EventBus.getDefault().register(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //实例化IntentFilter对象
            IntentFilter filter = new IntentFilter();
            filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            netBroadcastReceiver = new NetworkConnectChangedReceiver();
            //注册广播接收
            registerReceiver(netBroadcastReceiver, filter);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在无网络情况下打开APP时，系统不会发送网络状况变更的Intent，需要自己手动检查
        hasNetWork(NetUtils.isConnected(mContext));
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        unregisterReceiver(netBroadcastReceiver);
    }

    @Override
    public void finish() {
        super.finish();
        //当提示View被动态添加后直接关闭页面会导致该View内存溢出，所以需要在finish时移除
        if (mTipView != null && mTipView.getParent() != null) {
            mWindowManager.removeView(mTipView);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNetworkChangeEvent(NetworkChangeEvent event) {
        hasNetWork(event.isConnected);
    }

    private void hasNetWork(boolean has) {
        if (isCheckNetWork()) {
            if (has) {
                if (mTipView != null && mTipView.getParent() != null) {
                    mWindowManager.removeView(mTipView);
                    EventBus.getDefault().post("9");
                }
            } else {
                if (mTipView.getParent() == null) {
                    mWindowManager.addView(mTipView, mLayoutParams);
                    EventBus.getDefault().post("10");
                }
            }
        }
    }

    public void setCheckNetWork(boolean checkNetWork) {
        mCheckNetWork = checkNetWork;
    }

    public boolean isCheckNetWork() {
        return mCheckNetWork;
    }

    private void initTipView() {
        LayoutInflater inflater = getLayoutInflater();
        mTipView = inflater.inflate(R.layout.layout_network_tip, null); //提示View布局
        mWindowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        mLayoutParams = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                PixelFormat.TRANSLUCENT);
        //使用非CENTER时，可以通过设置XY的值来改变View的位置
        mLayoutParams.gravity = Gravity.TOP;
        mLayoutParams.x = 0;
        mLayoutParams.y = 0;
    }

    public void initTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.transparent));
        }
    }

    public void initWhite() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.white));
        }
    }
}
