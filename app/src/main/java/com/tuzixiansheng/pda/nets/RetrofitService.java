package com.tuzixiansheng.pda.nets;

import com.tuzixiansheng.pda.bean.ModuleBean;
import com.tuzixiansheng.pda.bean.PdaLoginRecord;
import com.tuzixiansheng.pda.bean.PickUpDetailRecord;
import com.tuzixiansheng.pda.bean.PickUpListForGoods;
import com.tuzixiansheng.pda.bean.PickUpRecord;
import com.tuzixiansheng.pda.bean.PickedDetail;
import com.tuzixiansheng.pda.bean.ReturnGoods;

import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
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

    // 待取列表-用户维度
    @POST("/pda/pdaInfo/pickUpList")
    Observable<PickUpRecord> pickUpList(@Header("Authorization") String token, @Body ModuleBean moduleBean);

    // 待取列表-商品维度
    @POST("/pda/pdaInfo/pickUpListForGoods")
    Observable<PickUpListForGoods> pickUpListForGoods(@Header("Authorization") String token, @Body ModuleBean moduleBean);

    // 已取列表
    @POST("/pda/pdaInfo/alreadyPickUpGoods")
    Observable<PickUpRecord> alreadyPickUpGoods(@Header("Authorization") String token, @Body ModuleBean moduleBean);

    // 已取列表
    @POST("/pda/pdaInfo/pickedDetail")
    Observable<PickedDetail> pickedDetail(@Header("Authorization") String token, @Body ModuleBean moduleBean);

    // 退货确认列表
    @POST("/pda/pdaInfo/returnConfirmList/{shopId}")
    Observable<ReturnGoods> returnGoods(@Header("Authorization") String token, @Path("shopId") String shopId, @Body ModuleBean moduleBean);

    // 退货历史列表
    @POST("/pda/pdaInfo/returnConfirmHistory/{shopId}")
    Observable<ReturnGoods> returnConfirmHistory(@Header("Authorization") String token, @Path("shopId") String shopId, @Body ModuleBean moduleBean);
}
