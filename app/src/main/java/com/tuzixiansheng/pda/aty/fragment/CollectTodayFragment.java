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
import com.tuzixiansheng.pda.adapter.PickedUpAdapter;
import com.tuzixiansheng.pda.aty.PickingUpDetailActivity;
import com.tuzixiansheng.pda.bean.ModuleBean;
import com.tuzixiansheng.pda.bean.NumBean;
import com.tuzixiansheng.pda.bean.PickUpRecord;
import com.tuzixiansheng.pda.bean.TitleBean;
import com.tuzixiansheng.pda.nets.DataRepository;
import com.tuzixiansheng.pda.nets.Injection;
import com.tuzixiansheng.pda.nets.RemotDataSource;
import com.tuzixiansheng.pda.utils.SpUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class CollectTodayFragment extends Fragment implements CollectTodayAdapter.MyItemClickListener {
    private String pickType;
    private RecyclerView recycler_view;
    private CollectTodayAdapter collectTodayAdapter;
    private DataRepository dataRepository;
    private int num;
    private List<PickUpRecord.DataBean> pickUp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public CollectTodayFragment(String pickType) {
        this.pickType = pickType;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_collect_today, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        dataRepository = Injection.dataRepository(getActivity());
        recycler_view = v.findViewById(R.id.recycler_view);

        collectTodayAdapter = new CollectTodayAdapter(getActivity());
        recycler_view.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recycler_view.setAdapter(collectTodayAdapter);
        collectTodayAdapter.setItemClickListener(this);

        pickUpList(pickType);
    }

    private void pickUpList(String pickType) {
        ViewLoading.show(getActivity());
        String token = SpUtil.getString(getActivity(), "token", "");
        String shopId = SpUtil.getString(getActivity(), "shopId", "");
        ModuleBean moduleBean = new ModuleBean();
        moduleBean.shopId = shopId;
        moduleBean.pickType = pickType;
        dataRepository.pickUpList(token, moduleBean, new RemotDataSource.getCallback() {
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
                        for (int i = 0; i < pickUpRecord.getData().size(); i++) {
                            num = num + Integer.parseInt(pickUpRecord.getData().get(i).getPickNum());
                        }
                        if (pickType.equals("1")) {
                            NumBean numBean = new NumBean("1", num);
                            EventBus.getDefault().post(numBean);
                        }else if (pickType.equals("2")) {
                            NumBean numBean = new NumBean("2", num);
                            EventBus.getDefault().post(numBean);
                        }else if (pickType.equals("3")) {
                            NumBean numBean = new NumBean("3", num);
                            EventBus.getDefault().post(numBean);
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getContext(), PickingUpDetailActivity.class);
        intent.putExtra("phone", pickUp.get(position).getPhone());
        intent.putExtra("pos", "1");
        startActivity(intent);
    }
}