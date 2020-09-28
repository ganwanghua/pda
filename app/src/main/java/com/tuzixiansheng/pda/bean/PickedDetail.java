package com.tuzixiansheng.pda.bean;

import java.util.List;

public class PickedDetail {
    /**
     * msg : 操作成功
     * code : 200
     * data : [{"userName":"你打不过我得","phone":"19956810688","orderNo":"FX2020092800018","actualPickTime":"2020-09-28 12:09","skus":[{"skuCode":"03011","skuName":"长豆角（豇豆）","skuStandard":"300g","orderActualPrice":"11.9","actualPickNum":"1","actualWeight":"550g","changePrice":"1.5","outStockPrice":"0","actualPrice":"19.99"},{"skuCode":"03012","skuName":"长豆角（豇豆）","skuStandard":"301g","orderActualPrice":"11.8","actualPickNum":"0","actualWeight":"550g","changePrice":"1.5","outStockPrice":"0","actualPrice":"19.99"}]},{"userName":"你打不过我得","phone":"19956810688","orderNo":"FX2020092800028","actualPickTime":"2020-09-29 12:09","skus":[{"skuCode":"06121","skuName":"薄皮青椒","skuStandard":"300斤","orderActualPrice":"200","actualPickNum":"1","actualWeight":"550斤","changePrice":"50.2","outStockPrice":"1","actualPrice":"149.8"}]}]
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
         * userName : 你打不过我得
         * phone : 19956810688
         * orderNo : FX2020092800018
         * actualPickTime : 2020-09-28 12:09
         * skus : [{"skuCode":"03011","skuName":"长豆角（豇豆）","skuStandard":"300g","orderActualPrice":"11.9","actualPickNum":"1","actualWeight":"550g","changePrice":"1.5","outStockPrice":"0","actualPrice":"19.99"},{"skuCode":"03012","skuName":"长豆角（豇豆）","skuStandard":"301g","orderActualPrice":"11.8","actualPickNum":"0","actualWeight":"550g","changePrice":"1.5","outStockPrice":"0","actualPrice":"19.99"}]
         */

        private String userName;
        private String phone;
        private String orderNo;
        private String actualPickTime;
        private List<SkusBean> skus;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getActualPickTime() {
            return actualPickTime;
        }

        public void setActualPickTime(String actualPickTime) {
            this.actualPickTime = actualPickTime;
        }

        public List<SkusBean> getSkus() {
            return skus;
        }

        public void setSkus(List<SkusBean> skus) {
            this.skus = skus;
        }

        public static class SkusBean {
            /**
             * skuCode : 03011
             * skuName : 长豆角（豇豆）
             * skuStandard : 300g
             * orderActualPrice : 11.9
             * actualPickNum : 1
             * actualWeight : 550g
             * changePrice : 1.5
             * outStockPrice : 0
             * actualPrice : 19.99
             */

            private String skuCode;
            private String skuName;
            private String skuStandard;
            private String orderActualPrice;
            private String actualPickNum;
            private String actualWeight;
            private String changePrice;
            private String outStockPrice;
            private String actualPrice;
            private String skuImg;

            public String getSkuImg() {
                return skuImg;
            }

            public void setSkuImg(String skuImg) {
                this.skuImg = skuImg;
            }

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

            public String getOrderActualPrice() {
                return orderActualPrice;
            }

            public void setOrderActualPrice(String orderActualPrice) {
                this.orderActualPrice = orderActualPrice;
            }

            public String getActualPickNum() {
                return actualPickNum;
            }

            public void setActualPickNum(String actualPickNum) {
                this.actualPickNum = actualPickNum;
            }

            public String getActualWeight() {
                return actualWeight;
            }

            public void setActualWeight(String actualWeight) {
                this.actualWeight = actualWeight;
            }

            public String getChangePrice() {
                return changePrice;
            }

            public void setChangePrice(String changePrice) {
                this.changePrice = changePrice;
            }

            public String getOutStockPrice() {
                return outStockPrice;
            }

            public void setOutStockPrice(String outStockPrice) {
                this.outStockPrice = outStockPrice;
            }

            public String getActualPrice() {
                return actualPrice;
            }

            public void setActualPrice(String actualPrice) {
                this.actualPrice = actualPrice;
            }
        }
    }
}
