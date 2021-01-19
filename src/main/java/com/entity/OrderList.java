package com.entity;

import lombok.Data;


import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

@Data
public class OrderList {

    private int onumbers; //订单编号
    private Timestamp ordertime; //下单时间
    private User user; //Uid
    private Address address; //地址ID
    private double omoney; //总金额
    private String ostatus; //收货状态

    public OrderList() {
        setOrdertime();
        setOnumbers();
    }

    public OrderList(int onumbers, Timestamp ordertime, User user, Address address, double omoney, String ostatus) {
        this.onumbers = onumbers;
        this.ordertime = ordertime;
        this.user = user;
        this.address = address;
        this.omoney = omoney;
        this.ostatus = ostatus;
    }


    public void setOrdertime() {
        ordertime = new Timestamp(System.currentTimeMillis());
    }

    public void setOnumbers() {
        Random random = new Random(System.currentTimeMillis());
        onumbers = random.nextInt(100000000);
    }
}
