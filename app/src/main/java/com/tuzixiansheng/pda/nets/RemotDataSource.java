package com.tuzixiansheng.pda.nets;

import com.tuzixiansheng.pda.bean.ModuleBean;

/**
 * Created by whs on 2017/6/7
 */

public interface RemotDataSource {
    interface getCallback {

        void onFailure(String info);

        void onSuccess(Object data);
    }

    void pdaLogin(ModuleBean moduleBean, getCallback callback);

    void pdaList(String token, getCallback callback);

    void pickUpDetail(ModuleBean moduleBean, getCallback callback);

    void toVerify(ModuleBean moduleBean, getCallback callback);

    void defect(ModuleBean moduleBean, getCallback callback);

    void noCode(ModuleBean moduleBean, getCallback callback);

    void refuse(ModuleBean moduleBean, getCallback callback);

    void barter(ModuleBean moduleBean, getCallback callback);

    void pickUpList(String token, ModuleBean moduleBean, getCallback callback);

    void pickUpListForGoods(ModuleBean moduleBean, getCallback callback);

    void alreadyPickUpGoods(String token, String date_type, String date, String type, int page, int store_id, getCallback callback);

    void pickedDetail(String token, ModuleBean moduleBean, getCallback callback);

    void returnGoods(String token, String shopId, ModuleBean moduleBean, getCallback callback);

    void returnConfirmHistory(String token, String shopId, ModuleBean moduleBean, getCallback callback);
}
