package com.tuzixiansheng.pda.nets;

import android.content.Context;

import com.tuzixiansheng.pda.bean.ModuleBean;

/**
 * Created by whs on 2017/5/18
 */

public class DataRepository implements RemotDataSource {
    private Context mContext;

    private static DataRepository INSTANCE = null;


    private final RemotDataSourceImpl mRemoteDataSource;

    public static DataRepository getInstance(RemotDataSourceImpl remoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new DataRepository(remoteDataSource);
        }
        return INSTANCE;
    }

    private DataRepository(RemotDataSourceImpl remoteDataSource) {
        //this.mContext=context;
        this.mRemoteDataSource = remoteDataSource;
    }

    @Override
    public void pdaLogin(ModuleBean moduleBean, getCallback callback) {
        mRemoteDataSource.pdaLogin(moduleBean, callback);
    }

    @Override
    public void pickUpDetail(String token, ModuleBean moduleBean, getCallback callback) {
        mRemoteDataSource.pickUpDetail(token, moduleBean, callback);
    }
}
