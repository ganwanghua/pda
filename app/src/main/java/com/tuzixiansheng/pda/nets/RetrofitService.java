package com.tuzixiansheng.pda.nets;

import com.tuzixiansheng.pda.bean.ModuleBean;
import com.tuzixiansheng.pda.bean.PdaLoginRecord;
import com.tuzixiansheng.pda.bean.PdaShopList;
import com.tuzixiansheng.pda.bean.PickUpDetailRecord;
import com.tuzixiansheng.pda.bean.PickUpListForGoods;
import com.tuzixiansheng.pda.bean.PickUpRecord;
import com.tuzixiansheng.pda.bean.PickedDetail;
import com.tuzixiansheng.pda.bean.ReturnGoods;
import com.tuzixiansheng.pda.bean.VerifyBean;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by whs on 2017/2/17
 * Retrofit 接口
 */

public interface RetrofitService {
    // PDA登录
    @POST("user/accountLogin")
    Observable<PdaLoginRecord> pdaLogin(@Body ModuleBean moduleBean);

    // PDA门店列表
    @GET("store/index")
    Observable<PdaShopList> pdaList(@Query("token") String token);

    // 个人待取货详情列表
    @Headers({"Content-Type:application/json", "Accept:application/json"})
    @POST("verify/list")
    Observable<PickUpDetailRecord> pickUpDetail(@Body ModuleBean moduleBean);

    // 核销商品
    @Headers({"Content-Type:application/json", "Accept:application/json"})
    @POST("verify/toVerify")
    Observable<VerifyBean> toVerify(@Body ModuleBean moduleBean);

    // 核销缺货
    @Headers({"Content-Type:application/json", "Accept:application/json"})
    @POST("verify/defect")
    Observable<VerifyBean> defect(@Body ModuleBean moduleBean);

    // 核销拒收
    @Headers({"Content-Type:application/json", "Accept:application/json"})
    @POST("verify/refuse")
    Observable<VerifyBean> refuse(@Body ModuleBean moduleBean);

    // 核销原货交付无码
    @Headers({"Content-Type:application/json", "Accept:application/json"})
    @POST("verify/noCode")
    Observable<VerifyBean> noCode(@Body ModuleBean moduleBean);

    // 核销换货
    @Headers({"Content-Type:application/json", "Accept:application/json"})
    @POST("verify/barter")
    Observable<VerifyBean> barter(@Body ModuleBean moduleBean);

    // 待取列表-用户维度
    @POST("/pda/pdaInfo/pickUpList")
    Observable<PickUpRecord> pickUpList(@Header("Authorization") String token, @Body ModuleBean moduleBean);

    // 待取列表-商品维度
    @POST("order/waitTake")
    Observable<PickUpListForGoods> pickUpListForGoods(@Body ModuleBean moduleBean);

    // 已取列表
    @GET("store.order/pda_order_list")
    Observable<PickUpRecord> alreadyPickUpGoods(@Query("token") String token, @Query("date_type") String date_type
            , @Query("date") String date, @Query("type") String type, @Query("page") int page, @Query("store_id") int store_id);

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
