package com.entity;

import lombok.Data;

@Data
public class User {

    private int uid; //主键
    private String uname; //用户名
    private String upsw; //密码
    private String uemail; //邮箱
    private String uphoto; //头像
    private Address address; //默认地址

    public User() {
    }

    public User(int uid, String uname, String upsw, String uemail, String uphoto) {
        this.uid = uid;
        this.uname = uname;
        this.upsw = upsw;
        this.uemail = uemail;
        this.uphoto = uphoto;
    }

    public User(String uname, String upsw, String uemail, String uphoto) {
        this.uname = uname;
        this.upsw = upsw;
        this.uemail = uemail;
        this.uphoto = uphoto;
    }
}
