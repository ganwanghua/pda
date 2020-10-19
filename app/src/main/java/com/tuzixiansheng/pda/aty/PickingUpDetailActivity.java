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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.lling.photopicker.PhotoPickerActivity;
import com.pedaily.yc.ycdialoglib.dialog.loading.ViewLoading;
import com.pedaily.yc.ycdialoglib.toast.ToastUtils;
import com.timmy.tdialog.TDialog;
import com.timmy.tdialog.base.BindViewHolder;
import com.timmy.tdialog.listener.OnBindViewListener;
import com.timmy.tdialog.listener.OnViewClickListener;
import com.tuzixiansheng.pda.LoginActivity;
import com.tuzixiansheng.pda.R;
import com.tuzixiansheng.pda.adapter.ImageAdapter;
import com.tuzixiansheng.pda.adapter.OriginalDeliveryAdapter;
import com.tuzixiansheng.pda.adapter.PickedUpAdapter;
import com.tuzixiansheng.pda.adapter.WaitAgoAdapter;
import com.tuzixiansheng.pda.adapter.WaitTodayAdapter;
import com.tuzixiansheng.pda.base.BaseActivity;
import com.tuzixiansheng.pda.bean.ModuleBean;
import com.tuzixiansheng.pda.bean.PickUpDetailRecord;
import com.tuzixiansheng.pda.bean.VerifyBean;
import com.tuzixiansheng.pda.nets.DataRepository;
import com.tuzixiansheng.pda.nets.Injection;
import com.tuzixiansheng.pda.nets.RemotDataSource;
import com.tuzixiansheng.pda.utils.SpUtil;
import com.tuzixiansheng.pda.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    //    private PickedUpAdapter pickedUpAdapter;
    private List<PickUpDetailRecord.TodayListBean> todayList = new ArrayList<>();
    private List<PickUpDetailRecord.YesterdaylistBean> historyList = new ArrayList<>();
    private BroadcastReceiver mReceiver;
    private IntentFilter mFilter;
    private DataRepository dataRepository;
    private String phone;
    private TextView tv_out_of_stock, tv_original_delivery, tv_poor_quality, tv_replacement, tv_cancel, tv_sure, tv_name;
    private int pos;
    private TDialog tDialog, tDialog1, tDialog2, tDialog3, tDialog4;
    private String pos1;
    private LinearLayout ll_sure;
    private ImageAdapter imageAdapter;
    private ArrayList<String> mPicList = new ArrayList<>();
    private RecyclerView recycler_view;
    private EditText edit_num;

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
                } else {
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
        phone = getIntent().getStringExtra("code");
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

//        pickedUpAdapter = new PickedUpAdapter(this);
//        rvPickedUp.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        rvPickedUp.setAdapter(pickedUpAdapter);

        pickUpDetail();

        mFilter = new IntentFilter("android.intent.action.SCANRESULT");
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                final String scanResult = intent.getStringExtra("value");
                toVerify(scanResult);
            }
        };
    }

    private void toVerify(String scanResult) {
        ViewLoading.show(this);
        ModuleBean moduleBean = new ModuleBean();
        moduleBean.barCode = scanResult;
        moduleBean.store_id = SpUtil.getInt(PickingUpDetailActivity.this, "shopId", -1);
        moduleBean.user_id = SpUtil.getString(PickingUpDetailActivity.this, "userId", "");
        dataRepository.toVerify(moduleBean, new RemotDataSource.getCallback() {
            @Override
            public void onFailure(String info) {
                ViewLoading.dismiss(PickingUpDetailActivity.this);
            }

            @Override
            public void onSuccess(Object data) {
                VerifyBean verifyBean = (VerifyBean) data;
                ViewLoading.dismiss(PickingUpDetailActivity.this);
                if (verifyBean.getCode().equals("0000")) {
                    ToastUtils.showToast(verifyBean.getResult());
                    pickUpDetails();
                }
            }
        });
    }

    private void pickUpDetail() {
        ViewLoading.show(this);
        ModuleBean moduleBean = new ModuleBean();
        moduleBean.code = phone;
        moduleBean.store_id = SpUtil.getInt(PickingUpDetailActivity.this, "shopId", -1);
        dataRepository.pickUpDetail(moduleBean, new RemotDataSource.getCallback() {
            @Override
            public void onFailure(String info) {
                ViewLoading.dismiss(PickingUpDetailActivity.this);
            }

            @Override
            public void onSuccess(Object data) {
                ViewLoading.dismiss(PickingUpDetailActivity.this);
                PickUpDetailRecord pickUpDetailRecord = (PickUpDetailRecord) data;
                if (pickUpDetailRecord.getCode().equals("0000")) {
                    SpUtil.saveString(PickingUpDetailActivity.this, "userId", pickUpDetailRecord.getUser_id());
                    todayList = pickUpDetailRecord.getTodaylist();
                    historyList = pickUpDetailRecord.getYesterdaylist();
                    if (todayList.size() == 0 && historyList.size() == 0) {
                        tvEmpty.setVisibility(View.VISIBLE);
                        nestedScrollView.setVisibility(View.GONE);
                        tvPick.setVisibility(View.GONE);
                        return;
                    } else {
                        tvEmpty.setVisibility(View.GONE);
                        nestedScrollView.setVisibility(View.VISIBLE);
                        tvPick.setVisibility(View.VISIBLE);
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
                    tvEmpty.setVisibility(View.VISIBLE);
                    nestedScrollView.setVisibility(View.GONE);
                    tvPick.setVisibility(View.GONE);
                    ToastUtils.showToast(pickUpDetailRecord.getResult());
                }
            }
        });
    }

    private void pickUpDetails() {
        ViewLoading.show(this);
        ModuleBean moduleBean = new ModuleBean();
        moduleBean.user_id = SpUtil.getString(PickingUpDetailActivity.this, "userId", "");
        moduleBean.store_id = SpUtil.getInt(PickingUpDetailActivity.this, "shopId", -1);
        dataRepository.pickUpDetail(moduleBean, new RemotDataSource.getCallback() {
            @Override
            public void onFailure(String info) {
                ViewLoading.dismiss(PickingUpDetailActivity.this);
            }

            @Override
            public void onSuccess(Object data) {
                ViewLoading.dismiss(PickingUpDetailActivity.this);
                PickUpDetailRecord pickUpDetailRecord = (PickUpDetailRecord) data;
                if (pickUpDetailRecord.getCode().equals("0000")) {
                    SpUtil.saveString(PickingUpDetailActivity.this, "userId", pickUpDetailRecord.getUser_id());
                    todayList = pickUpDetailRecord.getTodaylist();
                    historyList = pickUpDetailRecord.getYesterdaylist();
                    if (todayList.size() == 0 && historyList.size() == 0) {
                        tvEmpty.setVisibility(View.VISIBLE);
                        nestedScrollView.setVisibility(View.GONE);
                        tvPick.setVisibility(View.GONE);
                        return;
                    } else {
                        tvEmpty.setVisibility(View.GONE);
                        nestedScrollView.setVisibility(View.VISIBLE);
                        tvPick.setVisibility(View.VISIBLE);
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
                    tvEmpty.setVisibility(View.VISIBLE);
                    nestedScrollView.setVisibility(View.GONE);
                    tvPick.setVisibility(View.GONE);
                    ToastUtils.showToast(pickUpDetailRecord.getResult());
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
                if (todayList.size() == 0 && historyList.size() == 0) {
                    finish();
                }else {
                    showDialog();
                }
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
                        R.id.tv_replacement, R.id.tv_cancel, R.id.tv_sure, R.id.ll_sure, R.id.tv_name)
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
                        tv_name = viewHolder.itemView.findViewById(R.id.tv_name);
                        tv_name.setText(todayList.get(position).getGoods_title());
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
                                ll_sure.setVisibility(View.GONE);
                                tDialog2 = new TDialog.Builder(getSupportFragmentManager())
                                        .setLayoutRes(R.layout.dialog_original_delivery)
                                        .setScreenWidthAspect(PickingUpDetailActivity.this, 0.8f)
                                        .setGravity(Gravity.CENTER)
                                        .setCancelableOutside(false)
                                        .addOnClickListener(R.id.edit_num, R.id.tv_cancel, R.id.tv_sure, R.id.tv_name)
                                        .setOnBindViewListener(new OnBindViewListener() {
                                            @Override
                                            public void bindView(BindViewHolder viewHolder) {
                                                edit_num = viewHolder.itemView.findViewById(R.id.edit_num);
                                                tv_name = viewHolder.itemView.findViewById(R.id.tv_name);
                                                tv_name.setText(todayList.get(position).getGoods_title());
//                                                originalDeliveryAdapter = new OriginalDeliveryAdapter(PickingUpDetailActivity.this);
//                                                recycler_view.setLayoutManager(new LinearLayoutManager(PickingUpDetailActivity.this, LinearLayoutManager.VERTICAL, false));
//                                                recycler_view.setAdapter(originalDeliveryAdapter);
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
                                                        noCode(todayList.get(position).getSku_code(), edit_num.getText().toString());
                                                        tDialog2.dismiss();
                                                        break;
                                                }
                                            }
                                        })
                                        .create()
                                        .show();
                                break;
                            case R.id.tv_poor_quality:
                                pos = 3;
                                tv_out_of_stock.setBackgroundResource(R.drawable.bg_white_5);
                                tv_original_delivery.setBackgroundResource(R.drawable.bg_white_5);
                                tv_poor_quality.setBackgroundResource(R.drawable.bg_green);
                                tv_replacement.setBackgroundResource(R.drawable.bg_white_5);
                                ll_sure.setVisibility(View.GONE);
                                tDialog4 = new TDialog.Builder(getSupportFragmentManager())
                                        .setLayoutRes(R.layout.dialog_poor_quality)
                                        .setScreenWidthAspect(PickingUpDetailActivity.this, 0.8f)
                                        .setGravity(Gravity.CENTER)
                                        .setCancelableOutside(false)
                                        .addOnClickListener(R.id.tv_cancel, R.id.tv_sure, R.id.recycler_view, R.id.tv_name, R.id.edit_num)
                                        .setOnBindViewListener(new OnBindViewListener() {
                                            @Override
                                            public void bindView(BindViewHolder viewHolder) {
                                                edit_num = viewHolder.itemView.findViewById(R.id.edit_num);
                                                recycler_view = viewHolder.itemView.findViewById(R.id.recycler_view);
                                                tv_name = viewHolder.itemView.findViewById(R.id.tv_name);
                                                tv_name.setText(todayList.get(position).getGoods_title());
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
                                                        refuse(todayList.get(position).getSku_code(), edit_num.getText().toString());
                                                        tDialog4.dismiss();
                                                        mPicList.clear();
                                                        break;
                                                }
                                            }
                                        })
                                        .create()
                                        .show();
                                break;
                            case R.id.tv_replacement:
                                pos = 4;
                                tv_out_of_stock.setBackgroundResource(R.drawable.bg_white_5);
                                tv_original_delivery.setBackgroundResource(R.drawable.bg_white_5);
                                tv_poor_quality.setBackgroundResource(R.drawable.bg_white_5);
                                tv_replacement.setBackgroundResource(R.drawable.bg_green);
                                ll_sure.setVisibility(View.GONE);
                                tDialog3 = new TDialog.Builder(getSupportFragmentManager())
                                        .setLayoutRes(R.layout.dialog_replacement)
                                        .setScreenWidthAspect(PickingUpDetailActivity.this, 0.8f)
                                        .setGravity(Gravity.CENTER)
                                        .setCancelableOutside(false)
                                        .addOnClickListener(R.id.edit_num, R.id.tv_cancel, R.id.tv_sure, R.id.recycler_view, R.id.tv_name)
                                        .setOnBindViewListener(new OnBindViewListener() {
                                            @Override
                                            public void bindView(BindViewHolder viewHolder) {
                                                recycler_view = viewHolder.itemView.findViewById(R.id.recycler_view);
                                                tv_name = viewHolder.itemView.findViewById(R.id.tv_name);
                                                edit_num = viewHolder.itemView.findViewById(R.id.edit_num);
                                                tv_name.setText(todayList.get(position).getGoods_title());
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
                                                        barter(todayList.get(position).getSku_code(), edit_num.getText().toString());
                                                        tDialog3.dismiss();
                                                        mPicList.clear();
                                                        break;
                                                }
                                            }
                                        })
                                        .create()
                                        .show();
                                break;
                            case R.id.tv_cancel:
                                tDialog1.dismiss();
                                break;
                            case R.id.tv_sure:
                                defect(todayList.get(position).getSku_code());
                                tDialog1.dismiss();
                                break;
                        }
                    }
                })
                .create()
                .show();
    }

    private void refuse(String sku_code, String refuseReason) {
        ViewLoading.show(this);
        ModuleBean moduleBean = new ModuleBean();
        moduleBean.skuCode = sku_code;
        moduleBean.refuseReason = refuseReason;
        moduleBean.store_id = SpUtil.getInt(PickingUpDetailActivity.this, "shopId", -1);
        moduleBean.user_id = SpUtil.getString(PickingUpDetailActivity.this, "userId", "");
        dataRepository.refuse(moduleBean, new RemotDataSource.getCallback() {
            @Override
            public void onFailure(String info) {
                ViewLoading.dismiss(PickingUpDetailActivity.this);
            }

            @Override
            public void onSuccess(Object data) {
                VerifyBean verifyBean = (VerifyBean) data;
                ViewLoading.dismiss(PickingUpDetailActivity.this);
                if (verifyBean.getCode().equals("0000")) {
                    ToastUtils.showToast(verifyBean.getResult());
                    pickUpDetails();
                }
            }
        });
    }

    private void barter(String sku_code, String refuseReason) {
        ViewLoading.show(this);
        ModuleBean moduleBean = new ModuleBean();
        moduleBean.skuCode = sku_code;
        moduleBean.barterReason = refuseReason;
        moduleBean.store_id = SpUtil.getInt(PickingUpDetailActivity.this, "shopId", -1);
        moduleBean.user_id = SpUtil.getString(PickingUpDetailActivity.this, "userId", "");
        dataRepository.barter(moduleBean, new RemotDataSource.getCallback() {
            @Override
            public void onFailure(String info) {
                ViewLoading.dismiss(PickingUpDetailActivity.this);
            }

            @Override
            public void onSuccess(Object data) {
                VerifyBean verifyBean = (VerifyBean) data;
                ViewLoading.dismiss(PickingUpDetailActivity.this);
                if (verifyBean.getCode().equals("0000")) {
                    ToastUtils.showToast(verifyBean.getResult());
                    pickUpDetails();
                }
            }
        });
    }

    private void defect(String sku_code) {
        ViewLoading.show(this);
        ModuleBean moduleBean = new ModuleBean();
        moduleBean.skuCode = sku_code;
        moduleBean.store_id = SpUtil.getInt(PickingUpDetailActivity.this, "shopId", -1);
        moduleBean.user_id = SpUtil.getString(PickingUpDetailActivity.this, "userId", "");
        dataRepository.defect(moduleBean, new RemotDataSource.getCallback() {
            @Override
            public void onFailure(String info) {
                ViewLoading.dismiss(PickingUpDetailActivity.this);
            }

            @Override
            public void onSuccess(Object data) {
                VerifyBean verifyBean = (VerifyBean) data;
                ViewLoading.dismiss(PickingUpDetailActivity.this);
                if (verifyBean.getCode().equals("0000")) {
                    ToastUtils.showToast(verifyBean.getResult());
                    pickUpDetails();
                }
            }
        });
    }

    private void noCode(String sku_code, String acceptWeights) {
        ViewLoading.show(this);
        ModuleBean moduleBean = new ModuleBean();
        moduleBean.skuCode = sku_code;
        moduleBean.acceptWeights = acceptWeights;
        moduleBean.store_id = SpUtil.getInt(PickingUpDetailActivity.this, "shopId", -1);
        moduleBean.user_id = SpUtil.getString(PickingUpDetailActivity.this, "userId", "");
        dataRepository.noCode(moduleBean, new RemotDataSource.getCallback() {
            @Override
            public void onFailure(String info) {
                ViewLoading.dismiss(PickingUpDetailActivity.this);
            }

            @Override
            public void onSuccess(Object data) {
                VerifyBean verifyBean = (VerifyBean) data;
                ViewLoading.dismiss(PickingUpDetailActivity.this);
                if (verifyBean.getCode().equals("0000")) {
                    ToastUtils.showToast(verifyBean.getResult());
                    pickUpDetails();
                }
            }
        });
    }

    @Override
    public void onItemClicks(int position) {
        tDialog1 = new TDialog.Builder(getSupportFragmentManager())
                .setLayoutRes(R.layout.dialog_operation)
                .setScreenWidthAspect(this, 0.8f)
                .setGravity(Gravity.CENTER)
                .addOnClickListener(R.id.tv_out_of_stock, R.id.tv_original_delivery, R.id.tv_poor_quality,
                        R.id.tv_replacement, R.id.tv_cancel, R.id.tv_sure, R.id.ll_sure, R.id.tv_name)
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
                        tv_name = viewHolder.itemView.findViewById(R.id.tv_name);
                        tv_name.setText(historyList.get(position).getGoods_title());
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
                                ll_sure.setVisibility(View.GONE);
                                tDialog2 = new TDialog.Builder(getSupportFragmentManager())
                                        .setLayoutRes(R.layout.dialog_original_delivery)
                                        .setScreenWidthAspect(PickingUpDetailActivity.this, 0.8f)
                                        .setGravity(Gravity.CENTER)
                                        .setCancelableOutside(false)
                                        .addOnClickListener(R.id.edit_num, R.id.tv_cancel, R.id.tv_sure, R.id.tv_name)
                                        .setOnBindViewListener(new OnBindViewListener() {
                                            @Override
                                            public void bindView(BindViewHolder viewHolder) {
                                                edit_num = viewHolder.itemView.findViewById(R.id.edit_num);
                                                tv_name = viewHolder.itemView.findViewById(R.id.tv_name);
                                                tv_name.setText(historyList.get(position).getGoods_title());
//                                                originalDeliveryAdapter = new OriginalDeliveryAdapter(PickingUpDetailActivity.this);
//                                                recycler_view.setLayoutManager(new LinearLayoutManager(PickingUpDetailActivity.this, LinearLayoutManager.VERTICAL, false));
//                                                recycler_view.setAdapter(originalDeliveryAdapter);
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
                                                        noCode(historyList.get(position).getSku_code(), edit_num.getText().toString());
                                                        tDialog2.dismiss();
                                                        break;
                                                }
                                            }
                                        })
                                        .create()
                                        .show();
                                break;
                            case R.id.tv_poor_quality:
                                pos = 3;
                                tv_out_of_stock.setBackgroundResource(R.drawable.bg_white_5);
                                tv_original_delivery.setBackgroundResource(R.drawable.bg_white_5);
                                tv_poor_quality.setBackgroundResource(R.drawable.bg_green);
                                tv_replacement.setBackgroundResource(R.drawable.bg_white_5);
                                ll_sure.setVisibility(View.GONE);
                                tDialog4 = new TDialog.Builder(getSupportFragmentManager())
                                        .setLayoutRes(R.layout.dialog_poor_quality)
                                        .setScreenWidthAspect(PickingUpDetailActivity.this, 0.8f)
                                        .setGravity(Gravity.CENTER)
                                        .setCancelableOutside(false)
                                        .addOnClickListener(R.id.edit_num, R.id.tv_cancel, R.id.tv_sure, R.id.recycler_view, R.id.tv_name)
                                        .setOnBindViewListener(new OnBindViewListener() {
                                            @Override
                                            public void bindView(BindViewHolder viewHolder) {
                                                recycler_view = viewHolder.itemView.findViewById(R.id.recycler_view);
                                                tv_name = viewHolder.itemView.findViewById(R.id.tv_name);
                                                edit_num = viewHolder.itemView.findViewById(R.id.edit_num);
                                                tv_name.setText(historyList.get(position).getGoods_title());
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
                                                        refuse(historyList.get(position).getSku_code(), edit_num.getText().toString());
                                                        tDialog4.dismiss();
                                                        mPicList.clear();
                                                        break;
                                                }
                                            }
                                        })
                                        .create()
                                        .show();
                                break;
                            case R.id.tv_replacement:
                                pos = 4;
                                tv_out_of_stock.setBackgroundResource(R.drawable.bg_white_5);
                                tv_original_delivery.setBackgroundResource(R.drawable.bg_white_5);
                                tv_poor_quality.setBackgroundResource(R.drawable.bg_white_5);
                                tv_replacement.setBackgroundResource(R.drawable.bg_green);
                                ll_sure.setVisibility(View.GONE);
                                tDialog3 = new TDialog.Builder(getSupportFragmentManager())
                                        .setLayoutRes(R.layout.dialog_replacement)
                                        .setScreenWidthAspect(PickingUpDetailActivity.this, 0.8f)
                                        .setGravity(Gravity.CENTER)
                                        .setCancelableOutside(false)
                                        .addOnClickListener(R.id.edit_num, R.id.tv_cancel, R.id.tv_sure, R.id.recycler_view, R.id.tv_name)
                                        .setOnBindViewListener(new OnBindViewListener() {
                                            @Override
                                            public void bindView(BindViewHolder viewHolder) {
                                                recycler_view = viewHolder.itemView.findViewById(R.id.recycler_view);
                                                tv_name = viewHolder.itemView.findViewById(R.id.tv_name);
                                                edit_num = viewHolder.itemView.findViewById(R.id.edit_num);
                                                tv_name.setText(historyList.get(position).getGoods_title());
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
                                                        barter(historyList.get(position).getSku_code(), edit_num.getText().toString());
                                                        tDialog3.dismiss();
                                                        mPicList.clear();
                                                        break;
                                                }
                                            }
                                        })
                                        .create()
                                        .show();
                                break;
                            case R.id.tv_cancel:
                                tDialog1.dismiss();
                                break;
                            case R.id.tv_sure:
                                defect(historyList.get(position).getSku_code());
                                tDialog1.dismiss();
                                break;
                        }
                    }
                })
                .create()
                .show();
    }
}