package com.tuzixiansheng.pda.aty;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.tuzixiansheng.pda.R;
import com.tuzixiansheng.pda.adapter.FragmentAdapter;
import com.tuzixiansheng.pda.aty.fragment.CollectTodayFragment;
import com.tuzixiansheng.pda.base.BaseActivity;
import com.tuzixiansheng.pda.bean.ModuleBean;
import com.tuzixiansheng.pda.bean.PickUpRecord;
import com.tuzixiansheng.pda.bean.TitleBean;
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

public class PickedUpActivity extends BaseActivity {

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
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_clear)
    TextView tvClear;
    @BindView(R.id.ll_phone)
    LinearLayout llPhone;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    List<Fragment> fragments = new ArrayList<>();
    @BindView(R.id.tv_today)
    TextView tvToday;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.ll_today)
    LinearLayout llToday;
    @BindView(R.id.tv_history)
    TextView tvHistory;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.ll_history)
    LinearLayout llHistory;
    @BindView(R.id.tv_picking)
    TextView tvPicking;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.ll_picking)
    LinearLayout llPicking;
    @BindView(R.id.ll_classify)
    LinearLayout llClassify;
    private DataRepository dataRepository;
    private List<TitleBean> titleBeans = new ArrayList<>();
    private String pickType = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initWhite();
        StatusBarUtil.StatusBarLightMode(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picked_up);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        dataRepository = Injection.dataRepository(this);
        pickUpList(pickType);
    }

    private void pickUpList(String pickType) {
        String token = SpUtil.getString(this, "token", "");
        String shopId = SpUtil.getString(this, "shopId", "");
        ModuleBean moduleBean = new ModuleBean();
        moduleBean.shopId = shopId;
        moduleBean.pickType = pickType;
        dataRepository.pickUpList(token, moduleBean, new RemotDataSource.getCallback() {
            @Override
            public void onFailure(String info) {

            }

            @Override
            public void onSuccess(Object data) {
                PickUpRecord pickUpRecord = (PickUpRecord) data;
                if (pickUpRecord.getCode() == 200) {
                    if (pickUpRecord.getData() != null && pickUpRecord.getData().size() != 0) {
                        initTabData(pickUpRecord.getData());
                    }
                }
            }
        });
    }

    private void initTabData(List<PickUpRecord.DataBean> dataBeans) {
        titleBeans.clear();
        fragments.clear();
        for (int i = 0; i < dataBeans.size(); i++) {
            titleBeans.add(new TitleBean(dataBeans.get(i).getPhone(), dataBeans.get(i).getNickName(), dataBeans.get(i).getPickNum()));
        }
        for (int i = 0; i < titleBeans.size(); i++) {
            fragments.add(new CollectTodayFragment(titleBeans.get(i).getPhone(), titleBeans.get(i).getNickName(), titleBeans.get(i).getPickNum()));
        }
        FragmentAdapter adatper = new FragmentAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adatper);
        viewPager.setOffscreenPageLimit(fragments.size());
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    view.setVisibility(View.VISIBLE);
                    view1.setVisibility(View.GONE);
                    view2.setVisibility(View.GONE);
                } else if (position == 1) {
                    view.setVisibility(View.GONE);
                    view1.setVisibility(View.VISIBLE);
                    view2.setVisibility(View.GONE);
                } else {
                    view.setVisibility(View.GONE);
                    view1.setVisibility(View.GONE);
                    view2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick({R.id.iv_back, R.id.ll_today, R.id.ll_history, R.id.ll_picking})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_today:
                view.setVisibility(View.VISIBLE);
                view1.setVisibility(View.GONE);
                view2.setVisibility(View.GONE);
                pickType = "1";
                viewPager.setCurrentItem(0);
                pickUpList(pickType);
                break;
            case R.id.ll_history:
                view.setVisibility(View.GONE);
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.GONE);
                pickType = "2";
                viewPager.setCurrentItem(1);
                pickUpList(pickType);
                break;
            case R.id.ll_picking:
                view.setVisibility(View.GONE);
                view1.setVisibility(View.GONE);
                view2.setVisibility(View.VISIBLE);
                pickType = "3";
                viewPager.setCurrentItem(2);
                pickUpList(pickType);
                break;
        }
    }
}