package com.tuzixiansheng.pda.bean;


public class TitleBean {
    private String phone;
    private String nickName;
    private String pickNum;

    public TitleBean(String phone, String nickName, String pickNum) {
        this.phone = phone;
        this.nickName = nickName;
        this.pickNum = pickNum;
    }

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
