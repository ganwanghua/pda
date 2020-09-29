package com.tuzixiansheng.pda.aty.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pedaily.yc.ycdialoglib.dialog.loading.ViewLoading;
import com.tuzixiansheng.pda.R;
import com.tuzixiansheng.pda.adapter.CollectTodayAdapter;
import com.tuzixiansheng.pda.aty.PickedDetailActivity;
import com.tuzixiansheng.pda.aty.PickingUpDetailActivity;
import com.tuzixiansheng.pda.bean.ModuleBean;
import com.tuzixiansheng.pda.bean.NumBean;
import com.tuzixiansheng.pda.bean.PickUpRecord;
import com.tuzixiansheng.pda.nets.DataRepository;
import com.tuzixiansheng.pda.nets.Injection;
import com.tuzixiansheng.pda.nets.RemotDataSource;
import com.tuzixiansheng.pda.utils.SpUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class PickedFragment extends Fragment implements CollectTodayAdapter.MyItemClickListener {
    private String pickType, phone;
    private RecyclerView recycler_view;
    private CollectTodayAdapter collectTodayAdapter;
    private DataRepository dataRepository;
    private int num;
    private List<PickUpRecord.DataBean> pickUp;

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
            pickUpList(SpUtil.getString(getContext(), "type", ""), s);
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

        collectTodayAdapter = new CollectTodayAdapter(getActivity());
        recycler_view.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recycler_view.setAdapter(collectTodayAdapter);
        collectTodayAdapter.setItemClickListener(this);

        pickUpList(pickType, phone);
    }

    private void pickUpList(String pickType, String phone) {
        ViewLoading.show(getActivity());
        String token = SpUtil.getString(getActivity(), "token", "");
        String shopId = SpUtil.getString(getActivity(), "shopId", "");
        ModuleBean moduleBean = new ModuleBean();
        moduleBean.phone = phone;
        moduleBean.shopId = shopId;
        moduleBean.dayType = pickType;
        moduleBean.beginTime = "";
        moduleBean.endTime = "";
        dataRepository.alreadyPickUpGoods(token, moduleBean, new RemotDataSource.getCallback() {
            @Override
            public void onFailure(String info) {
                ViewLoading.dismiss(getActivity());
            }

            @Override
            public void onSuccess(Object data) {
                ViewLoading.dismiss(getActivity());
                PickUpRecord pickUpRecord = (PickUpRecord) data;
                if (pickUpRecord.getCode() == 200) {
                    if (pickUpRecord.getData() != null && pickUpRecord.getData().size() != 0) {
                        pickUp = pickUpRecord.getData();
                        collectTodayAdapter.setData(pickUpRecord.getData());
                    }
                }
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getContext(), PickedDetailActivity.class);
        intent.putExtra("phone", pickUp.get(position).getPhone());
        startActivity(intent);
    }
}