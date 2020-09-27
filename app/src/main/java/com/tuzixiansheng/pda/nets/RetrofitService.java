package com.tuzixiansheng.pda.nets;

import com.tuzixiansheng.pda.bean.ModuleBean;
import com.tuzixiansheng.pda.bean.PdaLoginRecord;
import com.tuzixiansheng.pda.bean.PickUpDetailRecord;
import com.tuzixiansheng.pda.bean.PickUpRecord;

import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by whs on 2017/2/17
 * Retrofit 接口
 */

public interface RetrofitService {
    // PDA登录
    @POST("/auth/login")
    Observable<PdaLoginRecord> pdaLogin(@Header("terminal") String terminal, @Body ModuleBean moduleBean);

    // 个人待取货详情列表
    @POST("/pda/pdaInfo/pickUpDetail")
    Observable<PickUpDetailRecord> pickUpDetail(@Header("Authorization") String token, @Body ModuleBean moduleBean);

    // 待取列表
    @POST("admin/app/pda/pickUpList")
    Observable<PickUpRecord> pickUpList(@Header("Authorization") String token, @Body ModuleBean moduleBean);
}
