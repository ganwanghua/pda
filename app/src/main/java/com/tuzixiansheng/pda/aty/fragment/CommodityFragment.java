package com.tuzixiansheng.pda.aty.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tuzixiansheng.pda.R;
import com.tuzixiansheng.pda.adapter.CommodityAdapter;
import com.tuzixiansheng.pda.bean.ModuleBean;
import com.tuzixiansheng.pda.bean.NumBean;
import com.tuzixiansheng.pda.bean.PickUpDetailRecord;
import com.tuzixiansheng.pda.bean.PickUpListForGoods;
import com.tuzixiansheng.pda.nets.DataRepository;
import com.tuzixiansheng.pda.nets.Injection;
import com.tuzixiansheng.pda.nets.RemotDataSource;
import com.tuzixiansheng.pda.utils.SpUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class CommodityFragment extends Fragment implements CommodityAdapter.MyItemClickListener {
    private String pickType;
    private RecyclerView recycler_view;
    private CommodityAdapter commodityAdapter;
    private DataRepository dataRepository;
    private int num;
    private List<PickUpListForGoods.DataBean> pickUp;

    public CommodityFragment(String pickType) {
        this.pickType = pickType;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_commodity, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        dataRepository = Injection.dataRepository(getActivity());
        recycler_view = v.findViewById(R.id.recycler_view);

        commodityAdapter = new CommodityAdapter(getActivity());
        recycler_view.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recycler_view.setAdapter(commodityAdapter);
        commodityAdapter.setItemClickListener(this);

        pickUpList(pickType);
    }

    private void pickUpList(String pickType) {
        String token = SpUtil.getString(getActivity(), "token", "");
        String shopId = SpUtil.getString(getActivity(), "shopId", "");
        ModuleBean moduleBean = new ModuleBean();
        moduleBean.shopId = shopId;
        moduleBean.pickType = pickType;
        dataRepository.pickUpListForGoods(token, moduleBean, new RemotDataSource.getCallback() {
            @Override
            public void onFailure(String info) {

            }

            @Override
            public void onSuccess(Object data) {
                PickUpListForGoods pickUpRecord = (PickUpListForGoods) data;
                if (pickUpRecord.getCode() == 200) {
                    if (pickUpRecord.getData() != null && pickUpRecord.getData().size() != 0) {
                        pickUp = pickUpRecord.getData();
                        commodityAdapter.setData(pickUpRecord.getData());
                        for (int i = 0; i < pickUpRecord.getData().size(); i++) {
                            num = num + Integer.parseInt(pickUpRecord.getData().get(i).getPickNum());
                        }
                        if (pickType.equals("1")) {
                            NumBean numBean = new NumBean("4", num);
                            EventBus.getDefault().post(numBean);
                        }else if (pickType.equals("2")) {
                            NumBean numBean = new NumBean("5", num);
                            EventBus.getDefault().post(numBean);
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onItemClick(int position) {
//        Intent intent = new Intent(getContext(), PickingUpDetailActivity.class);
//        intent.putExtra("phone", pickUp.get(position).getPhone());
//        intent.putExtra("pos", "1");
//        startActivity(intent);
    }
}