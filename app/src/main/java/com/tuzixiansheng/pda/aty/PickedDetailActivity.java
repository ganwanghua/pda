package com.tuzixiansheng.pda.aty;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pedaily.yc.ycdialoglib.dialog.loading.ViewLoading;
import com.tuzixiansheng.pda.R;
import com.tuzixiansheng.pda.adapter.CollectTodayAdapter;
import com.tuzixiansheng.pda.adapter.PickedDetailAdapter;
import com.tuzixiansheng.pda.base.BaseActivity;
import com.tuzixiansheng.pda.bean.ModuleBean;
import com.tuzixiansheng.pda.bean.PickedDetail;
import com.tuzixiansheng.pda.nets.DataRepository;
import com.tuzixiansheng.pda.nets.Injection;
import com.tuzixiansheng.pda.nets.RemotDataSource;
import com.tuzixiansheng.pda.utils.SpUtil;
import com.tuzixiansheng.pda.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PickedDetailActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.ll_info)
    LinearLayout llInfo;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    private DataRepository dataRepository;
    private String phone;
    private PickedDetailAdapter pickedDetailAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initWhite();
        StatusBarUtil.StatusBarLightMode(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picked_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        phone = getIntent().getStringExtra("phone");
        dataRepository = Injection.dataRepository(this);
        pickedDetail(phone);

        pickedDetailAdapter = new PickedDetailAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(pickedDetailAdapter);
    }

    private void pickedDetail(String phone) {
        ViewLoading.show(this);
        String token = SpUtil.getString(this, "token", "");
        String shopId = SpUtil.getString(this, "shopId", "");
        String pickType = SpUtil.getString(this, "type", "");
        ModuleBean moduleBean = new ModuleBean();
        moduleBean.shopId = shopId;
        moduleBean.pickType = pickType;
        moduleBean.phone = phone;
        dataRepository.pickedDetail(token, moduleBean, new RemotDataSource.getCallback() {
            @Override
            public void onFailure(String info) {
                ViewLoading.dismiss(PickedDetailActivity.this);
            }

            @Override
            public void onSuccess(Object data) {
                ViewLoading.dismiss(PickedDetailActivity.this);
                PickedDetail pickedDetail = (PickedDetail) data;
                if (pickedDetail.getCode() == 200) {
                    if (pickedDetail.getData() != null && pickedDetail.getData().size() > 0) {
                        tvName.setText(pickedDetail.getData().get(0).getUserName());
                        tvPhone.setText(pickedDetail.getData().get(0).getPhone());
                        pickedDetailAdapter.setData(pickedDetail.getData());
                    }
                }
            }
        });
    }
}