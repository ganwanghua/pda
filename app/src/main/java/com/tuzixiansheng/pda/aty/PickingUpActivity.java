package com.tuzixiansheng.pda.aty;

import android.os.Bundle;

import com.tuzixiansheng.pda.R;
import com.tuzixiansheng.pda.base.BaseActivity;
import com.tuzixiansheng.pda.utils.StatusBarUtil;

public class PickingUpActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initWhite();
        StatusBarUtil.StatusBarLightMode(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picking_up);
    }
}