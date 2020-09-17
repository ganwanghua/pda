package com.tuzixiansheng.pda.nets;


import android.content.Context;

import com.tuzixiansheng.pda.bean.ModuleBean;
import com.tuzixiansheng.pda.bean.PdaLoginRecord;
import com.tuzixiansheng.pda.bean.PickUpDetailRecord;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by whs on 2017/5/18
 */

public class RemotDataSourceImpl implements RemotDataSource {

    private static Context mContext;

    private static class Laydz {
        private static RemotDataSourceImpl instance = new RemotDataSourceImpl();
    }


    public static RemotDataSourceImpl getInstance(Context context) {
        mContext = context;
        return Laydz.instance;
    }

    @Override
    public void pdaLogin(ModuleBean moduleBean, final getCallback callback) {
        Observable<PdaLoginRecord> observable = RetrofitHelper.getInstance(mContext).getServer().pdaLogin(moduleBean);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PdaLoginRecord>() {
                    @Override
                    public void onCompleted() { // 完成请求后

                    }

                    @Override
                    public void onError(Throwable e) { // 异常处理
                        callback.onFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(PdaLoginRecord s) { // 请求成功
                        callback.onSuccess(s);
                    }
                });
    }

    @Override
    public void pickUpDetail(String token, ModuleBean moduleBean, final getCallback callback) {
        Observable<PickUpDetailRecord> observable = RetrofitHelper.getInstance(mContext).getServer().pickUpDetail(token, moduleBean);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PickUpDetailRecord>() {
                    @Override
                    public void onCompleted() { // 完成请求后

                    }

                    @Override
                    public void onError(Throwable e) { // 异常处理
                        callback.onFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(PickUpDetailRecord s) { // 请求成功
                        callback.onSuccess(s);
                    }
                });
    }
}
