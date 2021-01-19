package com.dao;

import com.entity.OrderList;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface OrderListDAOMapper {

    /**
     * 增加一条订单信息
     * @param orderList
     * @return
     */
    boolean insertADD(OrderList orderList);

    /**
     * 查询订单总数以及总金额
     * @param uid
     * @return
     */
    HashMap<String, Object> find(@Param("uid") int uid);

    /**
     * 查询特定状态的订单数量
     * @param ostatus
     * @return
     */
    Integer findBystate(@Param("ostatus") String ostatus,@Param("uid") int uid);


    /**
     * 查询订单
     * @param orderList
     * @return
     */
    List<OrderList> findByMore(OrderList orderList);

    /**
     * 查询订单
     * @param orderList
     * @return
     */
    List<OrderList> findByMore1(OrderList orderList);

    /**
     * 修改订单
     * @param orderList
     * @return
     */
    boolean updateMore(OrderList orderList);

}
