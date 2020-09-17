package com.tuzixiansheng.pda.aty.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tuzixiansheng.pda.R;

public class CollectTodayFragment extends Fragment {
    private String phone, name, num;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public CollectTodayFragment(String phone, String name, String num) {
        this.phone = phone;
        this.name = name;
        this.num = num;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_collect_today, container, false);
    }
}