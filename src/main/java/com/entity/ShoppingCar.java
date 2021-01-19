package com.entity;

import lombok.Data;

@Data
public class ShoppingCar {

    private int sid; //主键
    private Book book; //图书
    private int snumber; //单本购买数量
    private double stotalprice; //商品总价
    private User user; //用户ID

    public ShoppingCar() {
    }

    public ShoppingCar(int sid, Book book, int snumber, double stotalprice, User user) {
        this.sid = sid;
        this.book = book;
        this.snumber = snumber;
        this.stotalprice = stotalprice;
        this.user = user;
    }
}
