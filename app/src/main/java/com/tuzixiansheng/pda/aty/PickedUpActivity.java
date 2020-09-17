package com.tuzixiansheng.pda.aty;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.tuzixiansheng.pda.R;
import com.tuzixiansheng.pda.adapter.ClassifyAdapter;
import com.tuzixiansheng.pda.adapter.CollectTodayAdapter;
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

public class PickedUpActivity extends BaseActivity implements ClassifyAdapter.MyItemClickListener {

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
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    private DataRepository dataRepository;
    private List<String> mList = new ArrayList<>();
    private List<TitleBean> titleBeans = new ArrayList<>();
    private ClassifyAdapter classifyAdapter;
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
        mList.clear();
        mList.add("当日待取（" + dataBeans.size() + ")");
        mList.add("历史待取（" + dataBeans.size() + ")");
        mList.add("取货中（" + dataBeans.size() + ")");
        recycleView.setLayoutManager(new LinearLayoutManager(PickedUpActivity.this));
        classifyAdapter = new ClassifyAdapter(PickedUpActivity.this, mList);
        recycleView.setAdapter(classifyAdapter);
        classifyAdapter.setItemClickListener(this);

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
                classifyAdapter.singleChoose(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onItemClick(View view, int position) {
        pickType = String.valueOf((position+1));
        classifyAdapter.singleChoose(position);
        viewPager.setCurrentItem(position);
    }
}