package com.service;

import com.entity.BookType;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BookTypeServiceTest {

    /**
     * 测试查询书籍类型表中的所有信息
     */
    @Test
    public void findMore() {
        List<BookType> list = new BookTypeService().findMore();
        list.forEach(System.out::println);
    }

    /**
     * 测试条件查询传入ID
     */
    @Test
    public void findBY() {
        BookType bookType = new BookType();
        bookType.setTid(2);
        List<BookType> list = new BookTypeService().findBY(bookType);
        list.forEach(System.out::println);
    }

    /**
     * 测试删除一条书籍信息,并查看是否删除
     */
    @Test
    public void deleteID() {
        boolean b = new BookTypeService().deleteID(10);
        System.out.println(b);
        findMore();
    }


    /**
     * 测试根据传入的数据去修改一条数据信息
     */
    @Test
    public void updateID() {
        BookType bookType = new BookType();
        bookType.setTid(9);
        bookType.setTstate("正常");
        new BookTypeService().updateID(bookType);
    }

    /**
     * 测试新增一条数据
     */
    @Test
    public void insertADD() {
        BookType bookType = new BookType();
        bookType.setTname("sss");
        bookType.setTstate("异常");
        boolean b = new BookTypeService().insertADD(bookType);
        System.out.println(b);
    }
}
