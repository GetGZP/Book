package com.entity;

import lombok.Data;

@Data
public class BookType {

    private int tid; //书籍类型ID
    private String tname; //书籍类型
    private String tstate; //状态

    public BookType() {
    }

    public BookType(int tid, String tname, String tstate) {
        this.tid = tid;
        this.tname = tname;
        this.tstate = tstate;
    }

    public BookType(String tname, String tstate) {
        this.tname = tname;
        this.tstate = tstate;
    }

    @Override
    public String toString() {
        return "BookType{" +
                "tid=" + tid +
                ", tname='" + tname + '\'' +
                ", tstate='" + tstate + '\'' +
                '}';
    }
}
