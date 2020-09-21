package com.tuzixiansheng.pda.aty;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pedaily.yc.ycdialoglib.toast.ToastUtils;
import com.tuzixiansheng.pda.R;
import com.tuzixiansheng.pda.adapter.WaitAgoAdapter;
import com.tuzixiansheng.pda.adapter.WaitTodayAdapter;
import com.tuzixiansheng.pda.base.BaseActivity;
import com.tuzixiansheng.pda.base.MyApp;
import com.tuzixiansheng.pda.bean.ModuleBean;
import com.tuzixiansheng.pda.bean.PickUpDetailRecord;
import com.tuzixiansheng.pda.nets.DataRepository;
import com.tuzixiansheng.pda.nets.Injection;
import com.tuzixiansheng.pda.nets.RemotDataSource;
import com.tuzixiansheng.pda.utils.SpUtil;
import com.tuzixiansheng.pda.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PickingUpDetailActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.tv_pick)
    TextView tvPick;
    @BindView(R.id.tv_wait_today)
    TextView tvWaitToday;
    @BindView(R.id.rv_wait_today)
    RecyclerView rvWaitToday;
    @BindView(R.id.tv_wait_history)
    TextView tvWaitHistory;
    @BindView(R.id.rv_wait_ago)
    RecyclerView rvWaitAgo;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;
    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;
    private WaitTodayAdapter waitTodayAdapter;
    private WaitAgoAdapter waitAgoAdapter;
    private List<PickUpDetailRecord.DataBean> todayList = new ArrayList<>();
    private List<PickUpDetailRecord.DataBean> historyList = new ArrayList<>();
    private BroadcastReceiver mReceiver;
    private IntentFilter mFilter;
    private DataRepository dataRepository;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initWhite();
        StatusBarUtil.StatusBarLightMode(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picking_up);
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
        MyApp.Controll.close();
        super.onDestroy();
    }

    private void initView() {
        dataRepository = Injection.dataRepository(this);
        phone = getIntent().getStringExtra("phone");
        tvTitle.setText("待取货(" + phone + ")");
        waitTodayAdapter = new WaitTodayAdapter(this);
        rvWaitToday.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvWaitToday.setAdapter(waitTodayAdapter);

        waitAgoAdapter = new WaitAgoAdapter(this);
        rvWaitAgo.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvWaitAgo.setAdapter(waitAgoAdapter);

        pickUpDetail();

        mFilter = new IntentFilter("android.intent.action.SCANRESULT");
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                final String scanResult = intent.getStringExtra("value");
                Log.d("dsadsadsa",scanResult);
                //                mTvScanResult.append(scanResult);
//                mTvScanResult.invalidate();
            }
        };
    }

    private void pickUpDetail() {
        ModuleBean moduleBean = new ModuleBean();
        moduleBean.phone = phone;
        String token = SpUtil.getString(this, "token", "");
        dataRepository.pickUpDetail(token, moduleBean, new RemotDataSource.getCallback() {
            @Override
            public void onFailure(String info) {

            }

            @Override
            public void onSuccess(Object data) {
                PickUpDetailRecord pickUpDetailRecord = (PickUpDetailRecord) data;
                if (pickUpDetailRecord.getCode() == 200) {
                    List<PickUpDetailRecord.DataBean> data1 = pickUpDetailRecord.getData();
                    if (data1 == null || data1.size() == 0) {
                        tvEmpty.setVisibility(View.VISIBLE);
                        nestedScrollView.setVisibility(View.GONE);
                        tvPick.setVisibility(View.GONE);
                        return;
                    } else {
                        tvEmpty.setVisibility(View.GONE);
                        nestedScrollView.setVisibility(View.VISIBLE);
                        tvPick.setVisibility(View.VISIBLE);
                        for (PickUpDetailRecord.DataBean datum : data1) {
                            if (datum.isIsNow() == true) {
                                todayList.add(datum);
                            } else {
                                historyList.add(datum);
                            }
                        }
                    }

                    if (todayList.size() == 0) {
                        tvWaitToday.setVisibility(View.GONE);
                        rvWaitToday.setVisibility(View.GONE);
                    } else if (historyList.size() == 0) {
                        tvWaitHistory.setVisibility(View.GONE);
                        rvWaitAgo.setVisibility(View.GONE);
                    }

                    waitTodayAdapter.setData(todayList);
                    waitAgoAdapter.setData(historyList);

                } else {
                    ToastUtils.showToast(pickUpDetailRecord.getMsg());
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
//        startActivity(new Intent(this, PickedUpActivity.class));
        finish();
    }

    @OnClick({R.id.iv_back, R.id.tv_pick})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
//                startActivity(new Intent(this, PickedUpActivity.class));
                finish();
                break;
            case R.id.tv_pick:
                break;
        }
    }
}