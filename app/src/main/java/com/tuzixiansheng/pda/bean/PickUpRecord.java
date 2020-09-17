package com.tuzixiansheng.pda.bean;

import java.util.List;

public class PickUpRecord {

    /**
     * msg : 操作成功
     * code : 200
     * data : [{"phone":"18933434573","nickName":"四木","pickNum":"1"}]
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
         * phone : 18933434573
         * nickName : 四木
         * pickNum : 1
         */

        private String phone;
        private String nickName;
        private String pickNum;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPickNum() {
            return pickNum;
        }

        public void setPickNum(String pickNum) {
            this.pickNum = pickNum;
        }
    }
}
