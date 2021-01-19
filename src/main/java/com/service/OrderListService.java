package com.service;

import com.dao.BookDAOMapper;
import com.dao.OrderListDAOMapper;
import com.dao.ShopOrderTableDAOMapper;
import com.dao.ShoppingCarDAOMapper;
import com.entity.*;
import com.util.JsonUtil;
import com.util.MyBatisUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderListService {

    private static SqlSession session;

    /**
     * 生成订单
     * @param orderList
     * @param sid
     * @return
     */
    public Integer findADDOrder(OrderList orderList,String ...sid){
        SqlSession session = MyBatisUtil.sqlSessionFactory.openSession();
        OrderListDAOMapper mapper = session.getMapper(OrderListDAOMapper.class);
        boolean panding = false;
        panding = mapper.insertADD(orderList);
        ShoppingCarDAOMapper mapper1 = session.getMapper(ShoppingCarDAOMapper.class);
        List<ShoppingCar> list = mapper1.findBySid(sid);
        List<ShopOrderTable> list1 = new ArrayList<>();
        for (ShoppingCar shoppingCar : list){
            ShopOrderTable shopOrderTable = new ShopOrderTable();
            Book book = new Book();
            book.setBid(shoppingCar.getBook().getBid());
            shopOrderTable.setOnumbers(orderList.getOnumbers());
            shopOrderTable.setBook(book);
            shopOrderTable.setSonumber(shoppingCar.getSnumber());
            shopOrderTable.setSostotalprice(shoppingCar.getStotalprice());
            list1.add(shopOrderTable);
        }
        
        ShopOrderTableDAOMapper mapper2 = session.getMapper(ShopOrderTableDAOMapper.class);
        panding = mapper2.insertADDMore(list1);
        for (ShopOrderTable shopOrderTable : list1){
            BookDAOMapper mapper3 = session.getMapper(BookDAOMapper.class);
            Book book = new Book();
            BookType bookType = new BookType();
            book.setBookType(bookType);
            book.setBid(shopOrderTable.getBook().getBid());
            List<Book> list2 = mapper3.findMore(book);
            Book book1 = list2.get(0);


            book1.setBinventory(book1.getBinventory() - shopOrderTable.getSonumber());
            panding = mapper3.updateID(book1);
            ShoppingCar shoppingCar = new ShoppingCar();
            shoppingCar.setBook(book1);
            shoppingCar.setUser(orderList.getUser());
            panding = mapper1.deleteCar(shoppingCar);

        }

        if (panding){
            session.commit();
        }else {
            session.rollback();
        }
        session.close();

        return orderList.getOnumbers();
    }

    /**
     * 查询订单
     * @param orderList
     * @return
     */
    public List<OrderList> findByMore(OrderList orderList){
        session = MyBatisUtil.sqlSessionFactory.openSession();
        OrderListDAOMapper mapper = session.getMapper(OrderListDAOMapper.class);
        List<OrderList> list = mapper.findByMore(orderList);
        session.close();
        return list;
    }


    /**
     * 查询订单
     * @param orderList
     * @return
     */
    public List<OrderList> findByMore1(OrderList orderList){
        session = MyBatisUtil.sqlSessionFactory.openSession();
        OrderListDAOMapper mapper = session.getMapper(OrderListDAOMapper.class);
        List<OrderList> list = mapper.findByMore1(orderList);
        session.close();
        return list;
    }

    /**
     * 修改订单付款状态
     * @param orderList
     * @return
     */
    public boolean updateMore(OrderList orderList){
        session = MyBatisUtil.sqlSessionFactory.openSession();
        OrderListDAOMapper mapper = session.getMapper(OrderListDAOMapper.class);
        boolean b = mapper.updateMore(orderList);
        if(b){
            session.commit();
        }else {
            session.rollback();
        }
        session.close();
        return b;

    }

    /**
     * 查询特殊值
     * @param uid
     * @return
     */
    public HashMap<String, Object> find ( int uid){
        session = MyBatisUtil.sqlSessionFactory.openSession();
        OrderListDAOMapper mapper = session.getMapper(OrderListDAOMapper.class);
        HashMap<String, Object> map = mapper.find(uid);
        session.close();
        return map;
    }

    /**
     * 查询状态的值
     * @param ostatus
     * @param uid
     * @return
     */
    public Integer findBystate(String ostatus,int uid){
        session = MyBatisUtil.sqlSessionFactory.openSession();
        OrderListDAOMapper mapper = session.getMapper(OrderListDAOMapper.class);
        Integer integer = mapper.findBystate(ostatus, uid);
        session.commit();
        session.close();
        return integer;
    }
}
