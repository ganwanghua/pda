package com.tuzixiansheng.pda.bean;

import java.util.List;

public class PickUpDetailRecord {

    /**
     * msg : 操作成功
     * code : 200
     * data : [{"orderSkuId":"1","skuId":"234fafcd09294e4c8ec890f08db6bab3","skuName":"淮南大白菜","skuCode":"10001","skuNum":"10","isNow":true,"prePickTime":"2020-07-28"}]
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
         * orderSkuId : 1
         * skuId : 234fafcd09294e4c8ec890f08db6bab3
         * skuName : 淮南大白菜
         * skuCode : 10001
         * skuNum : 10
         * isNow : true
         * prePickTime : 2020-07-28
         */

        private String orderSkuId;
        private String skuId;
        private String skuName;
        private String skuCode;
        private String skuNum;
        private boolean isNow;
        private String prePickTime;
        private String skuStandard;

        public boolean isNow() {
            return isNow;
        }

        public void setNow(boolean now) {
            isNow = now;
        }

        public String getSkuStandard() {
            return skuStandard;
        }

        public void setSkuStandard(String skuStandard) {
            this.skuStandard = skuStandard;
        }

        public String getOrderSkuId() {
            return orderSkuId;
        }

        public void setOrderSkuId(String orderSkuId) {
            this.orderSkuId = orderSkuId;
        }

        public String getSkuId() {
            return skuId;
        }

        public void setSkuId(String skuId) {
            this.skuId = skuId;
        }

        public String getSkuName() {
            return skuName;
        }

        public void setSkuName(String skuName) {
            this.skuName = skuName;
        }

        public String getSkuCode() {
            return skuCode;
        }

        public void setSkuCode(String skuCode) {
            this.skuCode = skuCode;
        }

        public String getSkuNum() {
            return skuNum;
        }

        public void setSkuNum(String skuNum) {
            this.skuNum = skuNum;
        }

        public boolean isIsNow() {
            return isNow;
        }

        public void setIsNow(boolean isNow) {
            this.isNow = isNow;
        }

        public String getPrePickTime() {
            return prePickTime;
        }

        public void setPrePickTime(String prePickTime) {
            this.prePickTime = prePickTime;
        }
    }
}
