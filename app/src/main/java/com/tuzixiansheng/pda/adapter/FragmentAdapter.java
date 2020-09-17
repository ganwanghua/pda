package com.tuzixiansheng.pda.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Kun on 2016/12/20.
 * GitHub: https://github.com/AndroidKun
 * CSDN: http://blog.csdn.net/a1533588867
 * Description:
 */

public class FragmentAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragments;

    public FragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
