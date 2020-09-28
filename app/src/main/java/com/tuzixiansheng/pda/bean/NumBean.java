package com.tuzixiansheng.pda.bean;

public class NumBean {
    String type;
    int num;

    public NumBean(String type, int num) {
        this.type = type;
        this.num = num;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
