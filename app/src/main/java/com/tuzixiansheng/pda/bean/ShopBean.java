package com.tuzixiansheng.pda.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ShopBean {
    @Id
    Long id;
    private String shopId;
    private String name;
    private String address;
    private String phone;
    @Generated(hash = 2111858680)
    public ShopBean(Long id, String shopId, String name, String address,
            String phone) {
        this.id = id;
        this.shopId = shopId;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }
    @Generated(hash = 748345971)
    public ShopBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getShopId() {
        return this.shopId;
    }
    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
  
}
