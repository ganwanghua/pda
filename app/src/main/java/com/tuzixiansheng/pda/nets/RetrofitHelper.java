package com.tuzixiansheng.pda.nets;

import android.content.Context;
import android.util.Log;

import com.google.gson.GsonBuilder;
import com.pedaily.yc.ycdialoglib.toast.ToastUtils;
import com.tuzixiansheng.pda.R;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by whs on 2017/2/17
 * Retrofit 初始化
 */


public class RetrofitHelper {
    private Context mContext;

    public NetworkMonitor networkMonitor;
    private static RetrofitHelper instance = null;
    private Retrofit mRetrofit = null;
    private Retrofit mRetrofit1 = null;

    public static RetrofitHelper getInstance(Context context) {
        if (instance == null) {
            instance = new RetrofitHelper(context);

        }
        return instance;
    }

    private RetrofitHelper(Context mContext) {
        this.mContext = mContext;
        networkMonitor = new LiveNetworkMonitor(mContext);
        init();
    }

    private void init() {
        resetApp();
    }

    private void resetApp() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.addNetworkInterceptor(new MyNetworkInterceptor());
        okHttpClientBuilder.addInterceptor(new UrlInterceptor());
        //5秒超时
        okHttpClientBuilder.connectTimeout(20, TimeUnit.SECONDS);

        mRetrofit = new Retrofit.Builder()
                .baseUrl(mContext.getResources().getString(R.string.serverhost))
                .client(okHttpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mRetrofit1 = new Retrofit.Builder()
                .baseUrl(mContext.getResources().getString(R.string.serverhost1))
                .client(okHttpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    private class MyNetworkInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            if (networkMonitor.isConnected()) {
                Log.d("requestUrl", chain.request().url().toString());
                return chain.proceed(chain.request());
            } else {
                //throw new NoNetworkException();
                ToastUtils.showToast("无网络连接，请检查网络");
            }
            return null;
        }
    }

    private class UrlInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Log.i("requestUrl", chain.request().url().toString());
            return chain.proceed(chain.request());
        }
    }

    /**
     * 常规接口
     *
     * @return
     */
    public RetrofitService getServer() {
        return mRetrofit.create(RetrofitService.class);
    }

    public RetrofitService getServer1() {
        return mRetrofit1.create(RetrofitService.class);
    }
}
