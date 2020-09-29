package com.tuzixiansheng.pda.aty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pedaily.yc.ycdialoglib.dialog.loading.ViewLoading;
import com.pedaily.yc.ycdialoglib.toast.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.tuzixiansheng.pda.R;
import com.tuzixiansheng.pda.adapter.CommodityAdapter;
import com.tuzixiansheng.pda.adapter.ReturnGoodsAdapter;
import com.tuzixiansheng.pda.adapter.ReturnHistoryAdapter;
import com.tuzixiansheng.pda.base.BaseActivity;
import com.tuzixiansheng.pda.bean.ModuleBean;
import com.tuzixiansheng.pda.bean.ReturnGoods;
import com.tuzixiansheng.pda.nets.DataRepository;
import com.tuzixiansheng.pda.nets.Injection;
import com.tuzixiansheng.pda.nets.RemotDataSource;
import com.tuzixiansheng.pda.utils.SpUtil;
import com.tuzixiansheng.pda.utils.StatusBarUtil;
import com.tuzixiansheng.pda.weight.CommItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReturnGoodsActivity extends BaseActivity implements ReturnGoodsAdapter.MyItemClickListener {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_dimension)
    TextView tvDimension;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.tv_query)
    TextView tvQuery;
    @BindView(R.id.tv_clear)
    TextView tvClear;
    @BindView(R.id.ll_phone)
    LinearLayout llPhone;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R.id.rl_return_goods)
    RelativeLayout rlReturnGoods;
    @BindView(R.id.edit_phone1)
    EditText editPhone1;
    @BindView(R.id.tv_query1)
    TextView tvQuery1;
    @BindView(R.id.tv_clear1)
    TextView tvClear1;
    @BindView(R.id.ll_phone1)
    LinearLayout llPhone1;
    @BindView(R.id.recycler_view1)
    RecyclerView recyclerView1;
    @BindView(R.id.refresh1)
    SmartRefreshLayout refresh1;
    @BindView(R.id.rl_return_history)
    RelativeLayout rlReturnHistory;
    private boolean isClick = true;
    private DataRepository dataRepository;
    private int pageNum = 1;
    private int pageNum1 = 1;
    private ReturnGoodsAdapter returnGoodsAdapter;
    private ReturnHistoryAdapter returnHistoryAdapter;
    private String phone = "";
    private String phone1 = "";
    private boolean isLastPage = true;
    private boolean isLastPage1 = true;
    private List<ReturnGoods.DataBean.ListBean> dataBean = new ArrayList<>();
    private List<ReturnGoods.DataBean.ListBean> dataBean1 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initWhite();
        StatusBarUtil.StatusBarLightMode(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_goods);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        dataRepository = Injection.dataRepository(this);

        returnGoodsAdapter = new ReturnGoodsAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new CommItemDecoration(this, DividerItemDecoration.VERTICAL, 0x00000000, 20));
        recyclerView.setAdapter(returnGoodsAdapter);
        returnGoodsAdapter.setItemClickListener(this);

        returnHistoryAdapter = new ReturnHistoryAdapter(this);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView1.addItemDecoration(new CommItemDecoration(this, DividerItemDecoration.VERTICAL, 0x00000000, 20));
        recyclerView1.setAdapter(returnHistoryAdapter);

        refresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum++;
                returnGoods(pageNum, phone);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum = 1;
                dataBean.clear();
                isLastPage = true;
                returnGoods(pageNum, phone);
            }
        });

        refresh1.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum1++;
                returnHistory(pageNum1, phone1);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum1 = 1;
                dataBean1.clear();
                isLastPage1 = true;
                returnHistory(pageNum1, phone1);
            }
        });
        returnGoods(pageNum, phone);
    }

    private void returnGoods(int pageNum, String phone) {
        ViewLoading.show(this);
        String token = SpUtil.getString(this, "token", "");
        String shopId = SpUtil.getString(this, "shopId", "");
        ModuleBean moduleBean = new ModuleBean();
        moduleBean.phone = phone;
        moduleBean.pageNum = pageNum + "";
        dataRepository.returnGoods(token, shopId, moduleBean, new RemotDataSource.getCallback() {
            @Override
            public void onFailure(String info) {
                ViewLoading.dismiss(ReturnGoodsActivity.this);
                refresh.finishLoadMore();
                refresh.finishRefresh();
            }

            @Override
            public void onSuccess(Object data) {
                refresh.finishLoadMore();
                refresh.finishRefresh();
                ViewLoading.dismiss(ReturnGoodsActivity.this);
                ReturnGoods returnGoods = (ReturnGoods) data;
                if (returnGoods.getCode() == 200) {
                    if (isLastPage) {
                        dataBean.addAll(returnGoods.getData().getList());
                        returnGoodsAdapter.setData(dataBean);
                        if (returnGoods.getData().isIsLastPage() == true) {
                            isLastPage = false;
                        } else {
                            isLastPage = true;
                        }
                    } else {
                        ToastUtils.showToast("已经到底啦");
                    }
                }
            }
        });
    }

    private void returnHistory(int pageNum1, String phone1) {
        ViewLoading.show(this);
        String token = SpUtil.getString(this, "token", "");
        String shopId = SpUtil.getString(this, "shopId", "");
        ModuleBean moduleBean = new ModuleBean();
        moduleBean.phoneAndNickName = phone1;
        moduleBean.pageNum = pageNum1 + "";
        dataRepository.returnConfirmHistory(token, shopId, moduleBean, new RemotDataSource.getCallback() {
            @Override
            public void onFailure(String info) {
                ViewLoading.dismiss(ReturnGoodsActivity.this);
                refresh1.finishLoadMore();
                refresh1.finishRefresh();
            }

            @Override
            public void onSuccess(Object data) {
                refresh1.finishLoadMore();
                refresh1.finishRefresh();
                ViewLoading.dismiss(ReturnGoodsActivity.this);
                ReturnGoods returnGoods = (ReturnGoods) data;
                if (returnGoods.getCode() == 200) {
                    if (isLastPage1) {
                        dataBean1.addAll(returnGoods.getData().getList());
                        returnHistoryAdapter.setData(dataBean1);
                        if (returnGoods.getData().isIsLastPage() == true) {
                            isLastPage1 = false;
                        } else {
                            isLastPage1 = true;
                        }
                    } else {
                        ToastUtils.showToast("已经到底啦");
                    }
                }
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.tv_dimension, R.id.tv_query, R.id.tv_clear, R.id.tv_query1, R.id.tv_clear1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_dimension:
                if (isClick) {
                    isClick = false;
                    tvDimension.setText("客户退货确认");
                    rlReturnGoods.setVisibility(View.GONE);
                    rlReturnHistory.setVisibility(View.VISIBLE);
                    pageNum1 = 1;
                    dataBean1.clear();
                    isLastPage1 = true;
                    returnHistory(pageNum1, phone1);
                } else {
                    isClick = true;
                    tvDimension.setText("历史确认");
                    rlReturnGoods.setVisibility(View.VISIBLE);
                    rlReturnHistory.setVisibility(View.GONE);
                    pageNum = 1;
                    dataBean.clear();
                    isLastPage = true;
                    returnGoods(pageNum, phone);
                }
                break;
            case R.id.tv_query:
                if (editPhone.getText().toString().length() > 0) {
                    String telRegex = "[1][3456789]\\d{9}";
                    boolean matches = editPhone.getText().toString().matches(telRegex);
                    if (matches == false) {
                        ToastUtils.showToast("请输入正确的手机号码");
                    } else {
                        phone = editPhone.getText().toString();
                        pageNum = 1;
                        dataBean.clear();
                        isLastPage = true;
                        returnGoods(pageNum, phone);
                    }
                } else {
                    ToastUtils.showToast("请输入手机号");
                }
                break;
            case R.id.tv_clear:
                phone = "";
                editPhone.setText("");
                break;
            case R.id.tv_query1:
                if (editPhone1.getText().toString().length() > 0) {
                    phone1 = editPhone1.getText().toString();
                    pageNum1 = 1;
                    dataBean1.clear();
                    isLastPage1 = true;
                    returnHistory(pageNum1, phone1);
                } else {
                    ToastUtils.showToast("请输入手机号或昵称");
                }
                break;
            case R.id.tv_clear1:
                phone1 = "";
                editPhone1.setText("");
                break;
        }
    }

    @Override
    public void onItemClick(int position) {

    }
}