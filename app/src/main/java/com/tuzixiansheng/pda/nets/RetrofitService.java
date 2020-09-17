package com.tuzixiansheng.pda.nets;

import com.tuzixiansheng.pda.bean.ModuleBean;
import com.tuzixiansheng.pda.bean.PdaLoginRecord;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by whs on 2017/2/17
 * Retrofit 接口
 */

public interface RetrofitService {
    // PDA登录
    @POST("admin/app/home/pdaLogin")
    Observable<PdaLoginRecord> pdaLogin(@Body ModuleBean moduleBean);
}
