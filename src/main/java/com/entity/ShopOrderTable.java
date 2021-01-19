package com.entity;

import lombok.Data;

@Data
public class ShopOrderTable {

    private int onumbers; //订单编号
    private Book book; //书籍ID
    private int sonumber; //商品数量
    private double sostotalprice; //商品总价

    public ShopOrderTable() {
    }

    public ShopOrderTable(int onumbers, Book book, int sonumber, double sostotalprice) {
        this.onumbers = onumbers;
        this.book = book;
        this.sonumber = sonumber;
        this.sostotalprice = sostotalprice;
    }
}
