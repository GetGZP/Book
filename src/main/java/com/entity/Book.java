package com.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class Book {

    private int bid; //主键
    private String bname; //书籍名称
    private String bauthor; //作者
    private int binventory; //库存
    private double bbaseprice; //市场价
    private double bprice; //售价
    private String bstate; //状态
    private int bnumbers; //商品编号
    private String bpress; //出版社
    private Timestamp bpresstime; //出版时间
    private String bdetails; //商品描述
    private BookType bookType; //商品类型
    private String path; //图片路径

    private List<BookType> bookTypeList; //所有的图书类型

    public void setBpresstime() {
        bpresstime = new Timestamp(System.currentTimeMillis());
    }

    public Book() {
        setBpresstime();
    }

    @Override
    public String toString() {
        return "Book{" +
                "bid=" + bid +
                ", bname='" + bname + '\'' +
                ", bauthor='" + bauthor + '\'' +
                ", binventory=" + binventory +
                ", bbaseprice=" + bbaseprice +
                ", bprice=" + bprice +
                ", bstate='" + bstate + '\'' +
                ", bnumbers=" + bnumbers +
                ", bpress='" + bpress + '\'' +
                ", bpresstime=" + bpresstime +
                ", bdetails='" + bdetails + '\'' +
                ", bookType=" + bookType +
                ", path='" + path + '\'' +
                '}';
    }
}
