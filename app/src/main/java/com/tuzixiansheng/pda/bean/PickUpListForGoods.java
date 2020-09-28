package com.tuzixiansheng.pda.bean;

import java.util.List;

public class PickUpListForGoods {
    /**
     * msg : 操作成功
     * code : 200
     * data : [{"skuCode":"30791","skuName":"瓷盆栽九尾狐","skuStandard":"1盆","pickNum":"2"}]
     */

    private String msg;
    private int code;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * skuCode : 30791
         * skuName : 瓷盆栽九尾狐
         * skuStandard : 1盆
         * pickNum : 2
         */

        private String skuCode;
        private String skuName;
        private String skuStandard;
        private String pickNum;

        public String getSkuCode() {
            return skuCode;
        }

        public void setSkuCode(String skuCode) {
            this.skuCode = skuCode;
        }

        public String getSkuName() {
            return skuName;
        }

        public void setSkuName(String skuName) {
            this.skuName = skuName;
        }

        public String getSkuStandard() {
            return skuStandard;
        }

        public void setSkuStandard(String skuStandard) {
            this.skuStandard = skuStandard;
        }

        public String getPickNum() {
            return pickNum;
        }

        public void setPickNum(String pickNum) {
            this.pickNum = pickNum;
        }
    }
}
