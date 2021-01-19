package com.service;

import com.entity.Book;
import com.entity.BookType;
import com.github.pagehelper.PageInfo;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookServiceTest {

    /**
     * 测试查询带分页或者模糊查询
     */
    @Test
    public void findMore() {
        Book book = new Book();
        BookType bookType = new BookType();
        book.setBookType(bookType);
        PageInfo<Book> info = new BookService().findMore(book, 0, 0);
        System.out.println(info);

    }

    /**
     * 测试编辑一条数据
     */
    @Test
    public void updateID() {
        Book book = new Book();
        book.setBid(1);
        book.setBstate("正常");
        boolean b = new BookService().updateID(book);
        System.out.println(b);
    }

    /**
     * 测试修改一条数据上架状态
     */
    @Test
    public void upbstate() {
        Book book = new Book();
        book.setBid(3);
        book.setBstate("正常");
        boolean b = new BookService().upbstate(book);
        System.out.println(b);
    }

    /**
     * 测试新增一条数据
     */
    @Test
    public void insertADD() {
        Book book = new Book();
        book.setBname("s");
        book.setBprice(35);
        book.setBstate("已下架");
        book.setBnumbers(123);
        book.setPath("Book/img/15.jpg");
        boolean b = new BookService().insertADD(book);
        System.out.println(b);
    }

    /**
     * 测试修改多条数据上架状态
     */
    @Test
    public void stateupdates() {
        boolean b = new BookService().stateupdates("正常", "1","2","3");
        System.out.println(b);
    }
}
