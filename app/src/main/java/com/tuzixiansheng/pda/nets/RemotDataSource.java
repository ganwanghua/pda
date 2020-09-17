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
}
