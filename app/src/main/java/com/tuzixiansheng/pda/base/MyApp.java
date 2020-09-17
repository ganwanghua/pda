package com.tuzixiansheng.pda.base;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.android.barcodescandemo.ScannerInerface;
import com.pedaily.yc.ycdialoglib.toast.ToastUtils;
import com.tuzixiansheng.pda.greendao.DaoMaster;
import com.tuzixiansheng.pda.greendao.DaoSession;

public class MyApp extends Application {
    private static MyApp sInstance;
    private static DaoSession mDaoSession;
    public static final String DB_NAME = "pda.db";
    public static ScannerInerface Controll;

    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtils.init(this);
        sInstance = this;

        Controll = new ScannerInerface(this);
        Controll.open();
        Controll.setOutputMode(1);

        setDatabase();
    }

    private void setDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, DB_NAME, null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
    }

    public static MyApp getInstance() {
        return sInstance;
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}
