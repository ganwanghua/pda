package com.tuzixiansheng.pda.aty.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pedaily.yc.ycdialoglib.dialog.loading.ViewLoading;
import com.pedaily.yc.ycdialoglib.toast.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.tuzixiansheng.pda.R;
import com.tuzixiansheng.pda.adapter.CollectTodayAdapter;
import com.tuzixiansheng.pda.bean.PickUpRecord;
import com.tuzixiansheng.pda.nets.DataRepository;
import com.tuzixiansheng.pda.nets.Injection;
import com.tuzixiansheng.pda.nets.RemotDataSource;
import com.tuzixiansheng.pda.utils.SpUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class PickedFragment extends Fragment implements CollectTodayAdapter.MyItemClickListener, OnRefreshLoadMoreListener {
    private String pickType;
    private String time = "";
    private RecyclerView recycler_view;
    private CardView card;
    private CollectTodayAdapter collectTodayAdapter;
    private DataRepository dataRepository;
    private int page = 1;
    private TextView tv_empty;
    private List<PickUpRecord.DataBeanX.DataBean> pickUp = new ArrayList<>();
    private SmartRefreshLayout smartRefreshLayout;
    private boolean isLastPage = true;

    public PickedFragment(String pickType) {
        this.pickType = pickType;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_picked, container, false);
        initView(v);
        return v;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 100, sticky = false) //在ui线程执行，优先级为100
    public void onEvent(String s) {
        if (pickType.equals(SpUtil.getString(getContext(), "type", ""))) {
            pickUp.clear();
            pickUpList(SpUtil.getString(getContext(), "type", ""), page, s);
        }
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    private void initView(View v) {
        EventBus.getDefault().register(this);
        dataRepository = Injection.dataRepository(getActivity());
        recycler_view = v.findViewById(R.id.recycler_view);
        card = v.findViewById(R.id.card);
        tv_empty = v.findViewById(R.id.tv_empty);

        smartRefreshLayout = v.findViewById(R.id.refresh);
        smartRefreshLayout.setOnRefreshLoadMoreListener(this);

        collectTodayAdapter = new CollectTodayAdapter(getActivity());
        recycler_view.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recycler_view.setAdapter(collectTodayAdapter);
        collectTodayAdapter.setItemClickListener(this);

        pickUpList(pickType, page, time);
    }

    private void pickUpList(String pickType, int page, String time) {
        ViewLoading.show(getActivity());
        dataRepository.alreadyPickUpGoods(SpUtil.getString(getContext(), "token", ""), pickType, time, "finish", page, SpUtil.getInt(getContext(), "shopId", -1), new RemotDataSource.getCallback() {
            @Override
            public void onFailure(String info) {
                ViewLoading.dismiss(getActivity());
                smartRefreshLayout.finishLoadMore();
                smartRefreshLayout.finishRefresh();
            }

            @Override
            public void onSuccess(Object data) {
                ViewLoading.dismiss(getActivity());
                smartRefreshLayout.finishLoadMore();
                smartRefreshLayout.finishRefresh();
                PickUpRecord pickUpRecord = (PickUpRecord) data;
                if (pickUpRecord.getCode() == 1) {
                    if (pickUpRecord.getData().getData() != null && pickUpRecord.getData().getData().size() != 0) {
                        card.setVisibility(View.VISIBLE);
                        tv_empty.setVisibility(View.GONE);
                        if (page <= pickUpRecord.getData().getLast_page()) {
                            isLastPage = false;
                            pickUp.addAll(pickUpRecord.getData().getData());
                            collectTodayAdapter.setData(pickUp);
                        } else {
                            ToastUtils.showToast("已经到底啦");
                        }
                    } else {
                        if (isLastPage) {
                            card.setVisibility(View.GONE);
                            tv_empty.setVisibility(View.VISIBLE);
                        } else {
                            ToastUtils.showToast("已经到底啦");
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onItemClick(int position) {
//        Intent intent = new Intent(getContext(), PickedDetailActivity.class);
//        intent.putExtra("phone", pickUp.get(position).getPhone());
//        startActivity(intent);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        page++;
        pickUpList(pickType, page, time);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        page = 1;
        pickUp.clear();
        pickUpList(pickType, page, time);
    }
}