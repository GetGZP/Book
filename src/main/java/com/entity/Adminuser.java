package com.entity;

import lombok.Data;

@Data
public class Adminuser {

    private int aid; //主键
    private String auser; //管理员用户名
    private String apsw; //管理员密码
    private String path; //头像

    public Adminuser() {
    }

    public Adminuser(int aid, String auser, String apsw, String path) {
        this.aid = aid;
        this.auser = auser;
        this.apsw = apsw;
        this.path = path;
    }

    public Adminuser(String auser, String apsw, String path) {
        this.auser = auser;
        this.apsw = apsw;
        this.path = path;
    }

    @Override
    public String toString() {
        return "Adminuser{" +
                "aid=" + aid +
                ", auser='" + auser + '\'' +
                ", apsw='" + apsw + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
