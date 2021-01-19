package com.entity;

import lombok.Data;

@Data
public class Address {

    private int adid; //主键
    private String adname; //收货人姓名
    private String adphone; //手机号
    private String adress; //收货地址
    private User user; //客户

    public Address() {
    }

    public Address(int adid, String adname, String adphone, String adress, User user) {
        this.adid = adid;
        this.adname = adname;
        this.adphone = adphone;
        this.adress = adress;
        this.user = user;
    }

    public Address(String adname, String adphone, String adress, User user) {
        this.adname = adname;
        this.adphone = adphone;
        this.adress = adress;
        this.user = user;
    }

    public Address(int adid, String adname, String adphone, String adress) {
        this.adid = adid;
        this.adname = adname;
        this.adphone = adphone;
        this.adress = adress;
    }
}
