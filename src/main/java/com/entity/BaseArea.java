package com.entity;

import lombok.Data;

@Data
public class BaseArea {

    private int base_areaid; //地址ID
    private String name; //地区名字
    private int parentid; //上级路径ID
    private int vieworder; //顺序

    public BaseArea() {
    }

    public BaseArea(int base_areaid, String name, int parentid, int vieworder) {
        this.base_areaid = base_areaid;
        this.name = name;
        this.parentid = parentid;
        this.vieworder = vieworder;
    }
}
