package com.tuzixiansheng.pda.nets;


import android.content.Context;

import com.tuzixiansheng.pda.bean.ModuleBean;
import com.tuzixiansheng.pda.bean.PdaLoginRecord;
import com.tuzixiansheng.pda.bean.PdaShopList;
import com.tuzixiansheng.pda.bean.PickUpDetailRecord;
import com.tuzixiansheng.pda.bean.PickUpListForGoods;
import com.tuzixiansheng.pda.bean.PickUpRecord;
import com.tuzixiansheng.pda.bean.PickedDetail;
import com.tuzixiansheng.pda.bean.ReturnGoods;
import com.tuzixiansheng.pda.bean.VerifyBean;

import retrofit2.http.Query;
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
    public void pdaList(String token, final getCallback callback) {
        Observable<PdaShopList> observable = RetrofitHelper.getInstance(mContext).getServer().pdaList(token);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PdaShopList>() {
                    @Override
                    public void onCompleted() { // 完成请求后

                    }

                    @Override
                    public void onError(Throwable e) { // 异常处理
                        callback.onFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(PdaShopList s) { // 请求成功
                        callback.onSuccess(s);
                    }
                });
    }

    @Override
    public void pickUpDetail(ModuleBean moduleBean, final getCallback callback) {
        Observable<PickUpDetailRecord> observable = RetrofitHelper.getInstance(mContext).getServer1().pickUpDetail(moduleBean);
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

    @Override
    public void toVerify(ModuleBean moduleBean, final getCallback callback) {
        Observable<VerifyBean> observable = RetrofitHelper.getInstance(mContext).getServer1().toVerify(moduleBean);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VerifyBean>() {
                    @Override
                    public void onCompleted() { // 完成请求后

                    }

                    @Override
                    public void onError(Throwable e) { // 异常处理
                        callback.onFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(VerifyBean s) { // 请求成功
                        callback.onSuccess(s);
                    }
                });
    }

    @Override
    public void defect(ModuleBean moduleBean, final getCallback callback) {
        Observable<VerifyBean> observable = RetrofitHelper.getInstance(mContext).getServer1().defect(moduleBean);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VerifyBean>() {
                    @Override
                    public void onCompleted() { // 完成请求后

                    }

                    @Override
                    public void onError(Throwable e) { // 异常处理
                        callback.onFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(VerifyBean s) { // 请求成功
                        callback.onSuccess(s);
                    }
                });
    }

    @Override
    public void noCode(ModuleBean moduleBean, final getCallback callback) {
        Observable<VerifyBean> observable = RetrofitHelper.getInstance(mContext).getServer1().noCode(moduleBean);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VerifyBean>() {
                    @Override
                    public void onCompleted() { // 完成请求后

                    }

                    @Override
                    public void onError(Throwable e) { // 异常处理
                        callback.onFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(VerifyBean s) { // 请求成功
                        callback.onSuccess(s);
                    }
                });
    }

    @Override
    public void refuse(ModuleBean moduleBean, final getCallback callback) {
        Observable<VerifyBean> observable = RetrofitHelper.getInstance(mContext).getServer1().refuse(moduleBean);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VerifyBean>() {
                    @Override
                    public void onCompleted() { // 完成请求后

                    }

                    @Override
                    public void onError(Throwable e) { // 异常处理
                        callback.onFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(VerifyBean s) { // 请求成功
                        callback.onSuccess(s);
                    }
                });
    }

    @Override
    public void barter(ModuleBean moduleBean, final getCallback callback) {
        Observable<VerifyBean> observable = RetrofitHelper.getInstance(mContext).getServer1().barter(moduleBean);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VerifyBean>() {
                    @Override
                    public void onCompleted() { // 完成请求后

                    }

                    @Override
                    public void onError(Throwable e) { // 异常处理
                        callback.onFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(VerifyBean s) { // 请求成功
                        callback.onSuccess(s);
                    }
                });
    }

    @Override
    public void pickUpList(String token, ModuleBean moduleBean, final getCallback callback) {
        Observable<PickUpRecord> observable = RetrofitHelper.getInstance(mContext).getServer().pickUpList(token, moduleBean);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PickUpRecord>() {
                    @Override
                    public void onCompleted() { // 完成请求后

                    }

                    @Override
                    public void onError(Throwable e) { // 异常处理
                        callback.onFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(PickUpRecord s) { // 请求成功
                        callback.onSuccess(s);
                    }
                });
    }

    @Override
    public void pickUpListForGoods(ModuleBean moduleBean, final getCallback callback) {
        Observable<PickUpListForGoods> observable = RetrofitHelper.getInstance(mContext).getServer1().pickUpListForGoods(moduleBean);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PickUpListForGoods>() {
                    @Override
                    public void onCompleted() { // 完成请求后

                    }

                    @Override
                    public void onError(Throwable e) { // 异常处理
                        callback.onFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(PickUpListForGoods s) { // 请求成功
                        callback.onSuccess(s);
                    }
                });
    }

    @Override
    public void alreadyPickUpGoods(String token, String date_type, String date, String type, int page, int store_id, final getCallback callback) {
        Observable<PickUpRecord> observable = RetrofitHelper.getInstance(mContext).getServer().alreadyPickUpGoods(token, date_type, date, type, page, store_id);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PickUpRecord>() {
                    @Override
                    public void onCompleted() { // 完成请求后

                    }

                    @Override
                    public void onError(Throwable e) { // 异常处理
                        callback.onFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(PickUpRecord s) { // 请求成功
                        callback.onSuccess(s);
                    }
                });
    }

    @Override
    public void pickedDetail(String token, ModuleBean moduleBean, final getCallback callback) {
        Observable<PickedDetail> observable = RetrofitHelper.getInstance(mContext).getServer().pickedDetail(token, moduleBean);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PickedDetail>() {
                    @Override
                    public void onCompleted() { // 完成请求后

                    }

                    @Override
                    public void onError(Throwable e) { // 异常处理
                        callback.onFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(PickedDetail s) { // 请求成功
                        callback.onSuccess(s);
                    }
                });
    }

    @Override
    public void returnGoods(String token, String shopId, ModuleBean moduleBean, final getCallback callback) {
        Observable<ReturnGoods> observable = RetrofitHelper.getInstance(mContext).getServer().returnGoods(token, shopId, moduleBean);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReturnGoods>() {
                    @Override
                    public void onCompleted() { // 完成请求后

                    }

                    @Override
                    public void onError(Throwable e) { // 异常处理
                        callback.onFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(ReturnGoods s) { // 请求成功
                        callback.onSuccess(s);
                    }
                });
    }

    @Override
    public void returnConfirmHistory(String token, String shopId, ModuleBean moduleBean, final getCallback callback) {
        Observable<ReturnGoods> observable = RetrofitHelper.getInstance(mContext).getServer().returnConfirmHistory(token, shopId, moduleBean);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReturnGoods>() {
                    @Override
                    public void onCompleted() { // 完成请求后

                    }

                    @Override
                    public void onError(Throwable e) { // 异常处理
                        callback.onFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(ReturnGoods s) { // 请求成功
                        callback.onSuccess(s);
                    }
                });
    }
}
