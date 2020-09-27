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

    void pdaLogin(String terminal, ModuleBean moduleBean, getCallback callback);

    void pickUpDetail(String token, ModuleBean moduleBean, getCallback callback);

    void pickUpList(String token, ModuleBean moduleBean, getCallback callback);
}
