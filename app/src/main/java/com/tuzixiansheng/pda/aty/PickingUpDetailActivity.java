package com.tuzixiansheng.pda.aty;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lling.photopicker.PhotoPickerActivity;
import com.pedaily.yc.ycdialoglib.toast.ToastUtils;
import com.timmy.tdialog.TDialog;
import com.timmy.tdialog.base.BindViewHolder;
import com.timmy.tdialog.listener.OnBindViewListener;
import com.timmy.tdialog.listener.OnViewClickListener;
import com.tuzixiansheng.pda.R;
import com.tuzixiansheng.pda.adapter.ImageAdapter;
import com.tuzixiansheng.pda.adapter.OriginalDeliveryAdapter;
import com.tuzixiansheng.pda.adapter.PickedUpAdapter;
import com.tuzixiansheng.pda.adapter.WaitAgoAdapter;
import com.tuzixiansheng.pda.adapter.WaitTodayAdapter;
import com.tuzixiansheng.pda.base.BaseActivity;
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

public class PickingUpDetailActivity extends BaseActivity implements WaitTodayAdapter.OnItemClickListener, WaitAgoAdapter.OnItemClickListener {

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
    @BindView(R.id.card)
    CardView card;
    @BindView(R.id.card1)
    CardView card1;
    @BindView(R.id.tv_picked_up)
    TextView tvPickedUp;
    @BindView(R.id.rv_picked_up)
    RecyclerView rvPickedUp;
    @BindView(R.id.card2)
    CardView card2;
    private WaitTodayAdapter waitTodayAdapter;
    private WaitAgoAdapter waitAgoAdapter;
    private PickedUpAdapter pickedUpAdapter;
    private OriginalDeliveryAdapter originalDeliveryAdapter;
    private List<PickUpDetailRecord.DataBean> todayList = new ArrayList<>();
    private List<PickUpDetailRecord.DataBean> historyList = new ArrayList<>();
    private BroadcastReceiver mReceiver;
    private IntentFilter mFilter;
    private DataRepository dataRepository;
    private String phone;
    private TextView tv_out_of_stock, tv_original_delivery, tv_poor_quality, tv_replacement, tv_cancel, tv_sure;
    private int pos;
    private TDialog tDialog, tDialog1, tDialog2, tDialog3, tDialog4;
    private String pos1;
    private LinearLayout ll_sure;
    private ImageAdapter imageAdapter;
    private ArrayList<String> mPicList = new ArrayList<>();
    private RecyclerView recycler_view;

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
//        MyApp.Controll.close();
        super.onDestroy();
    }

    private void initGridView() {
        imageAdapter = new ImageAdapter(this, mPicList);
        recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recycler_view.setAdapter(imageAdapter);
        imageAdapter.setOnItemClickListener(new ImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick1(int position) {
                if (position == mPicList.size()) {
                    //如果“增加按钮形状的”图片的位置是最后一张，且添加了的图片的数量不超过5张，才能点击
                    if (mPicList.size() == 5) {
                        viewPluImg(position);
                    } else {
                        //添加凭证图片
                        selectPic();
                    }
                }else {
                    viewPluImg(position);
                }
            }
        });
    }

    private void viewPluImg(int index) {
        Intent imgIntent = new Intent(this, TaskBigImgActivity.class);
        imgIntent.putStringArrayListExtra("paths", mPicList);
        imgIntent.putExtra("position", index);
        startActivity(imgIntent);
    }

    private void selectPic() {
        Intent intent = new Intent(this, PhotoPickerActivity.class);
        intent.putExtra(PhotoPickerActivity.EXTRA_SHOW_CAMERA, true); // 是否显示相机
        intent.putExtra(PhotoPickerActivity.EXTRA_SELECT_MODE, PhotoPickerActivity.MODE_MULTI); // 选择模式（默认多选模式）
        intent.putExtra(PhotoPickerActivity.EXTRA_MAX_MUN, 5 - mPicList.size()); // 最大照片张数
        startActivityForResult(intent, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1000:
                    // 图片选择成功
                    mPicList.addAll(data.getStringArrayListExtra(PhotoPickerActivity.KEY_RESULT));
                    initGridView();
                    break;
            }
        }
    }

    private void initView() {
        dataRepository = Injection.dataRepository(this);
        phone = getIntent().getStringExtra("phone");
        pos1 = getIntent().getStringExtra("pos");
        tvTitle.setText("待取货(" + phone + ")");
        waitTodayAdapter = new WaitTodayAdapter(this);
        rvWaitToday.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvWaitToday.setAdapter(waitTodayAdapter);
        waitTodayAdapter.setOnItemClickListener(this);

        waitAgoAdapter = new WaitAgoAdapter(this);
        rvWaitAgo.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvWaitAgo.setAdapter(waitAgoAdapter);
        waitAgoAdapter.setOnItemClickListener(this);

        pickedUpAdapter = new PickedUpAdapter(this);
        rvPickedUp.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvPickedUp.setAdapter(pickedUpAdapter);

        pickUpDetail();

        mFilter = new IntentFilter("android.intent.action.SCANRESULT");
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                final String scanResult = intent.getStringExtra("value");
                Log.d("dsadsadsa", scanResult);
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
                        card.setVisibility(View.GONE);
                    } else if (historyList.size() == 0) {
                        tvWaitHistory.setVisibility(View.GONE);
                        card1.setVisibility(View.GONE);
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
        if (pos1.equals("1")) {
            finish();
        } else {
            startActivity(new Intent(this, PickedUpActivity.class));
            finish();
        }
    }

    @OnClick({R.id.iv_back, R.id.tv_pick})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                if (pos1.equals("1")) {
                    finish();
                } else {
                    startActivity(new Intent(this, PickedUpActivity.class));
                    finish();
                }
                break;
            case R.id.tv_pick:
                showDialog();
                break;
        }
    }

    private void showDialog() {
        tDialog = new TDialog.Builder(getSupportFragmentManager())
                .setLayoutRes(R.layout.dialog_pick_up)
                .setScreenWidthAspect(this, 0.8f)
                .setGravity(Gravity.CENTER)
                .setCancelableOutside(false)
                .setOnBindViewListener(new OnBindViewListener() {
                    @Override
                    public void bindView(BindViewHolder viewHolder) {
                        TextView time = viewHolder.itemView.findViewById(R.id.time);
                        new CountDownTimer(4000, 1000) {
                            public void onTick(long millisUntilFinished) {
                                time.setText(millisUntilFinished / 1000 + "S");
                            }

                            public void onFinish() {
                                tDialog.dismiss();
                            }
                        }.start();
                    }
                })
                .create()
                .show();
    }

    @Override
    public void onItemClick(int position) {
        tDialog1 = new TDialog.Builder(getSupportFragmentManager())
                .setLayoutRes(R.layout.dialog_operation)
                .setScreenWidthAspect(this, 0.8f)
                .setGravity(Gravity.CENTER)
                .addOnClickListener(R.id.tv_out_of_stock, R.id.tv_original_delivery, R.id.tv_poor_quality,
                        R.id.tv_replacement, R.id.tv_cancel, R.id.tv_sure, R.id.ll_sure)
                .setOnBindViewListener(new OnBindViewListener() {
                    @Override
                    public void bindView(BindViewHolder viewHolder) {
                        tv_out_of_stock = viewHolder.itemView.findViewById(R.id.tv_out_of_stock);
                        tv_original_delivery = viewHolder.itemView.findViewById(R.id.tv_original_delivery);
                        tv_poor_quality = viewHolder.itemView.findViewById(R.id.tv_poor_quality);
                        tv_replacement = viewHolder.itemView.findViewById(R.id.tv_replacement);
                        tv_cancel = viewHolder.itemView.findViewById(R.id.tv_cancel);
                        tv_sure = viewHolder.itemView.findViewById(R.id.tv_sure);
                        ll_sure = viewHolder.itemView.findViewById(R.id.ll_sure);
                    }
                })
                .setOnViewClickListener(new OnViewClickListener() {
                    @Override
                    public void onViewClick(BindViewHolder viewHolder, View view, TDialog tDialog) {
                        switch (view.getId()) {
                            case R.id.tv_out_of_stock:
                                pos = 1;
                                tv_out_of_stock.setBackgroundResource(R.drawable.bg_green);
                                tv_original_delivery.setBackgroundResource(R.drawable.bg_white_5);
                                tv_poor_quality.setBackgroundResource(R.drawable.bg_white_5);
                                tv_replacement.setBackgroundResource(R.drawable.bg_white_5);
                                ll_sure.setVisibility(View.VISIBLE);
                                break;
                            case R.id.tv_original_delivery:
                                pos = 2;
                                tv_out_of_stock.setBackgroundResource(R.drawable.bg_white_5);
                                tv_original_delivery.setBackgroundResource(R.drawable.bg_green);
                                tv_poor_quality.setBackgroundResource(R.drawable.bg_white_5);
                                tv_replacement.setBackgroundResource(R.drawable.bg_white_5);
                                ll_sure.setVisibility(View.VISIBLE);
                                break;
                            case R.id.tv_poor_quality:
                                pos = 3;
                                tv_out_of_stock.setBackgroundResource(R.drawable.bg_white_5);
                                tv_original_delivery.setBackgroundResource(R.drawable.bg_white_5);
                                tv_poor_quality.setBackgroundResource(R.drawable.bg_green);
                                tv_replacement.setBackgroundResource(R.drawable.bg_white_5);
                                ll_sure.setVisibility(View.VISIBLE);
                                break;
                            case R.id.tv_replacement:
                                pos = 4;
                                tv_out_of_stock.setBackgroundResource(R.drawable.bg_white_5);
                                tv_original_delivery.setBackgroundResource(R.drawable.bg_white_5);
                                tv_poor_quality.setBackgroundResource(R.drawable.bg_white_5);
                                tv_replacement.setBackgroundResource(R.drawable.bg_green);
                                ll_sure.setVisibility(View.VISIBLE);
                                break;
                            case R.id.tv_cancel:
                                tDialog1.dismiss();
                                break;
                            case R.id.tv_sure:
                                if (pos == 1) {
                                    tDialog1.dismiss();
                                } else if (pos == 2) {
                                    tDialog1.dismiss();
                                    tDialog2 = new TDialog.Builder(getSupportFragmentManager())
                                            .setLayoutRes(R.layout.dialog_original_delivery)
                                            .setScreenWidthAspect(PickingUpDetailActivity.this, 0.8f)
                                            .setGravity(Gravity.CENTER)
                                            .setCancelableOutside(false)
                                            .addOnClickListener(R.id.recycler_view, R.id.tv_cancel, R.id.tv_sure)
                                            .setOnBindViewListener(new OnBindViewListener() {
                                                @Override
                                                public void bindView(BindViewHolder viewHolder) {
                                                    RecyclerView recycler_view = viewHolder.itemView.findViewById(R.id.recycler_view);
                                                    originalDeliveryAdapter = new OriginalDeliveryAdapter(PickingUpDetailActivity.this);
                                                    recycler_view.setLayoutManager(new LinearLayoutManager(PickingUpDetailActivity.this, LinearLayoutManager.VERTICAL, false));
                                                    recycler_view.setAdapter(originalDeliveryAdapter);
                                                }
                                            })
                                            .setOnViewClickListener(new OnViewClickListener() {
                                                @Override
                                                public void onViewClick(BindViewHolder viewHolder, View view, TDialog tDialog) {
                                                    switch (view.getId()) {
                                                        case R.id.tv_cancel:
                                                            tDialog2.dismiss();
                                                            break;
                                                        case R.id.tv_sure:
                                                            tDialog2.dismiss();
                                                            break;
                                                    }
                                                }
                                            })
                                            .create()
                                            .show();
                                } else if (pos == 3) {
                                    tDialog1.dismiss();
                                    tDialog4 = new TDialog.Builder(getSupportFragmentManager())
                                            .setLayoutRes(R.layout.dialog_poor_quality)
                                            .setScreenWidthAspect(PickingUpDetailActivity.this, 0.8f)
                                            .setGravity(Gravity.CENTER)
                                            .setCancelableOutside(false)
                                            .addOnClickListener(R.id.tv_cancel, R.id.tv_sure, R.id.recycler_view)
                                            .setOnBindViewListener(new OnBindViewListener() {
                                                @Override
                                                public void bindView(BindViewHolder viewHolder) {
                                                    recycler_view = viewHolder.itemView.findViewById(R.id.recycler_view);
                                                    initGridView();
                                                }
                                            })
                                            .setOnViewClickListener(new OnViewClickListener() {
                                                @Override
                                                public void onViewClick(BindViewHolder viewHolder, View view, TDialog tDialog) {
                                                    switch (view.getId()) {
                                                        case R.id.tv_cancel:
                                                            tDialog4.dismiss();
                                                            mPicList.clear();
                                                            break;
                                                        case R.id.tv_sure:
                                                            tDialog4.dismiss();
                                                            mPicList.clear();
                                                            break;
                                                    }
                                                }
                                            })
                                            .create()
                                            .show();
                                } else if (pos == 4) {
                                    tDialog1.dismiss();
                                    tDialog3 = new TDialog.Builder(getSupportFragmentManager())
                                            .setLayoutRes(R.layout.dialog_replacement)
                                            .setScreenWidthAspect(PickingUpDetailActivity.this, 0.8f)
                                            .setGravity(Gravity.CENTER)
                                            .setCancelableOutside(false)
                                            .addOnClickListener(R.id.tv_cancel, R.id.tv_sure, R.id.recycler_view)
                                            .setOnBindViewListener(new OnBindViewListener() {
                                                @Override
                                                public void bindView(BindViewHolder viewHolder) {
                                                    recycler_view = viewHolder.itemView.findViewById(R.id.recycler_view);
                                                    initGridView();
                                                }
                                            })
                                            .setOnViewClickListener(new OnViewClickListener() {
                                                @Override
                                                public void onViewClick(BindViewHolder viewHolder, View view, TDialog tDialog) {
                                                    switch (view.getId()) {
                                                        case R.id.tv_cancel:
                                                            tDialog3.dismiss();
                                                            mPicList.clear();
                                                            break;
                                                        case R.id.tv_sure:
                                                            tDialog3.dismiss();
                                                            mPicList.clear();
                                                            break;
                                                    }
                                                }
                                            })
                                            .create()
                                            .show();
                                }
                                break;
                        }
                    }
                })
                .create()
                .show();
    }

    @Override
    public void onItemClicks(int position) {
        tDialog1 = new TDialog.Builder(getSupportFragmentManager())
                .setLayoutRes(R.layout.dialog_operation)
                .setScreenWidthAspect(this, 0.8f)
                .setGravity(Gravity.CENTER)
                .addOnClickListener(R.id.tv_out_of_stock, R.id.tv_original_delivery, R.id.tv_poor_quality,
                        R.id.tv_replacement, R.id.tv_cancel, R.id.tv_sure, R.id.ll_sure)
                .setOnBindViewListener(new OnBindViewListener() {
                    @Override
                    public void bindView(BindViewHolder viewHolder) {
                        tv_out_of_stock = viewHolder.itemView.findViewById(R.id.tv_out_of_stock);
                        tv_original_delivery = viewHolder.itemView.findViewById(R.id.tv_original_delivery);
                        tv_poor_quality = viewHolder.itemView.findViewById(R.id.tv_poor_quality);
                        tv_replacement = viewHolder.itemView.findViewById(R.id.tv_replacement);
                        tv_cancel = viewHolder.itemView.findViewById(R.id.tv_cancel);
                        tv_sure = viewHolder.itemView.findViewById(R.id.tv_sure);
                        ll_sure = viewHolder.itemView.findViewById(R.id.ll_sure);
                    }
                })
                .setOnViewClickListener(new OnViewClickListener() {
                    @Override
                    public void onViewClick(BindViewHolder viewHolder, View view, TDialog tDialog) {
                        switch (view.getId()) {
                            case R.id.tv_out_of_stock:
                                pos = 1;
                                tv_out_of_stock.setBackgroundResource(R.drawable.bg_green);
                                tv_original_delivery.setBackgroundResource(R.drawable.bg_white_5);
                                tv_poor_quality.setBackgroundResource(R.drawable.bg_white_5);
                                tv_replacement.setBackgroundResource(R.drawable.bg_white_5);
                                ll_sure.setVisibility(View.VISIBLE);
                                break;
                            case R.id.tv_original_delivery:
                                pos = 2;
                                tv_out_of_stock.setBackgroundResource(R.drawable.bg_white_5);
                                tv_original_delivery.setBackgroundResource(R.drawable.bg_green);
                                tv_poor_quality.setBackgroundResource(R.drawable.bg_white_5);
                                tv_replacement.setBackgroundResource(R.drawable.bg_white_5);
                                ll_sure.setVisibility(View.VISIBLE);
                                break;
                            case R.id.tv_poor_quality:
                                pos = 3;
                                tv_out_of_stock.setBackgroundResource(R.drawable.bg_white_5);
                                tv_original_delivery.setBackgroundResource(R.drawable.bg_white_5);
                                tv_poor_quality.setBackgroundResource(R.drawable.bg_green);
                                tv_replacement.setBackgroundResource(R.drawable.bg_white_5);
                                ll_sure.setVisibility(View.VISIBLE);
                                break;
                            case R.id.tv_replacement:
                                pos = 4;
                                tv_out_of_stock.setBackgroundResource(R.drawable.bg_white_5);
                                tv_original_delivery.setBackgroundResource(R.drawable.bg_white_5);
                                tv_poor_quality.setBackgroundResource(R.drawable.bg_white_5);
                                tv_replacement.setBackgroundResource(R.drawable.bg_green);
                                ll_sure.setVisibility(View.VISIBLE);
                                break;
                            case R.id.tv_cancel:
                                tDialog1.dismiss();
                                break;
                            case R.id.tv_sure:
                                if (pos == 1) {
                                    tDialog1.dismiss();
                                } else if (pos == 2) {
                                    tDialog1.dismiss();
                                    tDialog2 = new TDialog.Builder(getSupportFragmentManager())
                                            .setLayoutRes(R.layout.dialog_original_delivery)
                                            .setScreenWidthAspect(PickingUpDetailActivity.this, 0.8f)
                                            .setGravity(Gravity.CENTER)
                                            .setCancelableOutside(false)
                                            .addOnClickListener(R.id.recycler_view, R.id.tv_cancel, R.id.tv_sure)
                                            .setOnBindViewListener(new OnBindViewListener() {
                                                @Override
                                                public void bindView(BindViewHolder viewHolder) {
                                                    RecyclerView recycler_view = viewHolder.itemView.findViewById(R.id.recycler_view);
                                                    originalDeliveryAdapter = new OriginalDeliveryAdapter(PickingUpDetailActivity.this);
                                                    recycler_view.setLayoutManager(new LinearLayoutManager(PickingUpDetailActivity.this, LinearLayoutManager.VERTICAL, false));
                                                    recycler_view.setAdapter(originalDeliveryAdapter);
                                                }
                                            })
                                            .setOnViewClickListener(new OnViewClickListener() {
                                                @Override
                                                public void onViewClick(BindViewHolder viewHolder, View view, TDialog tDialog) {
                                                    switch (view.getId()) {
                                                        case R.id.tv_cancel:
                                                            tDialog2.dismiss();
                                                            break;
                                                        case R.id.tv_sure:
                                                            tDialog2.dismiss();
                                                            break;
                                                    }
                                                }
                                            })
                                            .create()
                                            .show();
                                } else if (pos == 3) {
                                    tDialog1.dismiss();
                                    tDialog4 = new TDialog.Builder(getSupportFragmentManager())
                                            .setLayoutRes(R.layout.dialog_poor_quality)
                                            .setScreenWidthAspect(PickingUpDetailActivity.this, 0.8f)
                                            .setGravity(Gravity.CENTER)
                                            .setCancelableOutside(false)
                                            .addOnClickListener(R.id.tv_cancel, R.id.tv_sure, R.id.recycler_view)
                                            .setOnBindViewListener(new OnBindViewListener() {
                                                @Override
                                                public void bindView(BindViewHolder viewHolder) {
                                                    recycler_view = viewHolder.itemView.findViewById(R.id.recycler_view);
                                                    initGridView();
                                                }
                                            })
                                            .setOnViewClickListener(new OnViewClickListener() {
                                                @Override
                                                public void onViewClick(BindViewHolder viewHolder, View view, TDialog tDialog) {
                                                    switch (view.getId()) {
                                                        case R.id.tv_cancel:
                                                            tDialog4.dismiss();
                                                            mPicList.clear();
                                                            break;
                                                        case R.id.tv_sure:
                                                            tDialog4.dismiss();
                                                            mPicList.clear();
                                                            break;
                                                    }
                                                }
                                            })
                                            .create()
                                            .show();
                                } else if (pos == 4) {
                                    tDialog1.dismiss();
                                    tDialog3 = new TDialog.Builder(getSupportFragmentManager())
                                            .setLayoutRes(R.layout.dialog_replacement)
                                            .setScreenWidthAspect(PickingUpDetailActivity.this, 0.8f)
                                            .setGravity(Gravity.CENTER)
                                            .setCancelableOutside(false)
                                            .addOnClickListener(R.id.tv_cancel, R.id.tv_sure, R.id.recycler_view)
                                            .setOnBindViewListener(new OnBindViewListener() {
                                                @Override
                                                public void bindView(BindViewHolder viewHolder) {
                                                    recycler_view = viewHolder.itemView.findViewById(R.id.recycler_view);
                                                    initGridView();
                                                }
                                            })
                                            .setOnViewClickListener(new OnViewClickListener() {
                                                @Override
                                                public void onViewClick(BindViewHolder viewHolder, View view, TDialog tDialog) {
                                                    switch (view.getId()) {
                                                        case R.id.tv_cancel:
                                                            tDialog3.dismiss();
                                                            mPicList.clear();
                                                            break;
                                                        case R.id.tv_sure:
                                                            tDialog3.dismiss();
                                                            mPicList.clear();
                                                            break;
                                                    }
                                                }
                                            })
                                            .create()
                                            .show();
                                }
                                break;
                        }
                    }
                })
                .create()
                .show();
    }
}