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
    public void pdaList(String token, getCallback callback) {
        mRemoteDataSource.pdaList(token, callback);
    }

    @Override
    public void pickUpDetail(ModuleBean moduleBean, getCallback callback) {
        mRemoteDataSource.pickUpDetail(moduleBean, callback);
    }

    @Override
    public void toVerify(ModuleBean moduleBean, getCallback callback) {
        mRemoteDataSource.toVerify(moduleBean, callback);
    }

    @Override
    public void defect(ModuleBean moduleBean, getCallback callback) {
        mRemoteDataSource.defect(moduleBean, callback);
    }

    @Override
    public void noCode(ModuleBean moduleBean, getCallback callback) {
        mRemoteDataSource.noCode(moduleBean, callback);
    }

    @Override
    public void refuse(ModuleBean moduleBean, getCallback callback) {
        mRemoteDataSource.refuse(moduleBean, callback);
    }

    @Override
    public void barter(ModuleBean moduleBean, getCallback callback) {
        mRemoteDataSource.barter(moduleBean, callback);
    }

    @Override
    public void pickUpList(String token, ModuleBean moduleBean, getCallback callback) {
        mRemoteDataSource.pickUpList(token, moduleBean, callback);
    }

    @Override
    public void pickUpListForGoods(ModuleBean moduleBean, getCallback callback) {
        mRemoteDataSource.pickUpListForGoods(moduleBean, callback);
    }

    @Override
    public void alreadyPickUpGoods(String token, String date_type, String date, String type, int page, int store_id, getCallback callback) {
        mRemoteDataSource.alreadyPickUpGoods(token, date_type, date, type, page, store_id, callback);
    }

    @Override
    public void pickedDetail(String token, ModuleBean moduleBean, getCallback callback) {
        mRemoteDataSource.pickedDetail(token, moduleBean, callback);
    }

    @Override
    public void returnGoods(String token, String shopId, ModuleBean moduleBean, getCallback callback) {
        mRemoteDataSource.returnGoods(token, shopId, moduleBean, callback);
    }

    @Override
    public void returnConfirmHistory(String token, String shopId, ModuleBean moduleBean, getCallback callback) {
        mRemoteDataSource.returnConfirmHistory(token, shopId, moduleBean, callback);
    }
}
